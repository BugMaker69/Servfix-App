package com.example.graduationproject.presentation.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.AcceptedProviders
import com.example.graduationproject.data.AcceptedUsers
import com.example.graduationproject.data.repositories.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatContactViewModel @Inject constructor(private val chatRepository: ChatRepository  ):ViewModel() {
    val chatListUsers =  MutableStateFlow(listOf<AcceptedUsers>())
    val chatListProviders =  MutableStateFlow(listOf<AcceptedProviders>())


    init {
          getList()
    }
    private fun getList(){
        try {
            getChatListProviders()
        }catch (ex:Exception){
            Log.d("erro1", "getList:${ex.message.toString()} ")
        }
        try {
            getChatListUsers()
        }catch(ex:Exception) {
            Log.d("erro2", "getList:${ex.message.toString()} ")

        }



    }
     private fun getChatListProviders(){
        viewModelScope.launch (Dispatchers.IO){
           val response= chatRepository.getChatListForUsers()
            withContext(Dispatchers.Main){
                chatListProviders.value=response
                Log.d("ddd", "getChatListProviders: ${chatListProviders.value.size}")
            }

        }

    }
    private fun getChatListUsers(){
        viewModelScope.launch (Dispatchers.IO){
            val response= chatRepository.getChatListForProviders()
            withContext(Dispatchers.Main){
                chatListUsers.value=response
                Log.d("ddd", "getChatListProviders: ${chatListUsers.value.size}")
            }

        }

    }
}