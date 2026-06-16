package com.djx.mulcomposerespect.export


enum class AppPlatform {
    Android,
    IOS,
    Desktop,
    Web,
    Wasm,
    Unknown
}

expect val currentPlatform: AppPlatform

val isDesktop: Boolean
    get() = currentPlatform == AppPlatform.Desktop

val isAndroid: Boolean
    get() = currentPlatform == AppPlatform.Android

val isIOS: Boolean
    get() = currentPlatform == AppPlatform.IOS