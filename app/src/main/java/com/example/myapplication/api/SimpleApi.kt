package com.example.myapplication.api

import com.example.myapplication.model.Lecture
import com.example.myapplication.model.LectureResult
import com.example.myapplication.model.StatusCode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {

    @GET("prod/version")
    suspend fun getStatusCode(): Response<StatusCode>

    @GET("lectures/")
    suspend fun getHangangList(
        @Query("limit")limit:Int,
        @Query("page")page:Int
    ): Response<LectureResult>
}