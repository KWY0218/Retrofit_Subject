package com.example.myapplication.repository

import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.model.LectureResult
import com.example.myapplication.model.StatusCode
import retrofit2.Response

class Repository {
    suspend fun getStatusCode(): Response<StatusCode> {
        return RetrofitInstance.api.getStatusCode()
    }

    suspend fun getHangangList(limit:Int,page:Int): Response<LectureResult>{
        return RetrofitInstance.hangangApi.getHangangList(limit,page)
    }
}