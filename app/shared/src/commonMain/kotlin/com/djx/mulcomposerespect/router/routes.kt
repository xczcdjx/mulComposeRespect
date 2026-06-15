package com.djx.mulcomposerespect.router

sealed class Routes(val route: String) {
    object Index: Routes("Index")
    object Detail: Routes("Detail")
    object TodoDemo: Routes("TodoDemo")
    object Scan: Routes("Scan")
    object ImageLoader: Routes("ImageLoader")
}