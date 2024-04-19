package com.example.graduationproject.data.repositories

import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.retrofit.ApiService
import com.example.graduationproject.data.retrofit.RetrofitClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FindProviderSearchReopsitory @Inject constructor(    val apiService : ApiService) {
    suspend fun getProvidersList(id: Int): List<ReturnedProviderData> {
      return  apiService.getProvidersSearch(id);
    }

}