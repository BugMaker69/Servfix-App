package com.example.graduationproject.presentation.notification

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.AllNotificationItem
import com.example.graduationproject.data.GetPostDataItem
import com.example.graduationproject.data.GetWorksItem
import com.example.graduationproject.data.NewOldPassword
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

    var getPostById by mutableStateOf(emptyList<GetPostDataItem>())

//    var getPostsForProvider by mutableStateOf(emptyList<GetPostsForProviderItem>())

    var getAllWorks by mutableStateOf(emptyList<GetWorksItem>())


    var oldPassword by mutableStateOf("")
    var newPassword by mutableStateOf("")


    fun onOldPasswordChange(oldPassword1: String) {
        oldPassword = oldPassword1
    }

    fun onNewPasswordChange(newPassword1: String) {
        newPassword = newPassword1
    }

    fun getAllNotifications() {
        viewModelScope.launch {
            getAllNotifications = providerRepository.getAllNotifications()
        }
    }

    fun getPostById(id: Int) {
        viewModelScope.launch {
            getPostById = providerRepository.getPostById(id)
        }
    }

    fun acceptPost(id: Int) {
        viewModelScope.launch {
            providerRepository.acceptPost(id)
        }
    }


    fun changePassword(newOldPassword: NewOldPassword) {
        viewModelScope.launch {
            addProviderRepository.changePassword(newOldPassword)
        }
    }

    fun getAllWorks() {
        viewModelScope.launch {
            addProviderRepository.getAllWorks()
        }
    }

    fun deleteWork(id: Int) {
        viewModelScope.launch {
            addProviderRepository.deleteWork(id)
        }
    }

    fun addWork(image: Uri) {
        viewModelScope.launch {
            addProviderRepository.addWork(image)
        }
    }


}