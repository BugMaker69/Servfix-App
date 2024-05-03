package com.example.graduationproject.data.repositories

import android.util.Log
import com.example.graduationproject.data.AllNotification
import com.example.graduationproject.data.GeneralPostAccept
import com.example.graduationproject.data.GetPostData
import com.example.graduationproject.data.GetPostsForProvider
import com.example.graduationproject.data.PostStatus
import com.example.graduationproject.data.retrofit.ApiService
import com.example.graduationproject.utils.DataStoreToken
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ProviderRepository @Inject constructor(
    val dataStoreToken: DataStoreToken,
    val apiService: ApiService
) {

    suspend fun getAllNotifications(): AllNotification {
        val response = apiService.getAllNotifications("Bearer ${dataStoreToken.getToken()}")
        return response
    }

    suspend fun getPostById(id: Int): GetPostData {
        val response = apiService.getPostById("Bearer ${dataStoreToken.getToken()}", id)
        return response
    }

    suspend fun getPostsForProvider(): GetPostsForProvider {
        val response = apiService.getPostsForProvider("Bearer ${dataStoreToken.getToken()}")
        return response
    }

    suspend fun acceptPost(id: Int): GeneralPostAccept {
        val response = apiService.acceptPost(token = "Bearer ${dataStoreToken.getToken()}", id = id)
        return response
    }

    suspend fun acceptPostForSpecificProvider(id: Int): PostStatus {
        val response = apiService.acceptPostForSpecificProvider(
            token = "Bearer ${dataStoreToken.getToken()}",
            post_id = id
        )
        Log.d("acceptPostForSpecificProvider", "acceptPostForSpecificProvider: $response")
        return response
    }

    suspend fun rejectPostForSpecificProvider(id: Int): PostStatus {
        val response = apiService.rejectPostForSpecificProvider(
            token = "Bearer ${dataStoreToken.getToken()}",
            post_id = id
        )
        Log.d("rejectPostForSpecificProvider", "rejectPostForSpecificProvider: $response")
        return response
    }

}


