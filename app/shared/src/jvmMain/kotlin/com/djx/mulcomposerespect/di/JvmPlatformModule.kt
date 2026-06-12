package com.djx.mulcomposerespect.di

import com.djx.mulcomposerespect.export.DataStoreFactory
import org.koin.dsl.module

val jvmPlatformModule = module {
    single {
        DataStoreFactory()
    }
}