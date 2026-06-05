package com.djx.mulcomposerespect.di

import com.djx.mulcomposerespect.viewmodel.HomeViewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel


val appModule = module {
    viewModel<HomeViewModel>()
}

/*@Module
@ComponentScan("com.djx")
class AppModule*/
