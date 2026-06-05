package com.djx.mulcomposerespect.di

import org.koin.core.KoinApplication
import org.koin.core.annotation.KoinApplication as KoinApplicationAnnotation
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.plugin.module.dsl.startKoin

@KoinApplicationAnnotation(modules = [
    AppModule::class,
])
class KoinApp

fun initApplication(config: KoinAppDeclaration? = null): KoinApplication {
    return startKoin<KoinApp> {
        includes(config)
    }
}

@Suppress("unused")
fun initKoin() = initApplication {}