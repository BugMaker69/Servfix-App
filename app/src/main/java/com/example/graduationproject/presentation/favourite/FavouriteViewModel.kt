package com.example.graduationproject.presentation.favourite

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.constants.Constant
import com.example.graduationproject.data.repositories.FavouriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(val repo:FavouriteRepository): ViewModel() {
    var id by mutableStateOf(0)
var showDialog = mutableStateOf(false)

    var providersFavList = MutableStateFlow(listOf<ReturnedProviderData>())
    init {
        viewModelScope.launch {
            showAllFavourites()
        }
    }

    fun deleteFavourite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteFavourite(id)
            showAllFavourites()
        }
    }

    fun showAllFavourites() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.showAllFavourites().collect { favouritesList ->
                val x = favouritesList.providerFavourite
                x.map {
                    it.image = Constant.BASE_URL + it.image
                }
                providersFavList.value = x
            }
        }
    }



}
