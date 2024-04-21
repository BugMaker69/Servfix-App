package com.example.graduationproject.data.repositories

import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.retrofit.ApiService
import com.example.graduationproject.utils.DataStoreToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FindProviderSearchReopsitory @Inject constructor(    val apiService : ApiService,val dataStoreToken: DataStoreToken) {
    suspend fun getProvidersList(id: Int,pageNum:Int):List<ReturnedProviderData> {
      return  apiService.getProviders(id,pageNum).providers;
    }
    suspend fun searchOnList(id:Int,keyword:String,minRate:Int,place:String,pageNum: Int):List<ReturnedProviderData>{
        return apiService.getProvidersSearch(token = "Bearer ${dataStoreToken.getToken()}", id = id, keyword = keyword, rate = minRate,place=place,page=pageNum).providers
    }

}