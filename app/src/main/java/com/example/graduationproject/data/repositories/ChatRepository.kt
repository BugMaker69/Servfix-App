package com.example.graduationproject.data.repositories

import android.util.Log
import com.example.graduationproject.data.Chat
import com.example.graduationproject.data.ChatContactList
import com.example.graduationproject.data.ChatDetails
import com.example.graduationproject.data.Rate
import com.example.graduationproject.data.SendChatMessage
import com.example.graduationproject.data.retrofit.ApiService
import com.example.graduationproject.utils.DataStoreToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    val apiService: ApiService,
    val dataStoreToken: DataStoreToken
) {

    suspend fun addMessage(id: Int, sendChatMessage: SendChatMessage) {
        apiService.AddChat(
            id = id,
            token = "Bearer ${dataStoreToken.getToken()}",
            content = sendChatMessage
        )
    }

    suspend fun getChatList(): Flow<ChatContactList> {
        return flow {

            when (val response =
                apiService.getChatContactList("Bearer ${dataStoreToken.getToken()}")) {
                is ChatContactList.UserResponse -> {
                    emit(ChatContactList.UserResponse(response.accepted_users))
                }

                is ChatContactList.ProviderResponse -> {
                    emit(ChatContactList.ProviderResponse(response.accepted_providers))
                }
            }
        }
    }

    suspend fun addReview(id: Int, rate: Rate) {
        apiService.addReview(token = "Bearer ${dataStoreToken.getToken()}", id = id, rate = rate)
        Log.d(
            "ool", "addReview: ${
                apiService.addReview(
                    token = "Bearer ${dataStoreToken.getToken()}",
                    id = id,
                    rate = rate
                ).body()?.detail.toString()

            }"
        )
    }

    suspend fun deleteMessage(id: Int) {
        apiService.deleteChat("Bearer ${dataStoreToken.getToken()}", id = id)
    }

    suspend fun determinateChat(id: Int) {
        val determinate = apiService.terminateChat("Bearer ${dataStoreToken.getToken()}", id = id)
        determinate
        Log.d("TAG", "determinateChat: $determinate}")


    }

    suspend fun getChat(id: Int): Flow<List<Chat>> {
        return flow {
            emit(apiService.getChat(id = id, token = "Bearer ${dataStoreToken.getToken()}"))
        }


    }

    suspend fun getChatListDetails(): Flow<List<ChatDetails>> {
        return flow {
            emit(apiService.getChatListDetails(token = "Bearer ${dataStoreToken.getToken()}"))
        }
    }
}
