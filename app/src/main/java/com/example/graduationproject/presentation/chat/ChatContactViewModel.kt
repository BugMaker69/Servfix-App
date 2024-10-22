package com.example.graduationproject.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.ChatContactList
import com.example.graduationproject.data.ChatListItem
import com.example.graduationproject.data.constants.Constant
import com.example.graduationproject.data.repositories.ChatRepository
import com.example.graduationproject.presentation.common.UserType
import com.example.graduationproject.utils.DataStoreToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatContactViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    val dataStoreToken: DataStoreToken
) :
    ViewModel() {
    val chatListItems = MutableStateFlow(listOf<ChatListItem>())

    init {
        getChatListItems()
    }

    fun getChatListItems() {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                if (dataStoreToken.getUserType() == UserType.OwnerPerson.name) {
                    chatRepository.getChatList().collect { response ->
                        when (response) {
                            is ChatContactList.ProviderResponse -> {
                                chatRepository.getChatListDetails().collect { details ->
                                    val items = response.accepted_providers.map { provider ->
                                        val detail = details.find { it.name == provider.name }
                                        ChatListItem(
                                            terminate_id = provider.terminate_id,
                                            id = provider.id,
                                            name = provider.name,
                                            image = Constant.BASE_URL + provider.image,
                                            content = detail?.content,
                                            unseenMessages = detail?.unseenMessages,
                                            phone = provider.phone

                                        )

                                    }
                                    withContext(Dispatchers.Main) {
                                        chatListItems.value = items
                                    }

                                }


                            }

                            else -> {}
                        }
                    }
                } else if (dataStoreToken.getUserType() == UserType.HirePerson.name) {
                    chatRepository.getChatList().collect { response ->
                        when (response) {
                            is ChatContactList.UserResponse -> {
                                chatRepository.getChatListDetails().collect { details ->
                                    val items = response.accepted_users.map { user ->
                                        val detail = details.find { it.name == user.name }
                                        ChatListItem(
                                            terminate_id = user.terminate_id,
                                            id = user.id,
                                            name = user.name,
                                            image = user.image,
                                            content = detail?.content,
                                            unseenMessages = detail?.unseenMessages,
                                            phone=null
                                        )
                                    }
                                    withContext(Dispatchers.Main) {
                                        chatListItems.value = items
                                    }
                                }
                            }
                           else -> {

                            }
                        }
                    }
                }
                delay(4000)
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