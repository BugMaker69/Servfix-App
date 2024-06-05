package com.example.graduationproject.data.repositories

import android.util.Log
import com.example.graduationproject.data.AllNotification
import com.example.graduationproject.data.AllNotificationSpecific
import com.example.graduationproject.data.GeneralPostAccept
import com.example.graduationproject.data.GetPostData
import com.example.graduationproject.data.GetPostsForProvider
import com.example.graduationproject.data.GetPostsForProviderItem
import com.example.graduationproject.data.PostStatus
import com.example.graduationproject.data.SpecificNotificationItemById
import com.example.graduationproject.data.retrofit.ApiService
import com.example.graduationproject.utils.DataStoreToken
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ProviderRepository @Inject constructor(
    val dataStoreToken: DataStoreToken,
    val apiService: ApiService,
) {

    suspend fun getAllNotifications(): AllNotification {
        val response = apiService.getAllNotifications("Bearer ${dataStoreToken.getToken()}")
        return response
    }

    suspend fun getAllSpecificNotifications(): AllNotificationSpecific {
        val response = apiService.getAllSpecificNotifications("Bearer ${dataStoreToken.getToken()}")
        return response
    }

    suspend fun getSpecificNotificationById(id: Int): GetPostsForProviderItem {
        val response = apiService.getSpecificNotificationById("Bearer ${dataStoreToken.getToken()}",id)
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

    suspend fun acceptPost(id: Int):GeneralPostAccept{
                var response1 = GeneralPostAccept("")
        apiService.acceptPost("Bearer ${dataStoreToken.getToken()}", id)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    response1 = GeneralPostAccept( response.message())
                    if (response.body() != null && response.isSuccessful) {
                        try {

                            if (response.code() == 201) {
                                Log.d(
                                    "Post Accepted Successfully",
                                    "onResponse: Post Accepted Successfully ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                                )

                            } else {
                                Log.d(
                                    "Error",
                                    "onResponse: Post Accepted Error ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                                )

                            }
                        } catch (e: Exception) {
                            Log.d(
                                "Catched Error Post Accepted",
                                "onResponse: Post Accepted Error  ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )

                        }
                    } else {
                        Log.d("onFailure Error Post Accepted", "onResponse: Updated Nothing Happen Why ")
                        Log.d(
                            "onFailure Error Post Accepted",
                            "onResponse: Post Accepted onFailure Error ${response} ||  ${response.isSuccessful} ||  ${response.message()} ||  ${response.code()} ||  ${response.errorBody()} ||  ${response.headers()} ||  ${response.raw()} || "
                        )
                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    response1 = GeneralPostAccept(t.message.toString())
                    Log.d(
                        "onFailure Error Updated",
                        "onResponse: Post Accepted onFailure Error ${t} ||  ${t.message} ||  ${t.cause} ||  ${t.localizedMessage} ||  ${t.stackTrace} ||  ${t.suppressed} || "
                    )

                }
            })
/*        val response = apiService.acceptPost(token = "Bearer ${dataStoreToken.getToken()}", id = id)
        return response*/
        return response1
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


