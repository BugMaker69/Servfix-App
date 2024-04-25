package com.example.graduationproject.data.repositories

import com.example.graduationproject.data.Services
import com.example.graduationproject.data.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserServicesRepository @Inject constructor(val apiService:ApiService){

suspend fun getServicesList():List<Services>{

    return apiService.getServices().service
}
}