package com.djx.mulcomposerespect.services

import com.djx.mulcomposerespect.constants.BaseEntityRes
import com.djx.mulcomposerespect.entities.TodoBody
import com.djx.mulcomposerespect.entities.TodoCls
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.PATCH
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Path

interface ApiService {

    @GET("/")
    suspend fun getHello(): BaseEntityRes<String>

    @GET("todos")
    suspend fun getTodos(): BaseEntityRes<List<TodoCls>>

    @GET("todos/{id}")
    suspend fun getTodo(@Path("id") id: String): BaseEntityRes<TodoCls>

    @POST("todos")
    suspend fun createTodo(
        @Body body: TodoBody
    ): BaseEntityRes<TodoCls>

    @PATCH("todos")
    suspend fun updateTodo(
        @Body body: TodoBody
    ): BaseEntityRes<TodoCls>

    @DELETE("todos/{id}")
    suspend fun deleteTodo(@Path("id") id: String): BaseEntityRes<Unit?>
}