package com.djx.mulcomposerespect

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform