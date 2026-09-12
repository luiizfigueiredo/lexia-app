package com.lexia.app

import android.app.Application
import com.lexia.app.di.AppContainer

class LexiaApplication : Application() {
    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}
