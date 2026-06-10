package com.djx.mulcomposerespect.dto

import kotlinx.serialization.Serializable
interface BaseRes {
    val code: Int
    val msg: String
}
@Serializable
data class BaseEntityRes<T>(val data: T, override val code: Int, override val msg: String) :
    BaseRes