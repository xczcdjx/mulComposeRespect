package com.djx.mulcomposerespect.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module


/*val appModule = module {
    viewModel<HomeViewModel>()
}*/

@Module
@ComponentScan("com.djx.mulcomposerespect")
class AppModule
