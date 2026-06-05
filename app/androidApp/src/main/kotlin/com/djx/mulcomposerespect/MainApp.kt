package com.djx.mulcomposerespect

import android.app.Application
import com.djx.mulcomposerespect.di.initApplication
import org.koin.android.ext.koin.androidContext
class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initApplication{
            androidContext(this@MainApp)
        }
    }
}