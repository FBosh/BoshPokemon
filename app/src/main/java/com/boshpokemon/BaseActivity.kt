package com.boshpokemon

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.boshpokemon.fragments.BaseFragment

open class BaseActivity : AppCompatActivity() {
    private var canCloseApp = false

    protected val bar by lazy {
        findViewById<Toolbar?>(resources.getIdentifier("action_bar", "id", packageName))
    }

    override fun onStart() {
        super.onStart()

        supportFragmentManager.popBackStack()
    }

    override fun onBackPressed() {
        if (canCloseApp) finish()

        Toast.makeText(this, R.string.text_close_app, Toast.LENGTH_SHORT).show()
        canCloseApp = true
        App.shared().appHandler.postDelayed({ canCloseApp = false }, 2500)
    }

    protected fun handleFragment(act: String, containerId: Int, frag: BaseFragment) {
        when (act) {
            Constants.FRAG_ADD -> supportFragmentManager.beginTransaction().add(containerId, frag).commit()
            Constants.FRAG_REPLACE -> supportFragmentManager.beginTransaction().replace(containerId, frag).commit()
            Constants.FRAG_REMOVE -> supportFragmentManager.beginTransaction().remove(frag).commit()
        }
    }

    protected fun vibrateOnce(milliseconds: Long) {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator? ?: return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(milliseconds)
        }
    }
}
