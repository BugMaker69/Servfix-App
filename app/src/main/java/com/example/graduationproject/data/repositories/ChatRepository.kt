package com.example.graduationproject.data.repositories

import android.util.Log
import com.example.graduationproject.data.AcceptedProviders
import com.example.graduationproject.data.AcceptedUsers
import com.example.graduationproject.data.retrofit.ApiService
import com.example.graduationproject.utils.DataStoreToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(val apiService: ApiService,val dataStoreToken: DataStoreToken) {
    suspend fun  getChatListForUsers():List<AcceptedProviders>{
        Log.d("do", "getChatListProviders: ${apiService.getChatListForUsers("Bearer ${dataStoreToken.getToken()}").acceptedProviderUsers.size}")

        return apiService.getChatListForUsers("Bearer ${dataStoreToken.getToken()}").acceptedProviderUsers
    }
    suspend fun  getChatListForProviders():List<AcceptedUsers>{
        return apiService.getChatListForProviders("Bearer ${dataStoreToken.getToken()}").accepted_users

    }
}
