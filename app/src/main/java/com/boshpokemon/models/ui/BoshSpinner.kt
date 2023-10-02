package com.boshpokemon.models.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.ListPopupWindow
import androidx.core.content.ContextCompat
import com.boshpokemon.App
import com.boshpokemon.R

class BoshSpinner : AppCompatSpinner {
    constructor(ctx: Context) : super(ctx)

//    constructor(ctx: Context, mode: Int) : super(ctx, mode)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    constructor(ctx: Context, attrs: AttributeSet, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr)

//    constructor(ctx: Context, attrs: AttributeSet, defStyleAttr: Int, mode: Int)
//            : super(ctx, attrs, defStyleAttr, mode)

//    constructor(ctx: Context, attrs: AttributeSet, defStyleAttr: Int, mode: Int, popupTheme: Resources.Theme)
//            : super(ctx, attrs, defStyleAttr, mode, popupTheme)

    override fun performClick() = run {
        AppCompatSpinner::class.java.getDeclaredField("mPopup").also { field ->
            field.isAccessible = true

            val window = field.get(this) as ListPopupWindow

            object : Runnable {
                override fun run() {
                    window.listView.also {
//                        when (it?.isVerticalScrollBarEnabled) {
//                            null -> App.shared().appHandler.post(this)
//                            true -> it.isVerticalScrollBarEnabled = false
//                        }

                        if (it == null) {
                            App.shared().appHandler.post(this)

                            return
                        }

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            it.verticalScrollbarThumbDrawable = ContextCompat.getDrawable(context, R.drawable.shape_scrollbar)

                            return
                        }

                        View::class.java.getDeclaredField("mScrollCache").also { cacheField ->
                            cacheField.isAccessible = true

                            cacheField.get(it).also { myScrollBar ->
                                myScrollBar.javaClass.getDeclaredField("scrollBar").also { scrollBarField ->
                                    scrollBarField.isAccessible = true

                                    scrollBarField.get(myScrollBar).also tag@{ nativeScrollBar ->
                                        val jClass = nativeScrollBar.javaClass
                                        val strTargetMethod = "setVerticalThumbDrawable"

                                        if (!jClass.declaredMethods.contentToString().contains(strTargetMethod)) {
                                            return@tag
                                        }

                                        jClass.getDeclaredMethod(
                                                strTargetMethod,
                                                Drawable::class.java
                                        ).also { method ->
                                            method.isAccessible = true

                                            method.invoke(
                                                    nativeScrollBar,
                                                    ContextCompat.getDrawable(context, R.drawable.shape_scrollbar)
                                            )

                                            method.isAccessible = false
                                        }
                                    }

                                    scrollBarField.isAccessible = false
                                }
                            }

                            cacheField.isAccessible = false
                        }
                    }
                }
            }.run()

            field.isAccessible = false
        }

        super.performClick()
    }
}
