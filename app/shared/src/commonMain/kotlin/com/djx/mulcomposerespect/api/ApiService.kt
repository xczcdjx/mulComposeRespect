package com.djx.mulcomposerespect.api


import com.djx.mulcomposerespect.dto.BaseEntityRes
import com.djx.mulcomposerespect.entities.TodoBody
import com.djx.mulcomposerespect.entities.TodoCls
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.Field
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Path

interface ApiService {

    @GET("/")
    suspend fun getHello(): BaseEntityRes<String>

    @GET("todo")
    suspend fun getTodos(): BaseEntityRes<List<TodoCls>>

    @GET("todo/{id}")
    suspend fun getTodo(@Path("id") id: String): BaseEntityRes<TodoCls>

    @POST("todo")
    suspend fun createTodo(
        @Body body: TodoBody
    ): BaseEntityRes<TodoCls>

    @PUT("todo")
    suspend fun updateTodo(
        @Body body: TodoBody
    ): BaseEntityRes<TodoCls>

    @DELETE("todo/{id}")
    suspend fun deleteTodo(): BaseEntityRes<TodoCls?>
}