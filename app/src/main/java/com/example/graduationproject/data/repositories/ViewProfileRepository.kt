package com.example.graduationproject.data.repositories


import android.util.Log
import com.example.graduationproject.data.ViewProfileData
import com.example.graduationproject.data.retrofit.ApiService
import com.example.graduationproject.utils.DataStoreToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewProfileRepository @Inject constructor(val api:ApiService, val dataStoreToken:DataStoreToken) {


    suspend fun getProviderProfile(id:Int):ViewProfileData{
        Log.d("teet", "getProviderProfile: ${dataStoreToken.getToken()}")
            return  api.viewProviderProfile(id=id,"Bearer ${dataStoreToken.getToken()}")
    }
    suspend fun addToFavorite(id:Int):String{

        api.addFavorite(id=id,"Bearer ${dataStoreToken.getToken()}")
        Log.d("lol", "addToFavorite: first $id +${        api.addFavorite(id=id,"Bearer ${dataStoreToken.getToken()}").body()?.details
        }")
        return   api.addFavorite(id=id,"Bearer ${dataStoreToken.getToken()}").body()?.details.toString()
    }
}