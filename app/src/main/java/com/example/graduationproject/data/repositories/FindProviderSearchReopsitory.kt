package com.example.graduationproject.data.repositories

import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.ServiceProviderSearch
import com.example.graduationproject.data.retrofit.RetrofitClient

class FindProviderSearchReopsitory {
    val apiService = RetrofitClient.userRegisterationApiService()
    suspend fun getProvidersList(id: Int): List<ReturnedProviderData> {
      return  apiService.getProvidersSearch(id);
    }

}