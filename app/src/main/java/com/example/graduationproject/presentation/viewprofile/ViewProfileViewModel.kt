package com.example.graduationproject.presentation.viewprofile


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.ViewProfileData
import com.example.graduationproject.data.constants.Constant
import com.example.graduationproject.data.repositories.ViewProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@SuppressLint("SuspiciousIndentation")
@HiltViewModel
class ViewProfileViewModel @Inject constructor(
    val repo: ViewProfileRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val profile = MutableStateFlow(ViewProfileData())
    var toastMessage = mutableStateOf("")
   // val profile = MutableStateFlow(ViewProfileData())


    init {
        val id = savedStateHandle.get<Int>("pid")
            if (id != null) {
                getData(id)



        }
    }

    fun addToFavourite() {
        viewModelScope.launch(Dispatchers.IO) {
            profile.value.provider?.id?.let {
                Log.d("wow", "addToFavourite: $it")
                repo.addToFavorite(it.toInt())
                toastMessage.value=repo.addToFavorite(it)
                withContext(Dispatchers.Main){
                    profile.value = profile.value.copy(isFavourite = true)
                }
            }

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
            withContext(Dispatchers.IO) { profile.value = providerProfile }


        }
    }

}