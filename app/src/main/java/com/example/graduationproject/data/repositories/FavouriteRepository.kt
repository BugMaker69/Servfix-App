package com.example.graduationproject.data.repositories

import com.example.graduationproject.data.FavouritesList
import com.example.graduationproject.data.retrofit.ApiService
import com.example.graduationproject.utils.DataStoreToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavouriteRepository @Inject constructor(val apiService:ApiService,val dataStoreToken: DataStoreToken){

    suspend fun showAllFavourites(): Flow<FavouritesList> {
        return flow {
            emit(apiService.showFavourites("Bearer ${dataStoreToken.getToken()}"))
        }
    }

    suspend fun  deleteFavourite(id:Int){
        apiService.deleteFavorite(id=id, token = "Bearer ${dataStoreToken.getToken()}")
    }

}