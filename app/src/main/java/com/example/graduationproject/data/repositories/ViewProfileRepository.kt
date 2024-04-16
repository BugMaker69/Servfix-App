package com.example.graduationproject.data.repositories


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.example.graduationproject.data.FavouritesList
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.ViewProfileData
import com.example.graduationproject.data.retrofit.RetrofitClient
import com.example.graduationproject.utils.DataStoreToken
import kotlinx.coroutines.flow.Flow

class ViewProfileRepository() {
 val dataStoreToken=DataStoreToken()

    val api=RetrofitClient.userRegisterationApiService()
//    suspend  fun showAllFavourites(): Flow<FavouritesList> {
//
//        return api.showFavourites("Bearer ${dataStoreToken.getToken()}")
//    }
    suspend fun getProviderProfile(id:Int):ViewProfileData{
        Log.d("teet", "getProviderProfile: ${dataStoreToken.getToken()}")
            return  api.viewProviderProfile(id=id,"Bearer ${dataStoreToken.getToken()}")
    }
    suspend fun addToFavorite(id:Int){
        api.addFavorite(id=id,"Bearer ${dataStoreToken.getToken()}")    }
}