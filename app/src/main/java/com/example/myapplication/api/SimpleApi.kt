package com.example.myapplication.api

import com.example.myapplication.model.Body
import com.example.myapplication.model.StatusCode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {

    @GET("prod/version")
    suspend fun getStatusCode(@Query("statusCode")s:String): Response<StatusCode>

    @GET("prod/version")
    suspend fun getBody(@Query("body")b:String):Response<Body>

}