package com.djx.mulcomposerespect.router

sealed class Routes(val route: String) {
    object Index: Routes("Index")
    object WebView: Routes("WebView")
    object Count: Routes("Count")
    object TodoList: Routes("TodoList")
    object Scan: Routes("Scan")
    object ImageLoader: Routes("ImageLoader")
}