package com.example.graduationproject.presentation.favourite

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.repositories.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavouriteViewModel(): ViewModel() {
    val repo =FavouriteRepository()
    var id by mutableStateOf(0)
var showDialog = mutableStateOf(false)
    private val BASE_URL = "https://p2kjdbr8-8000.uks1.devtunnels.ms/api"

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
                    it.image = BASE_URL + it.image
                }
                providersFavList.value = x
            }
        }
    }



}
