package com.example.graduationproject.presentation.provider_home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.GetPostsForProviderItem
import com.example.graduationproject.data.repositories.ProviderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    val providerRepository: ProviderRepository,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var getPostById by mutableStateOf<GetPostsForProviderItem>(GetPostsForProviderItem("",1, "", false,false,"",1,1))
//    var getPostById by mutableStateOf<SpecificNotificationItemByIdItem>(SpecificNotificationItemByIdItem(1, "", ""))
//    var getPostById by mutableStateOf<GetPostDataItem>(GetPostDataItem("", 1, "", "", ""))

    init {
        val id = savedStateHandle.get<Int>("id")
//        viewModelScope.launch(Dispatchers.Main) {
        if (id != null) {
            getPostById(id)
//            }
        }
    }


    fun getPostById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("getPostById", "getPostById: ${providerRepository.getSpecificNotificationById(id)}")
            val x = providerRepository.getSpecificNotificationById(id)
            withContext(Dispatchers.Main) { getPostById = x }
        }
    }

    fun acceptPostForSpecificProvider(id: Int) {
        viewModelScope.launch {
            providerRepository.acceptPostForSpecificProvider(
                id = id
            )
        }
    }

    fun rejectPostForSpecificProvider(id: Int) {
        viewModelScope.launch {
            providerRepository.rejectPostForSpecificProvider(
                id = id
            )
        }
    }

}