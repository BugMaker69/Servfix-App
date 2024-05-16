package com.example.graduationproject.presentation.chat

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.graduationproject.data.ChatListItem
import com.example.graduationproject.data.constants.Constant
import com.example.graduationproject.data.repositories.ChatRepository
import com.example.graduationproject.presentation.common.UserType
import com.example.graduationproject.utils.DataStoreToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatContactViewModel @Inject constructor(private val chatRepository: ChatRepository, val dataStoreToken: DataStoreToken) :
    ViewModel() {
    val chatListItems = MutableStateFlow(listOf<ChatListItem>())

    init {
        getChatListItems()
    }

    private fun getChatListItems() {
        viewModelScope.launch(Dispatchers.IO) {
            if (dataStoreToken.getUserType() == UserType.OwnerPerson.name) {
                chatRepository.getChatListForUsers().collect { providers ->
                    chatRepository.getChatListDetails().collect { details ->
                        val items = providers.map { provider ->
                            val detail = details.find { it.name == provider.name }
                            ChatListItem(
                                id = provider.id,
                                name = provider.name,
                                image = Constant.BASE_URL + provider.image,
                                content = detail?.content,
                                unseenMessages = detail?.unseenMessages
                            )
                        }

                        withContext(Dispatchers.Main) {
                            chatListItems.value = items
                        }
                    }
                }
            } else if (dataStoreToken.getUserType() == UserType.HirePerson.name) {
                chatRepository.getChatListForProviders().collect { users ->
                    chatRepository.getChatListDetails().collect { details ->
                        val items = users.map { user ->
                            val detail = details.find { it.name == user.name }
                            ChatListItem(
                                id = user.id,
                                name = user.name,
                                image = user.image,
                                content = detail?.content,
                                unseenMessages = detail?.unseenMessages
                            )
                        }

                        withContext(Dispatchers.Main) {
                            chatListItems.value = items
                        }
                    }
                }
            }
        }
    }
}


//@HiltViewModel
//class ChatContactViewModel @Inject constructor(private val chatRepository: ChatRepository) :
//    ViewModel() {
//    val chatListUsers = MutableStateFlow(listOf<AcceptedUsers>())
//    val chatListProviders = MutableStateFlow(listOf<AcceptedProviders>())
//val chatDetails= MutableStateFlow(listOf<ChatDetails>())
//    val mergedChat =MutableStateFlow(listOf<MergedChatList>())
//
//    init {
//        getList()
//        getChatDetails()
//    }
//
//    fun getChatDetails(){
//        viewModelScope.launch {
//          chatDetails.value=  chatRepository.getChatListDetails()
//
//        }
//    }
//    private fun getList() {
//        try {
//            getChatListProviders()
//        } catch (ex: Exception) {
//            Log.d("erro1", "getList:${ex.message.toString()} ")
//        }
//        try {
//            getChatListUsers()
//        } catch (ex: Exception) {
//            Log.d("erro2", "getList:${ex.message.toString()} ")
//
//        }
//
//
//    }
//
//    private fun getChatListProviders() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = chatRepository.getChatListForUsers()
//            withContext(Dispatchers.Main) {
//                chatListProviders.value = response
//                Log.d("ddd", "getChatListProviders: ${chatListProviders.value.size}")
//            }
//
//        }
//
//    }
//
//    private fun getChatListUsers() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = chatRepository.getChatListForProviders()
//            withContext(Dispatchers.Main) {
//                chatListUsers.value = response
//                Log.d("ddd", "getChatListProviders: ${chatListUsers.value.size}")
//            }
//
//        }
//
//    }
//}