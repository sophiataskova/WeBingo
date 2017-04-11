package com.example.sophiataskova.webingo

import android.app.Application

/**
 * Created by sophiataskova on 4/11/17.
 */
class BingoApplication : Application() {
    companion object {
        var instance: Application? = null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}