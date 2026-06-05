package com.djx.mulcomposerespect.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.ksp.generated.module

fun initApplication(config: KoinAppDeclaration? = null): KoinApplication {
    return startKoin {
        includes(config)
        modules(
            AppModule().module,
        )
    }
}

@Suppress("unused") //using in iOS
fun initKoin() = initApplication {}