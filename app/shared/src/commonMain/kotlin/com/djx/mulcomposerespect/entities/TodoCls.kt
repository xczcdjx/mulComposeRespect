package com.djx.mulcomposerespect.entities

import kotlinx.serialization.Serializable

@Serializable
data class TodoCls(
    val id: String,
    val createdAt: String,
    val updatedAt: String?,
    var title: String,
    var content: String,
    var done: Boolean,
)

@Serializable
data class TodoBody(
    val id: String="",
    val title: String="",
    val content: String="",
    val done: Boolean?=false,
)