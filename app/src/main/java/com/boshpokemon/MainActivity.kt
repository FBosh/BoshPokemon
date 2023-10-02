package com.boshpokemon

import android.os.Bundle
import android.view.View
import com.boshpokemon.fragments.SplashFragment
import com.boshpokemon.fragments.TypeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private var isFirstStart = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        if (isFirstStart) {
            handleFirstStart()
            return
        }

        handleFragment(Constants.FRAG_REPLACE, frag_container.id, TypeFragment())
        initUI()
    }

    private fun handleFirstStart() {
        if (isFirstStart) {
            bar?.visibility = View.GONE
            handleFragment(Constants.FRAG_REPLACE, frag_container.id, SplashFragment())

            App.shared().appHandler.also {
                it.postDelayed({ isFirstStart = false }, 1000)
                it.postDelayed({ if (!this.isDestroyed) this.onStart() }, 3000)
            }
        }
    }

    private fun initUI() {
        bar?.also {
            it.visibility = View.VISIBLE
            it.setLogo(R.mipmap.ic_poke_ball)

            it.setOnClickListener {
                val currentFrag = supportFragmentManager.findFragmentById(R.id.frag_container)

                if (currentFrag is TypeFragment) {
//                    handleFragment(Constants.FRAG_REPLACE, frag_container.id, TypeFragment())
                    currentFrag.onStart()
                    vibrateOnce(50)
                }
            }
        }
    }
}
