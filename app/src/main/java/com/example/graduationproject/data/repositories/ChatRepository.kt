package com.example.graduationproject.data.repositories

import android.util.Log
import com.example.graduationproject.data.AcceptedProviders
import com.example.graduationproject.data.AcceptedUsers
import com.example.graduationproject.data.Chat
import com.example.graduationproject.data.ChatDetails
import com.example.graduationproject.data.retrofit.ApiService
import com.example.graduationproject.utils.DataStoreToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(val apiService: ApiService,val dataStoreToken: DataStoreToken) {
    suspend fun  getChatListForUsers():Flow<List<AcceptedProviders>>{
        Log.d("do", "getChatListProviders: ${apiService.getChatListForUsers("Bearer ${dataStoreToken.getToken()}").acceptedProviderUsers.size}")

        return flow {
            emit(        apiService.getChatListForUsers("Bearer ${dataStoreToken.getToken()}").acceptedProviderUsers
            )
        }
    }
    suspend fun  getChatListForProviders():Flow<List<AcceptedUsers>>{
        return flow{
            emit(        apiService.getChatListForProviders("Bearer ${dataStoreToken.getToken()}").accepted_users
            )
        }

    }
    suspend fun getChat(id:Int): Flow<List<Chat>> {
return flow {
    emit( apiService.getChat(id=id, token = "Bearer ${dataStoreToken.getToken()}"))
}


    }
    suspend fun getChatListDetails():Flow<List<ChatDetails>>{
        return flow {
            emit(apiService.getChatListDetails(token = "Bearer ${dataStoreToken.getToken()}"))
        }
    }
}
