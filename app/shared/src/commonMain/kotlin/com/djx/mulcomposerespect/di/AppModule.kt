package com.djx.mulcomposerespect.di

import com.djx.mulcomposerespect.viewmodel.ViewModelModule
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module

@Configuration
@Module(includes = [ViewModelModule::class])
class AppModule