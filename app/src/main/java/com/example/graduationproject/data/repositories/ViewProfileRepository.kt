package com.example.graduationproject.data.repositories


import android.util.Log
import com.example.graduationproject.data.ViewProfileData
import com.example.graduationproject.data.retrofit.RetrofitClient
import com.example.graduationproject.utils.DataStoreToken

class ViewProfileRepository() {
 val dataStoreToken=DataStoreToken()

    val api=RetrofitClient.userRegisterationApiService()
    suspend fun getProviderProfile(id:Int):ViewProfileData{

        Log.d("teet", "getProviderProfile: ${dataStoreToken.getToken()}")
        return  api.viewProviderProfile(id=id,dataStoreToken.getToken())
    }
}