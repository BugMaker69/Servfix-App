package com.example.graduationproject.presentation.provider_home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.GetPostsForProviderItem
import com.example.graduationproject.data.repositories.ProviderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProviderHomeViewModel @Inject constructor(
    val providerRepository: ProviderRepository
) : ViewModel() {

    var getPostsForProvider by mutableStateOf(emptyList<GetPostsForProviderItem>())

    fun getPostsForProvider() {
        viewModelScope.launch {
            getPostsForProvider = providerRepository.getPostsForProvider()
        }
    }

}