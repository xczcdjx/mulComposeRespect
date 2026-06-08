package com.djx.mulcomposerespect.di

import com.djx.mulcomposerespect.di.modules.AppStateModule
import com.djx.mulcomposerespect.di.modules.NetworkModule
import com.djx.mulcomposerespect.di.modules.ViewModelModule
import org.koin.core.KoinApplication
import org.koin.core.annotation.Module
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

@Module(includes = [
    ViewModelModule::class,
    AppStateModule::class,
    NetworkModule::class,
])
class AppModule

@Suppress("unused")
fun initKoin() {
    initApplication {}
}

object KoinInitializer {
    fun start() {
        initApplication {}
    }
}