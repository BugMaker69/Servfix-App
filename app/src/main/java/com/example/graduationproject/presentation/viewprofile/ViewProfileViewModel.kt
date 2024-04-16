package com.example.graduationproject.presentation.viewprofile


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.Provider
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.ViewProfileData
import com.example.graduationproject.data.repositories.ViewProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewProfileViewModel(savedStateHandle2: SavedStateHandle): ViewModel() {
    val repo : ViewProfileRepository =ViewProfileRepository()

    val profile= mutableStateOf(Provider())
    val isFavourite= mutableStateOf(false)
    private val BASE_URL = "https://p2kjdbr8-8000.uks1.devtunnels.ms/api"

    init {
        val id = savedStateHandle2.get<Int>("pid")
        viewModelScope.launch {
            if (id != null) {
                getData(id)

            }

        }
    }
    fun addToFavourite(){
        viewModelScope.launch (Dispatchers.IO){
            profile.value.id?.let { repo.addToFavorite(it.toInt()) }
        }

    }

    fun getData(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val providerProfile = repo.getProviderProfile(id).provider!!
        //    val fav =repo.showAllFavourites()

        //    fav.providerFavourite.contains(providerProfile)

                providerProfile.image=BASE_URL+providerProfile.image

                profile.value=providerProfile


        }
    }

}