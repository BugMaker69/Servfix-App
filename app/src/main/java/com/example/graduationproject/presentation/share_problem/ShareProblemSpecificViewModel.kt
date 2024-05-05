package com.example.graduationproject.presentation.share_problem

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.MyApplication
import com.example.graduationproject.data.repositories.AddProviderRepository
import com.example.graduationproject.utils.FileUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class ShareProblemSpecificViewModel @Inject constructor(
    val addProviderRepository: AddProviderRepository,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var providerName by mutableStateOf("")
    var providerid by mutableStateOf(0)

    init {
        providerName = savedStateHandle.get<String>("providerName") ?: ""
        providerid = savedStateHandle.get<Int>("pid") ?: 0
    }


    var postText by mutableStateOf("")
    val maxPostTextLength = 500

    fun onPostTextChange(postText1: String) {
        if (postText1.length < maxPostTextLength) {
            postText = postText1
        }
    }

    val isLoading = mutableStateOf(false)
    val maxImages = 5
    val launchCamera = mutableStateOf(false)
    var imageUri by mutableStateOf<Uri?>(null)

    val permissionDenied = mutableStateOf(false)
    val imageMap = mutableStateMapOf<String, Uri>()
//    private val _fileName = MutableLiveData("")

    private val _fileNames = mutableStateListOf<String>()

    val fileNames: List<String>
        get() = _fileNames

    fun addFileName(name: String) {
        _fileNames.add(name)
    }

    fun removeFileName(name: String) {
        _fileNames.remove(name)
    }

    fun addImage(id: String, uri: Uri) {
        if (imageMap.size < maxImages) {
            imageMap[id] = uri
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
            Pair(uri.toString(), uri)
        }.forEach { (id, uri) ->
            addImage(id, uri)
        }
        stopLoading()
    }

    fun handleCameraResult(uri: Uri?) {
        uri?.let { addImage(UUID.randomUUID().toString(), it) }
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

    fun shareSpecificPost() {
        viewModelScope.launch {
            Log.d(
                "shareCreatePost Token ",
                "shareCreatePost: ${addProviderRepository.dataStoreToken.getToken()} || "
            )
            Log.d("shareCreatePost Data ", "shareCreatePost:  ${postText} ||   ${imageUri} ||  ")
            addProviderRepository.shareSpecificPost(
                token = addProviderRepository.dataStoreToken.getToken(),
                message = postText,
                id = providerid,
                image = imageMap.values.toList()!!
            )
            File(
                FileUtils.getDocumentCacheDir(MyApplication.getApplicationContext().applicationContext),
                fileNames.toString()
            ).delete()

        }
    }


}