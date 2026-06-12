package com.djx.mulcomposerespect.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.ksp.generated.module



fun initApplication(config: KoinAppDeclaration = {}): KoinApplication {
    return startKoin{
        config()
        modules(
            AppModule().module,
        )
    }
}

@Suppress("unused")
fun initKoin() {
    initApplication {}
}