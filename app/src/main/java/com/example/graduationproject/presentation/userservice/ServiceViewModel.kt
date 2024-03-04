package com.example.graduationproject.presentation.userservice

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import java.util.UUID

class ServiceViewModel : ViewModel() {

    //  Share Problem   Handle Text

    var expandDropDownMenu by mutableStateOf(false)
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
    var textfieldSentToServiceSize by mutableStateOf(Size.Zero)






    //  Share Problem   Handle Photos

    val isLoading = mutableStateOf(false)
    val maxImages = 5
    val launchCamera = mutableStateOf(false)
    val permissionDenied = mutableStateOf(false)
    val imageMap = mutableStateMapOf<String, Bitmap>()


    fun addImage(id: String, bitmap: Bitmap) {
        if (imageMap.size < maxImages) {
            imageMap[id] = bitmap
        }
    }

    fun removeImage(id: String) {
        imageMap.remove(id)
    }

    fun startLoading() {
        isLoading.value = true
    }

    fun stopLoading() {
        isLoading.value = false
    }


    fun handleGalleryResult(context: Context, uris: List<Uri>) {
        uris.mapNotNull { uri ->
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                Pair(uri.toString(), BitmapFactory.decodeStream(inputStream))
            }
        }.forEach { (id, bitmap) ->
            addImage(id, bitmap)
        }
        stopLoading()
    }

    fun handleCameraResult(bitmap: Bitmap?) {
        bitmap?.let { addImage(UUID.randomUUID().toString(), it) }
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

}