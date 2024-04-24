package com.example.graduationproject.presentation.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.RefreshRequest
import com.example.graduationproject.data.retrofit.RetrofitClient
import com.example.graduationproject.utils.DataStoreToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ChatViewModel : ViewModel() {
    val dataStoreToken= DataStoreToken()
    val apiService= RetrofitClient.userRegisterationApiService()


    fun btnClicked() {
        viewModelScope.launch(Dispatchers.Main) {


            val response = apiService.moreToken(RefreshRequest(dataStoreToken.getToken2().toString()))
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {

                    Log.d("mmm", "btnClicked: ${responseBody.access} and last token is ${dataStoreToken.getToken2()}")
                } else {
                    Log.d("nnn", "btnClicked: Response body is null")
                }
            } else {
                Log.d("ccc", "btnClicked: Request failed with status code ${response.code()}, error body: ${response.errorBody()?.string()}")
            }
        }
    }

}