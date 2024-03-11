package com.example.graduationproject.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private fun instance(baseUrl: String): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()

    fun userRegisterationApiService():ApiService{
        return instance("https://p2kjdbr8-8000.uks1.devtunnels.ms").create(ApiService::class.java)
    }
}
