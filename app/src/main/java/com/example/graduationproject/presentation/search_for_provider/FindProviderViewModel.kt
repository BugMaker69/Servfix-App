package com.example.graduationproject.presentation.search_for_provider

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.repositories.FindProviderSearchReopsitory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
class FindProviderViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val findProviderRepo:FindProviderSearchReopsitory= FindProviderSearchReopsitory()
var serviceName by mutableStateOf("")
   var selectedCity = mutableStateOf("")
    var expandend = mutableStateOf(false)
    private var listState = MutableStateFlow(emptyList<ReturnedProviderData>())
    var showDialog = mutableStateOf(false)

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()
     var _serviceProviders = MutableStateFlow(listOf<ReturnedProviderData>())


    init {
      val id= savedStateHandle.get<Int>("id")
         serviceName= savedStateHandle.get<String>("serviceName").toString()
        Log.d("tesstt", "$id: ")
        if (id != null) {
            getSearchProvidersList(id)
        }
        listFilteration()
    }
    private val BASE_URL = "https://p2kjdbr8-8000.uks1.devtunnels.ms/api"

    fun listFilteration() {
        viewModelScope.launch(Dispatchers.IO) {
            searchText.combine(listState) { text, _ ->
                if (text.isBlank()) {
                    listState.value
                } else {
                    listState.value.filter {
                        it.username.contains(text)
                    }
                }
            }.collect { filteredList ->
                _serviceProviders.value = filteredList
            }
        }
    }

    fun dismissDialog() {
        showDialog.value = false
    }
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
   private fun getSearchProvidersList(id:Int){
        viewModelScope.launch (Dispatchers.IO){
        val list = findProviderRepo.getProvidersList(id)
            list.forEach {
                it.image= BASE_URL+it.image
            }
            listState.value=list
            if (listState.value.isNotEmpty()){
//                category.value=listState.value.get(0).profession
            }
        }
    }

}