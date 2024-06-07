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
import kotlinx.coroutines.delay
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
        val response =  apiService.acceptPost("Bearer ${dataStoreToken.getToken()}", id)
                    Log.d("first acceptPost Provider Repo", "acceptPost: ${response}")
                    Log.d("first acceptPost Provider Repo1", "acceptPost: ${response.body()}")
                    Log.d("first acceptPost Provider Repo11", "acceptPost: ${response.isSuccessful}")
                    Log.d("first acceptPost Provider Repo12", "acceptPost: ${response.code()}")
                    Log.d("first acceptPost Provider Repo55", "acceptPost: ${response.body()?.details.toString()}")
                    Log.d("first acceptPost Provider Repo2", "acceptPost: ${response.message()}")
                    Log.d("first acceptPost Provider Repo3", "acceptPost: ${response.errorBody()}")
        if (response.code()==200){
            response1.details = "The post accepted successfully"
        }
        else if (response.code()==400){
            response1.details = "This post has already been accepted"
        }
        Log.d("first acceptPost Provider Repo3", "acceptPost: ${response1.details}")
        Log.d("first acceptPost Provider Repo3", "acceptPost: ${response1}")

        return response1

//            return  response1
    }

//    suspend fun acceptPost(id: Int):GeneralPostAccept{
//                var response1 = GeneralPostAccept("This post has already been accepted")
//        apiService.acceptPost("Bearer ${dataStoreToken.getToken()}", id)
//            .enqueue(object : Callback<ResponseBody> {
//                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
////                    Log.d("first acceptPost Provider Repo", "acceptPost: ${response.body()}")
//
//                    response1 = response1.copy( response.message())
//                    if (response.body() != null && response.isSuccessful) {
//                        try {
//
//                            if (response.code() == 200) {
//                                response1 = response1.copy( "The post accepted successfully")
//                    Log.d("first acceptPost Provider Repo", "acceptPost: ${response1}")
//
//                                Log.d(
//                                    "Post Accepted Successfully",
//                                    "onResponse: Post Accepted Successfully ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
//                                )
//
//                            } else {
//                                Log.d(
//                                    "Error",
//                                    "onResponse: Post Accepted Error ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
//                                )
//
//                            }
//                        } catch (e: Exception) {
//                            Log.d(
//                                "Catched Error Post Accepted",
//                                "onResponse: Post Accepted Error  ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
//                            )
//
//                        }
//                    } else {
//                        response1 = response1.copy("This post has already been accepted")
//                        Log.d("onFailure Error Post Accepted", "onResponse: Updated Nothing Happen Why ")
//                        Log.d(
//                            "onFailure Error Post Accepted",
//                            "onResponse: Post Accepted onFailure Error $response1 |||| ${response} ||  ${response.isSuccessful} ||  ${response.message()} ||  ${response.code()} ||  ${response.errorBody()} ||  ${response.headers()} ||  ${response.raw()} || "
//                        )
//                    }
//
//                }
//
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    response1 = response1.copy("This post has already been accepted")
////                    response1 = GeneralPostAccept("This post has already been accepted")
//                    Log.d(
//                        "onFailure Error Updated",
//                        "onResponse: Post Accepted onFailure Error $response1 ||| ${t} ||  ${t.message} ||  ${t.cause} ||  ${t.localizedMessage} ||  ${t.stackTrace} ||  ${t.suppressed} || "
//                    )
//
//                }
//            })
///*        val response = apiService.acceptPost(token = "Bearer ${dataStoreToken.getToken()}", id = id)
//        return response*/
//        Log.d("acceptPost Provider Repo", "acceptPost: ${response1}")
//        return response1
//    }

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


