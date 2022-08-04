package com.jorfald.friender

import android.app.Application
import android.content.Context

class FrienderApplication : Application() {

    companion object {
        lateinit var application: Application
    }

    override fun onCreate() {
        super.onCreate()

        application = this
    }
    fun getContext() : Context {
        return application!!.applicationContext

    }
}