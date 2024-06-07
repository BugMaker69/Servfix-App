package com.example.graduationproject.presentation.favourite

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(val repo:FavouriteRepository): ViewModel() {
    var id by mutableIntStateOf(0)
    var loading by mutableStateOf(true)

    var providersFavList = MutableStateFlow(listOf<ReturnedProviderData>())
    init {
            showAllFavourites()

    }

    fun deleteFavourite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteFavourite(id)
            showAllFavourites()
        }
    }

    fun showAllFavourites() {
        loading = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.showAllFavourites().collect { favouritesList ->
                    val x = favouritesList.providerFavourite
                    x.map {
                        it.image = Constant.BASE_URL + it.image
                    }
                    withContext(Dispatchers.Main) {
                        providersFavList.value = x
                        loading = false
                    }
                }
            } catch (e: SocketTimeoutException) {
                Log.d("wwwwww", "showAllFavourites: ${e.message.toString()}")
                withContext(Dispatchers.Main) {
                    loading = false
                }
            }
        }

    }
    }

