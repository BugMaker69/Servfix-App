package com.example.graduationproject.data.repositories


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.example.graduationproject.data.FavouritesList
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.ViewProfileData
import com.example.graduationproject.data.retrofit.ApiService
import com.example.graduationproject.data.retrofit.RetrofitClient
import com.example.graduationproject.utils.DataStoreToken
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewProfileRepository @Inject constructor(val api:ApiService, val dataStoreToken:DataStoreToken) {


    suspend fun getProviderProfile(id:Int):ViewProfileData{
        Log.d("teet", "getProviderProfile: ${dataStoreToken.getToken()}")
            return  api.viewProviderProfile(id=id,"Bearer ${dataStoreToken.getToken()}")
    }
    suspend fun addToFavorite(id:Int){
        api.addFavorite(id=id,"Bearer ${dataStoreToken.getToken()}")    }
}