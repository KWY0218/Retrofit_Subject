package com.example.myapplication.api

import com.example.myapplication.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("prod/version")
    suspend fun getPost(): Response<Post>

}