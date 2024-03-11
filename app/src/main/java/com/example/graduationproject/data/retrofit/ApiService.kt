package com.example.graduationproject.data.retrofit

import com.example.graduationproject.data.Register
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/api/register/")
    suspend fun postRegister(@Body register: Register): Response<Register>
}