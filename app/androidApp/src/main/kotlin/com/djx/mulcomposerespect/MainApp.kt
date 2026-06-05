package com.djx.mulcomposerespect

import android.app.Application
import com.djx.mulcomposerespect.di.initKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}