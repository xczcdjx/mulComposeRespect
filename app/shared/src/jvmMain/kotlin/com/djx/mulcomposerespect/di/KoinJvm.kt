package com.djx.mulcomposerespect.di

fun startKoin() {
    initApplication {
        modules(jvmPlatformModule)
    }
}