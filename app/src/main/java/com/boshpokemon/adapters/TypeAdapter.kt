package com.boshpokemon.adapters

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.boshpokemon.R
import com.boshpokemon.models.entities.Type

class TypeAdapter : ArrayAdapter<Type> {
    companion object {
        private const val GET_VIEW = "getView"
        private const val GET_DROP_DOWN_VIEW = "getDropDownView"
    }

    private val mAL: ArrayList<Type>

    constructor(ctx: Context, layoutRes: Int, array: Array<Type>) : super(ctx, layoutRes, array) {
        mAL = arrayListOf<Type>().also { it.addAll(array) }
    }

    constructor(ctx: Context, layoutRes: Int, list: List<Type>) : super(ctx, layoutRes, list) {
        mAL = arrayListOf<Type>().also { it.addAll(list) }
    }

    override fun getCount() = mAL.size

    override fun getItem(position: Int) = mAL[position]

    override fun getPosition(item: Type?) = if (item != null) mAL.indexOf(item) else -878787

    override fun getView(position: Int, convertView: View?, parent: ViewGroup) =
            getCustomView(position, convertView, parent, GET_VIEW)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup) =
            getCustomView(position, convertView, parent, GET_DROP_DOWN_VIEW)

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup, which: String): View {
        val resultView = (LayoutInflater.from(context)
                ?: return super.getView(position, convertView, parent))
                .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
                ?: return super.getView(position, convertView, parent)

        (resultView.findViewById<TextView>(android.R.id.text1) ?: return resultView).also {
            if (mAL[position].name == context.getString(R.string.type_unknown)) return resultView

            it.text = mAL[position].name
            it.setTextColor(ContextCompat.getColor(context, mAL[position].colorDarkRes))

            when (which) {
                GET_VIEW -> {
                    it.background = ContextCompat.getDrawable(context, R.drawable.shape_radius)
                    (it.background as GradientDrawable).setColor(ContextCompat.getColor(context, mAL[position].colorLightRes))
                }

                GET_DROP_DOWN_VIEW -> {
                    it.setBackgroundResource(mAL[position].colorLightRes)
                }
            }
        }

        return resultView
    }

    fun removeTemporarily(type: Type) = run {
        recoverAL()
        mAL.remove(type)
    }

    fun recoverAL() = initAL(arrayListOf<Type>().also { it.addAll(Type.getTypes()) })

    fun initAL(al: ArrayList<Type>): Boolean {
        mAL.also {
            it.clear()
            return it.addAll(al)
        }
    }

    fun getAL() = mAL
}
