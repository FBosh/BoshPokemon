package com.boshpokemon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.boshpokemon.App

abstract class BaseFragment : Fragment() {
    abstract val layoutRes: Int

    protected val appCtx by lazy { App.shared().applicationContext ?: App.shared() }

    @CallSuper
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutRes, container, false)

    override fun onStart() {
        super.onStart()

        initUI()
    }

    protected abstract fun initUI()

    protected fun getColor(colorRes: Int) = ContextCompat.getColor(appCtx, colorRes)
}
