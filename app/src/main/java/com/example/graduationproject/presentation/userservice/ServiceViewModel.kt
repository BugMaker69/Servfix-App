package com.example.graduationproject.presentation.userservice

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.data.Services
import com.example.graduationproject.data.repositories.AddProviderRepository
import com.example.graduationproject.data.repositories.UserServicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(    val addProviderRepository :AddProviderRepository
                                               ,val userServicesRep: UserServicesRepository): ViewModel() {
    var servicesState = mutableStateOf(emptyList<Services>())

    init {
        getServicesList()
    }

    private val BASE_URL = "https://p2kjdbr8-8000.uks1.devtunnels.ms/api"

    fun getServicesList() {
        try {


            viewModelScope.launch(Dispatchers.IO) {
                val services = userServicesRep.getServicesList()
                services.forEach { service ->
                    service.image = BASE_URL + service.image

                }
                servicesState.value = services
                Log.d("pppp", ": ${servicesState.value}")
            }
        }catch (e:Exception){
            Log.d("Test", "getServicesList: ${e.message.toString()}")
        }



    }
    //  Share Problem   Handle Text

    var expandDropDownMenu by mutableStateOf(false)
    var expandDropDownMenuLocation by mutableStateOf(false)
    var postText by mutableStateOf("")
    val maxPostTextLength = 500

    fun onPostTextChange(postText1: String) {
        if (postText1.length < maxPostTextLength) {
            postText = postText1
        }
    }


    //  DropdownMenu Of Service Inside Share Problem
    var selectedSentToServiceIndex by mutableStateOf(-1)
    var selectedSentToServiceValue by mutableStateOf("")
    var selectedSentToLocationIndex by mutableStateOf(-1)
    var selectedSentToLocationValue by mutableStateOf("")
    var textfieldSentToServiceSize by mutableStateOf(Size.Zero)


    //  Share Problem   Handle Photos

    val isLoading = mutableStateOf(false)
//    val maxImages = 5
    val launchCamera = mutableStateOf(false)
    var imageUri by mutableStateOf<Uri?>(null)

    val permissionDenied = mutableStateOf(false)
    //val imageMap = mutableStateMapOf<String, Bitmap>()


    //    fun addImage(id: String, uri: Uri) {
//        if (imageMap.size < maxImages) {
//            imageMap[id] = uri
//        }
//    }
//
//    fun removeImage(id: String) {
//        imageMap.remove(id)
//    }
    fun removeImage() {
        imageUri=null
    }
    fun startLoading() {
        isLoading.value = true
    }

    fun stopLoading() {
        isLoading.value = false
    }


//    fun handleGalleryResult(context: Context, uris: List<Uri>) {
//        uris.mapNotNull { uri ->
//            context.contentResolver.openInputStream(uri)?.use { inputStream ->
//                Pair(uri.toString(), BitmapFactory.decodeStream(inputStream))
//            }
//        }.forEach { (id, bitmap) ->
//            addImage(id, bitmap)
//        }
//        stopLoading()
//    }

    fun handleCameraResult(uri: Uri?) {
        uri?.let { imageUri = it }
        stopLoading()
    }

    fun handlePermissionResult(isGranted: Boolean) {
        if (isGranted) {
            startLoading()
            launchCamera.value = true
        } else {
            permissionDenied.value = true
        }
    }

    fun shareCreatePost(){
        viewModelScope.launch {
            Log.d("shareCreatePost Token ", "shareCreatePost: ${addProviderRepository.dataStoreToken.getToken()} || ")
            Log.d("shareCreatePost Data ", "shareCreatePost: ${selectedSentToLocationValue} ||  ${selectedSentToServiceValue} ||   ${postText} ||   ${imageUri} ||  ")
            addProviderRepository.shareCreatePost(
                token = addProviderRepository.dataStoreToken.getToken(),
                city = selectedSentToLocationValue,
                service_name = selectedSentToServiceValue,
                problem_description = postText,
                image = imageUri!!
            )
        }
    }

}