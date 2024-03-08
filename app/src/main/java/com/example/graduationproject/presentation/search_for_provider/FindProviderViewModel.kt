package com.example.graduationproject.presentation.search_for_provider

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.ServiceProviderCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class FindProviderViewModel : ViewModel() {
    val serviceProvidersList = listOf<ServiceProviderCard>(
        ServiceProviderCard("mohamed"),
        ServiceProviderCard("saied"),
        ServiceProviderCard("yasser"),
        ServiceProviderCard("ziad"),
        ServiceProviderCard("sameh"),
        ServiceProviderCard("ahmed"),
        ServiceProviderCard("omar"),
        ServiceProviderCard("maged")
    )
   var selectedCity = mutableStateOf("")
    var expandend = mutableStateOf(false)
    val testState by mutableStateOf(serviceProvidersList)
    var showDialog = mutableStateOf(false)

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()
    private val _serviceProviders = MutableStateFlow(listOf<ServiceProviderCard>())
    val serviceProviders = searchText.combine(_serviceProviders) { text,_ ->
        if (text.isBlank()) {
            testState
        } else {
            testState.filter {
                it.doesMatchSearchQuery(text)
            }
        }

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), testState)
    fun dismissDialog() {
        showDialog.value = false
    }
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

}