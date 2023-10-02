package com.boshpokemon

import android.app.Application
import android.os.Handler
import android.util.Log

class App : Application() {
    companion object {
        const val TAG = "App"
        private lateinit var instance: App

        fun shared() = instance
    }

    val appHandler = Handler()

    override fun onCreate() {
        super.onCreate()

        instance = this

        object : Runnable {
            override fun run() {
                Log.i(TAG, "$packageName is running.")
                appHandler.postDelayed(this, 1000)
            }
        }.run()
    }
}
