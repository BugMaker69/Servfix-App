package com.example.graduationproject.presentation.search_for_provider

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
    var showRemoveIcon= mutableStateOf(false)
    var showDialog = mutableStateOf(false)
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
    val categoryFiltered= MutableStateFlow(listOf<ReturnedProviderData>())
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()
     var _serviceProviders = MutableStateFlow(listOf<ReturnedProviderData>())
    private val _originalServiceProviders = MutableStateFlow(listOf<ReturnedProviderData>())

    var rating by mutableIntStateOf(0)
fun showRemoveIcon(){
    if (selectedCity.value.isNotBlank()||rating!=0){
        showRemoveIcon.value=true

    }
}
    fun removeFilter(){
        _serviceProviders.value=_originalServiceProviders.value
        selectedCity.value=""
    }
    fun changeExpandedValue(){
        expandend.value = ! expandend.value
    }

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


    fun categoryFilteration() {
        viewModelScope.launch(Dispatchers.IO) {
            _serviceProviders.value = _originalServiceProviders.value.filter {
                it.city == selectedCity.value && it.ratings.toDouble() <= rating.toDouble()||it.ratings.toDouble() <= rating.toDouble()

            }
            categoryFiltered.value=_serviceProviders.value
        }
    }


    private fun listFilteration() {
        viewModelScope.launch(Dispatchers.Main) {
            searchText.combine(_originalServiceProviders) { text, _ ->
                if (text.isBlank()&&selectedCity.value.isBlank()) {
                    _originalServiceProviders.value
                }else if (text.isBlank()&&selectedCity.value.isBlank()||text.isBlank()&&selectedCity.value.isNotBlank()){
                    categoryFiltered.value

                }
                else {
                    _originalServiceProviders.value.filter {
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
    private fun getSearchProvidersList(id: Int) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val list = findProviderRepo.getProvidersList(id)
                list.forEach {
                    it.image = BASE_URL + it.image
                }
                _originalServiceProviders.value = list
                _serviceProviders.value = list
            }
        } catch (e: Exception) {
            Log.d("test", "getSearchProvidersList: ${e.toString()}")
        }
    }

}