package com.example.graduationproject.presentation.search_for_provider

import ProviderPagingSource
import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.repositories.FindProviderSearchReopsitory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("SuspiciousIndentation")
@HiltViewModel
class FindProviderViewModel @Inject constructor(
    private val findProviderRepo: FindProviderSearchReopsitory,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var loading by mutableStateOf(true)
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
    private var _lista = MutableStateFlow<PagingData<ReturnedProviderData>>(PagingData.empty())
    val lista: StateFlow<PagingData<ReturnedProviderData>> = _lista
    var rating by mutableStateOf(0)
    var serviceName by mutableStateOf("")
    var selectedCity = mutableStateOf("")
    var expandend = mutableStateOf(false)
    var showRemoveIcon = mutableStateOf(false)
    var itemId by mutableStateOf(0)
    var showDialog = mutableStateOf(false)
    init {
        serviceName = savedStateHandle.get<String>("serviceName") ?: ""
        itemId = savedStateHandle.get<Int>("id") ?: 0
        viewModelScope.launch {

searchProvider()
        }


    }
    fun searchProvider(){
loading=true
        viewModelScope.launch(Dispatchers.IO) {

            searchText.collectLatest { query ->
                _lista.value = Pager(
                    pagingSourceFactory = { ProviderPagingSource(findProviderRepo, itemId, query,selectedCity.value,rating.toInt()) },
                    config = PagingConfig(pageSize = 12)
                ).flow.cachedIn(viewModelScope).first()
                loading=false
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text

    }

    fun showRemoveIcon() {
        if (selectedCity.value.isNotBlank() || rating != 0) {
            showRemoveIcon.value = true
        }
    }
    fun categoryFilteration() {
     searchProvider()
    }

    fun removeFilter() {
        _searchText.value = ""
        selectedCity.value = ""
        rating = 0
        searchProvider()
        showRemoveIcon.value = false

    }

    fun changeExpandedValue() {
        expandend.value = !expandend.value
    }

    fun dismissDialog() {
        showDialog.value = false
    }

}

//    fun categoryFilteration() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _serviceProviders.value = _originalServiceProviders.value.filter {
//                it.city == selectedCity.value && it.ratings.toDouble() <= rating.toDouble() || it.ratings.toDouble() <= rating.toDouble()
//
//            }
//            categoryFiltered.value = _serviceProviders.value
//        }
//    }
//    private fun listFilteration() {
//        viewModelScope.launch(Dispatchers.Main) {
//            searchText.combine(_originalServiceProviders) { text, _ ->
//                if (text.isBlank() && selectedCity.value.isBlank()) {
//                    _originalServiceProviders.value
//                } else if (text.isBlank() && selectedCity.value.isBlank() || text.isBlank() && selectedCity.value.isNotBlank()) {
//                    categoryFiltered.value
//
//                } else {
//                    _originalServiceProviders.value.filter {
//                        it.username.contains(text)
//                    }
//                }
//            }.collect { filteredList ->
//                _serviceProviders.value = filteredList
//
//            }
//        }
//    }

//    private fun getSearchProvidersList(id: Int) {
//        try {
//            viewModelScope.launch(Dispatchers.IO) {
//                val list = findProviderRepo.getProvidersList(id)
//                list.forEach {
//                    it.image = BASE_URL + it.image
//                }
//                _originalServiceProviders.value = list
//                _serviceProviders.value = list
//            }
//        } catch (e: Exception) {
//            Log.d("test", "getSearchProvidersList: ${e.toString()}")
//        }
//    }

