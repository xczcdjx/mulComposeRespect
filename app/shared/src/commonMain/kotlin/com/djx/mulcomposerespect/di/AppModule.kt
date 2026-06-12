package com.djx.mulcomposerespect.di

import com.djx.mulcomposerespect.di.modules.AppStateModule
import com.djx.mulcomposerespect.di.modules.DataStoreModule
import com.djx.mulcomposerespect.di.modules.NetworkModule
import com.djx.mulcomposerespect.di.modules.ViewModelModule
import org.koin.core.annotation.Module

@Module(includes = [
    DataStoreModule::class,
    AppStateModule::class,
    NetworkModule::class,
    ViewModelModule::class,
])
class AppModule
