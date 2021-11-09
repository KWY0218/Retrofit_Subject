package com.example.myapplication.repository

import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.model.Body
import com.example.myapplication.model.StatusCode
import retrofit2.Response

class Repository {
    suspend fun getStatusCode(s:String): Response<StatusCode> {
        return RetrofitInstance.api.getStatusCode(s)
    }

    suspend fun getBody(b:String):Response<Body>{
        return RetrofitInstance.api.getBody(b)
    }
}