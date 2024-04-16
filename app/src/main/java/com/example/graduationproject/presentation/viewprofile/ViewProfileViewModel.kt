package com.example.graduationproject.presentation.viewprofile


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.repositories.ViewProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewProfileViewModel(): ViewModel() {
    val repo : ViewProfileRepository =ViewProfileRepository()

    val name= mutableStateOf("")

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            val x = repo.getProviderProfile(2).provider!!.username
            Log.d("teet", "getData: $x")

                name.value = x!!

        }
    }

}