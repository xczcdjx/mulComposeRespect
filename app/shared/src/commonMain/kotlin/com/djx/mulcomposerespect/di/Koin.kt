package com.djx.mulcomposerespect.di

import com.djx.mulcomposerespect.viewmodel.list.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.module
import org.koin.ksp.generated.*
val appModule = module {
    viewModelOf(::HomeViewModel)
}
fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        includes(config)
        modules(appModule)
    }
}