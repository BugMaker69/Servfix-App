package com.example.graduationproject.presentation.userservice

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.MyApplication
import com.example.graduationproject.data.Services
import com.example.graduationproject.data.constants.Constant
import com.example.graduationproject.data.repositories.AddProviderRepository
import com.example.graduationproject.data.repositories.UserServicesRepository
import com.example.graduationproject.data.retrofit.InternetObserver
import com.example.graduationproject.data.retrofit.NetworkConnectivityObserver
import com.example.graduationproject.utils.FileUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(    val addProviderRepository :AddProviderRepository
                                               ,val userServicesRep: UserServicesRepository,
                                               val savedStateHandle: SavedStateHandle

): ViewModel() {
    var servicesState = mutableStateOf(emptyList<Services>())



    init {
       getServicesList()



    }


    fun getServicesList() {
        try {


            viewModelScope.launch(Dispatchers.IO) {
                val services = userServicesRep.getServicesList()
                services.forEach { service ->
                    service.image = Constant.BASE_URL + service.image

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

//    var getAllWork by mutableStateOf(emptyList<GetWorksItem>())


    //  DropdownMenu Of Service Inside Share Problem
    var selectedSentToServiceIndex by mutableStateOf(-1)
    var selectedSentToServiceValue by mutableStateOf("")
    var selectedSentToLocationIndex by mutableStateOf(-1)
    var selectedSentToLocationValue by mutableStateOf("")
    var textfieldSentToServiceSize by mutableStateOf(Size.Zero)


    //  Share Problem   Handle Photos

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
/*    fun removeImage() {
        imageUri=null
    }*/
    fun startLoading() {
        isLoading.value = true
    }

    fun stopLoading() {
        isLoading.value = false
    }


    fun handleGalleryResult(context: Context, uris: List<Uri>) {
        uris.mapNotNull { uri ->
                Pair(uri.toString(),uri)
        }.forEach { (id, uri) ->
            addImage(id, uri)
        }
        stopLoading()
    }

/*    val fileName: LiveData<String>
        get() = _fileName


    fun setFileName(name:String) {
        _fileName.value = name
    }*/

/*

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
*/

/*    fun handleCameraResult(uri: Uri?) {
        uri?.let { imageUri = it }
        stopLoading()
    }*/

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

    fun shareCreatePost(){
        viewModelScope.launch {
            Log.d("shareCreatePost Token ", "shareCreatePost: ${addProviderRepository.dataStoreToken.getToken()} || ")
            Log.d("shareCreatePost Data ", "shareCreatePost: ${selectedSentToLocationValue} ||  ${selectedSentToServiceValue} ||   ${postText} ||   ${imageUri} ||  ")
            addProviderRepository.shareCreatePost(
                token = addProviderRepository.dataStoreToken.getToken(),
                city = selectedSentToLocationValue,
                service_name = selectedSentToServiceValue,
                problem_description = postText,
//                fileRealPath = fileNames,
                image = imageMap.values.toList()!!
            )
            File(FileUtils.getDocumentCacheDir(MyApplication.getApplicationContext().applicationContext), fileNames.toString()).delete()

        }
    }




/*
    fun getAllWorks() {
        viewModelScope.launch {
            getAllWork = addProviderRepository.getAllWorks()
        }
    }

    fun deleteWork(id: Int) {
        viewModelScope.launch {
            addProviderRepository.deleteWork(id)
        }
    }

    fun addWork() {
        viewModelScope.launch {
            Log.d("addWork Images", "addWork: ${imageMap.values.toList()}")
            addProviderRepository.addWork(imageMap.values.toList())
        }
    }
*/







}