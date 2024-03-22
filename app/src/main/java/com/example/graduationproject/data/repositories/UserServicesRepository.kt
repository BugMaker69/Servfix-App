package com.example.graduationproject.data.repositories

import com.example.graduationproject.data.Services
import com.example.graduationproject.data.retrofit.RetrofitClient


class UserServicesRepository {
    private val apiService= RetrofitClient.userRegisterationApiService()

suspend fun getServicesList():List<Services>{
    return apiService.getServices().service
}
}