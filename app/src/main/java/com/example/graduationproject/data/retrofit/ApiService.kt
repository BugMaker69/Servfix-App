package com.example.graduationproject.data.retrofit

import com.example.graduationproject.data.LoginRequest
import com.example.graduationproject.data.LoginResponse
import com.example.graduationproject.data.Register
import com.example.graduationproject.data.RequsetUpdateData
import com.example.graduationproject.data.ReturnedUserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @POST("/api/register/")
    suspend fun postRegister(@Body register: Register): Response<Register>

    @POST("/api/token/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("api/userinfo/")
    suspend fun getReturnedUserData(@Header("Authorization") token: String): ReturnedUserData


    @PUT("api/userinfo/update")
    suspend fun updateUserData(@Header("Authorization") token: String, @Body userData: RequsetUpdateData): ReturnedUserData


}