package com.example.graduationproject.presentation.notification

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.AllNotificationItem
import com.example.graduationproject.data.AllNotificationItemSpecific
import com.example.graduationproject.data.AllNotifications
import com.example.graduationproject.data.GeneralPostAccept
import com.example.graduationproject.data.GetPostDataItem
import com.example.graduationproject.data.GetWorksItem
import com.example.graduationproject.data.NewOldPassword
import com.example.graduationproject.data.SpecificNotificationItemByIdItem
import com.example.graduationproject.data.repositories.AddProviderRepository
import com.example.graduationproject.data.repositories.ProviderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    val addProviderRepository: AddProviderRepository,
    val providerRepository: ProviderRepository
) : ViewModel() {

    var getAllNotifications by mutableStateOf(emptyList<AllNotificationItem>())

    var getAllSpecificNotifications by mutableStateOf(emptyList<AllNotificationItemSpecific>())
    var getSpecificNotificationById by mutableStateOf(emptyList<SpecificNotificationItemByIdItem>())

//    var getAllNotificationsForAll by mutableStateOf(AllNotifications(getAllNotifications,getAllSpecificNotifications))

    var getPostById by mutableStateOf(emptyList<GetPostDataItem>())

//    var getPostsForProvider by mutableStateOf(emptyList<GetPostsForProviderItem>())

    var getAllWorks by mutableStateOf(emptyList<GetWorksItem>())

    var acceptPost= GeneralPostAccept("")



    fun getAllNotifications() {
        viewModelScope.launch {
            getAllNotifications = providerRepository.getAllNotifications()
        }
    }


    fun getAllSpecificNotifications() {
        viewModelScope.launch {
            getAllSpecificNotifications = providerRepository.getAllSpecificNotifications()
        }
    }

/*    fun getSpecificNotificationById(id: Int) {
        viewModelScope.launch {
            getSpecificNotificationById = providerRepository.getSpecificNotificationById(id)
        }
    }*/


    /*    fun getAllNotificationsForAll(){
            viewModelScope.launch {
                getAllNotificationsForAll = AllNotifications(getAllNotifications,getAllSpecificNotifications)
            }
        }*/

    fun getPostById(id: Int) {
        viewModelScope.launch {
            getPostById = providerRepository.getPostById(id)
        }
    }

    fun acceptPost(id: Int) {
        viewModelScope.launch {
            acceptPost = providerRepository.acceptPost(id)
        }
    }







}