package com.example.graduationproject.presentation.viewprofile


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.ViewProfileData
import com.example.graduationproject.data.constants.Constant
import com.example.graduationproject.data.repositories.ViewProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ViewProfileViewModel @Inject constructor(
    val repo: ViewProfileRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val profile = mutableStateOf(ViewProfileData())
    val isFavourite = mutableStateOf(false)

    init {
        val id = savedStateHandle.get<Int>("pid")
        viewModelScope.launch {
            if (id != null) {
                getData(id)

            }

        }
    }

    fun addToFavourite() {
        viewModelScope.launch(Dispatchers.IO) {
            profile.value.provider?.id?.let { repo.addToFavorite(it.toInt()) }
        }

    }

    fun getData(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val providerProfile = repo.getProviderProfile(id)
            //    val fav =repo.showAllFavourites()

            //    fav.providerFavourite.contains(providerProfile)

            providerProfile.provider?.image = Constant.BASE_URL + providerProfile.provider?.image
//            providerProfile.works = providerProfile.works.filter { it.images.isNotEmpty() } as ArrayList<Works>
//                        providerProfile.works.forEach { work ->
//                work.images.f+orEach{
//                   it.image =  BASE_URL+it.image
//
//                }
//            }
            providerProfile.works.forEach { work ->
                work.image = Constant.BASE_URL + work.image

            }
//            providerProfile.works.forEach { work ->
//                work.images.forEach{
//                   it.image =  BASE_URL+it.image
//
//                }
//            }
            withContext(Dispatchers.Main) { profile.value = providerProfile }


        }
    }

}