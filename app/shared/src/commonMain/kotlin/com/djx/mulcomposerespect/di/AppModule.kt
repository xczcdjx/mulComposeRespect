package com.djx.mulcomposerespect.di

import com.djx.mulcomposerespect.di.modules.ViewModelModule
import org.koin.core.annotation.Module

@Module(includes = [
    ViewModelModule::class,
])
class AppModule
