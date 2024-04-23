package com.example.graduationproject.presentation.notification

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.AllNotificationItem
import com.example.graduationproject.data.GetPostDataItem
import com.example.graduationproject.data.GetPostsForProviderItem
import com.example.graduationproject.data.GetWorksItem
import com.example.graduationproject.data.NewOldPassword
import com.example.graduationproject.data.repositories.AddProviderRepository
import kotlinx.coroutines.launch

class NotificationViewModel : ViewModel() {
    private val addProviderRepository = AddProviderRepository()

    var getAllNotifications by mutableStateOf(emptyList<AllNotificationItem>())

    var getPostById by mutableStateOf(emptyList<GetPostDataItem>())

    var getPostsForProvider by mutableStateOf(emptyList<GetPostsForProviderItem>())

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
            getAllNotifications = addProviderRepository.getAllNotifications()
        }
    }

    fun getPostById(id: Int) {
        viewModelScope.launch {
            getPostById = addProviderRepository.getPostById(id)
        }
    }

    fun getPostsForProvider() {
        viewModelScope.launch {
            getPostsForProvider = addProviderRepository.getPostsForProvider()
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