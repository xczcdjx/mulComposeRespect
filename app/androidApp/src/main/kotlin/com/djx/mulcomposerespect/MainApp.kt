package com.djx.mulcomposerespect

import android.app.Application
import com.djx.mulcomposerespect.di.initApplication
import com.djx.mulcomposerespect.export.DataStoreFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidPlatformModule = module {
    single {
        DataStoreFactory(androidContext())
    }
}
class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initApplication{
            androidContext(this@MainApp)
            modules(androidPlatformModule)
        }
    }
}