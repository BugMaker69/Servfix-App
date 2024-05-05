package com.example.graduationproject.presentation.common.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.NewOldPassword
import com.example.graduationproject.data.repositories.AddProviderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(val repo: AddProviderRepository) : ViewModel() {
    var oldPassword by mutableStateOf("")
    var newPassword by mutableStateOf("")
    var confirmedPassword by mutableStateOf("")

    fun confirmedPasswordChange(password: String) {
        confirmedPassword = password
    }

    fun onOldPasswordChange(oldPassword1: String) {
        oldPassword = oldPassword1
    }

    fun changePassword(newOldPassword: NewOldPassword) {
        viewModelScope.launch {
            repo.changePassword(newOldPassword)
        }
    }

    fun onNewPasswordChange(newPassword1: String) {
        newPassword = newPassword1
    }

//    fun deleteAccount(password: String) {
//        viewModelScope.launch {
//            repo.deleteAccount(password = password)
//        }
//    }
}