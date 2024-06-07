package com.example.graduationproject.presentation.viewprofile


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.ViewProfileData
import com.example.graduationproject.data.constants.Constant
import com.example.graduationproject.data.repositories.ViewProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import javax.inject.Inject

@SuppressLint("SuspiciousIndentation")
@HiltViewModel
class ViewProfileViewModel @Inject constructor(
    val repo: ViewProfileRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val profile = MutableStateFlow(ViewProfileData())
    var toastMessage = mutableStateOf("")
    var loading by mutableStateOf(true)


    init {
        val id = savedStateHandle.get<Int>("pid")
        if (id != null) {
            getData(id)

        }
    }

    fun addToFavourite() {
        try {
            viewModelScope.launch(Dispatchers.IO) {

                profile.value.provider?.id?.let {
                    Log.d("wow", "addToFavourite: $it")
                    repo.addToFavorite(it.toInt())
                    profile.value = profile.value.copy(isFavourite = true)
                    toastMessage.value = repo.addToFavorite(it)

                }

            }
        } catch (ex: Exception) {
            Log.d("dddddd", "addToFavourite: ${ex.message.toString()}")
        }


    }


    fun getData(id: Int) {
        loading = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val providerProfile = repo.getProviderProfile(id)
                providerProfile.provider?.image =
                    Constant.BASE_URL + providerProfile.provider?.image
                providerProfile.works.forEach { work ->
                    work.image = Constant.BASE_URL + work.image
                }
                withContext(Dispatchers.Main) {
                    profile.value = providerProfile
                    loading = false
                }
            } catch (e: SocketTimeoutException) {
                withContext(Dispatchers.Main) {
                    Log.d("wwwwww", "viewProfile: ${e.message.toString()}")

                    toastMessage.value = "Network request timed out. Please try again."
                    loading = false
                }
            }
        }
    }
}