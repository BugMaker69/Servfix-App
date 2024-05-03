package com.example.graduationproject.data.repositories

import android.util.Log
import com.example.graduationproject.data.AllNotification
import com.example.graduationproject.data.GeneralPostAccept
import com.example.graduationproject.data.GetPostData
import com.example.graduationproject.data.GetPostsForProvider
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
        val response = apiService.acceptPost("Bearer ${dataStoreToken.getToken()}", id)
        return response
    }

    suspend fun acceptPostForSpecificProvider(id: Int) {
        apiService.acceptPostForSpecificProvider("Bearer ${dataStoreToken.getToken()}", id)
            .enqueue(object : Callback<ResponseBody> {

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.body() != null && response.isSuccessful) {
                        try {
                            if (response.code() == 201) {
                                Log.d(
                                    "acceptPostForSpecificProvider Successfully",
                                    "onResponse: acceptPostForSpecificProvider ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                                )

                            } else {
                                Log.d(
                                    "Error",
                                    "onResponse: acceptPostForSpecificProvider Error ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                                )

                            }
                        } catch (e: Exception) {
                            Log.d(
                                "Catched Error acceptPostForSpecificProvider",
                                "onResponse: acceptPostForSpecificProvider Catched Error  ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )
                        }
                    } else {
                        Log.d(
                            "onFailure Error acceptPostForSpecificProvider",
                            "onResponse: Updated Nothing Happen Why "
                        )
                        Log.d(
                            "onFailure Error acceptPostForSpecificProvider",
                            "onResponse: acceptPostForSpecificProvider onFailure Error ${response} ||  ${response.isSuccessful} ||  ${response.message()} ||  ${response.code()} ||  ${response.errorBody()} ||  ${response.headers()} ||  ${response.raw()} || "
                        )
                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d(
                        "onFailure Error acceptPostForSpecificProvider",
                        "onResponse: acceptPostForSpecificProvider onFailure Error ${t} ||  ${t.message} ||  ${t.cause} ||  ${t.localizedMessage} ||  ${t.stackTrace} ||  ${t.suppressed} || "
                    )
                }
            })
    }

    suspend fun rejectPostForSpecificProvider(id: Int) {
        apiService.rejectPostForSpecificProvider("Bearer ${dataStoreToken.getToken()}", id)
            .enqueue(object : Callback<ResponseBody> {

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.body() != null && response.isSuccessful) {
                        try {
                            if (response.code() == 201) {
                                Log.d(
                                    "rejectPostForSpecificProvider Successfully",
                                    "onResponse: rejectPostForSpecificProvider ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                                )

                            } else {
                                Log.d(
                                    "Error",
                                    "onResponse: rejectPostForSpecificProvider Error ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                                )

                            }
                        } catch (e: Exception) {
                            Log.d(
                                "Catched Error rejectPostForSpecificProvider",
                                "onResponse: rejectPostForSpecificProvider Catched Error  ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )
                        }
                    } else {
                        Log.d(
                            "onFailure Error rejectPostForSpecificProvider",
                            "onResponse: Updated Nothing Happen Why "
                        )
                        Log.d(
                            "onFailure Error rejectPostForSpecificProvider",
                            "onResponse: rejectPostForSpecificProvider onFailure Error ${response} ||  ${response.isSuccessful} ||  ${response.message()} ||  ${response.code()} ||  ${response.errorBody()} ||  ${response.headers()} ||  ${response.raw()} || "
                        )
                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d(
                        "onFailure Error rejectPostForSpecificProvider",
                        "onResponse: rejectPostForSpecificProvider onFailure Error ${t} ||  ${t.message} ||  ${t.cause} ||  ${t.localizedMessage} ||  ${t.stackTrace} ||  ${t.suppressed} || "
                    )
                }
            })
    }


}


