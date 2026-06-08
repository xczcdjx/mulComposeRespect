package com.djx.mulcomposerespect.api


import com.djx.mulcomposerespect.dto.ApiResponse
import de.jensklingenberg.ktorfit.http.GET

interface ApiService {

    @GET("/")
    suspend fun getHello(): ApiResponse<String>
}