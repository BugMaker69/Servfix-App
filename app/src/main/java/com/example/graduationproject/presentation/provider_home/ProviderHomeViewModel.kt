package com.example.graduationproject.presentation.provider_home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.GetPostsForProviderItem
import com.example.graduationproject.data.repositories.ProviderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProviderHomeViewModel @Inject constructor(
    val providerRepository: ProviderRepository
) : ViewModel() {
private val _isLoading=MutableStateFlow(false)
    val isLoading=_isLoading.asStateFlow()
init {
    loadStuff()
}
    fun loadStuff(){
        viewModelScope.launch {
            _isLoading.value=true
            delay(3000L)
            _isLoading.value=false
        }
    }
    var getPostsForProvider by mutableStateOf(emptyList<GetPostsForProviderItem>())

    fun getPostsForProvider() {
        viewModelScope.launch {
            getPostsForProvider = providerRepository.getPostsForProvider()
        }
    }

}