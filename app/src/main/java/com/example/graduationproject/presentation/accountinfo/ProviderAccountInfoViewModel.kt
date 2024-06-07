package com.example.graduationproject.presentation.accountinfo

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.R
import com.example.graduationproject.data.GetWorksItem
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.constants.Constant
import com.example.graduationproject.data.repositories.AddProviderRepository
import com.example.graduationproject.presentation.common.UserType
import com.example.graduationproject.utils.DataStoreToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProviderAccountInfoViewModel @Inject constructor(val addProviderRepository: AddProviderRepository,val dataStoreToken: DataStoreToken,val savedStateHandle: SavedStateHandle): ViewModel() {
    var returnedProviderData: ReturnedProviderData? by mutableStateOf<ReturnedProviderData?>(null)
    var userName by mutableStateOf("")
    var rating by mutableStateOf("")

/*    var imageId by mutableStateOf(0)
    var imageWork by mutableStateOf("")

    init {
        imageId = savedStateHandle.get<Int>("pid") ?: 0
        imageWork = savedStateHandle.get<String>("pname") ?: ""
        Log.d("savedStateHandle Image ID", "savedStateHandle Image ID : $imageId")
        Log.d("savedStateHandle Image ID", "savedStateHandle Image ID : $imageWork")
    }*/

    var getAllWork by mutableStateOf(emptyList<GetWorksItem>())

    val isLoading = mutableStateOf(false)
    val maxImages = 5
    val launchCamera = mutableStateOf(false)
//    var imageUri by mutableStateOf<Uri?>(null)

    val permissionDenied = mutableStateOf(false)
    val imageMap = mutableStateMapOf<String, Uri>()


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
            Pair(uri.toString(),uri)
        }.forEach { (id, uri) ->
            addImage(id, uri)
        }
        stopLoading()
    }

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



    //    var city by mutableStateOf("")
    var address by mutableStateOf("")
    var emailN by mutableStateOf("")
    var phone by mutableStateOf("")
    var fixedSalary by mutableStateOf("")
    var selectedCityValue by mutableStateOf("")
    var imageUri by mutableStateOf<Uri?>(null)
    var accounType by mutableStateOf(UserType.HirePerson)
    //  DropdownMenu Of City Inside SignUp
    var expanded by mutableStateOf(false)
    var selectedCityIndex by mutableStateOf(-1)
    var textfieldSize by mutableStateOf(Size.Zero)


    var userNameError by mutableStateOf(false)
    var cityError by mutableStateOf(false)
    var addressError by mutableStateOf(false)
    var emailNError by mutableStateOf(false)
    var phoneError by mutableStateOf(false)
    val isUsernameFocused = mutableStateOf(false)
    val isPhoneNumberFocused = mutableStateOf(false)
    val isEmailNFocused = mutableStateOf(false)
    var passwordN by mutableStateOf("")

    val Emailregex = "^[a-zA-Z]{3,}.*@.*\\.[a-zA-Z]+".toRegex()
    fun onFixedSalaryChanged(fixedSalary1: String) {
        fixedSalary = fixedSalary1
    }

    //  DropdownMenu Of Service Inside SignUp
    var expandedService by mutableStateOf(false)
    var selectedServiceIndex by mutableStateOf(-1)
    var textfieldServiceSize by mutableStateOf(Size.Zero)
    val fullnameregex = "^[a-zA-Z]{3,}(\\s+[a-zA-Z]{3,})+".toRegex()

    val phoneNumberregex = "^01[0|1|2|5]\\d{8}$".toRegex()


    var selectedServiceValue by mutableStateOf("")
    init {
            getProviderData()

    }
    fun getProviderData() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val providerData = withContext(Dispatchers.IO) {
                    addProviderRepository.getProviderData()
                }
                returnedProviderData = providerData
                returnedProviderData?.let {
                    userName = it.username
                    address = it.address
                    selectedCityValue = it.city
                    emailN = it.email
                    phone = it.phone
                    rating=it.ratings
                    fixedSalary = it.fixed_salary.toString()
                    selectedServiceValue = it.profession
                    imageUri = Uri.parse( Constant.BASE_URL + it.image)
                }
                accounType = UserType.HirePerson
                Log.d(
                    "TAG",
                    "getProviderData: data40 ${returnedProviderData?.email}   ${returnedProviderData?.id}   ${returnedProviderData?.username}  "
                )
            } catch (e: Exception) {
                Log.d(
                    "EEERRROOORRR",
                    "Provider Error: ${e.cause} ||  ${e.message} ||  ${e.localizedMessage} ||  ${e.stackTrace} ||  ${e.suppressed} ||  ${e} || "
                )
            }
        }
    }

    fun onUserNameChanged(userName1: String) {
        userName = userName1
        userNameError = !userName1.matches(fullnameregex)
    }
    val usernameRequirements = listOf(
        R.string.username_requirement_words to { s: String ->
            s.split(" ")
                .filter { it.all { c -> c.isLetter() || c.isWhitespace() } && it.length >= 3 }.size >= 2
        })
    fun onAddressChanged(address1: String) {
        address = address1
        addressError = address1.isEmpty()
    }
    fun onEmailChangedN(email1: String) {
        emailN = email1.trim()
        emailNError = !email1.matches(Emailregex)
    }
    val emailRequirements = listOf(
        R.string.email_requirement_format to { s: String -> s.matches("^[a-zA-Z]{3,}.*@.*\\.[a-zA-Z]+".toRegex()) }
    )

    fun onPhoneChanged(phone1: String) {
        if (phone1.length < 12) {
            phone = phone1.trim()
            phoneError = !phone1.matches(phoneNumberregex)
        }
    }
    val phoneNumberRequirements = listOf(
        R.string.phone_requirement_start to { s: String -> s.matches("^01[0|1|2|5][0-9]*\$".toRegex()) },
        R.string.phone_requirement_length to { s: String -> s.length == 11 }
    )
    fun updateProviderData() {

        viewModelScope.launch(Dispatchers.IO) {
            addProviderRepository.updateProviderData(
//                token =  addProviderRepository.dataStoreToken.getToken(),
                address = address,
                city = selectedCityValue,
                email = emailN,
                fixed_salary = fixedSalary,
                image = imageUri!!,
                password = passwordN,
                phone = phone,
                profession = selectedServiceValue,
                username = userName,
                /*            ratings = "5",
                            service_id = 1,
                            user = 1,*/
            )
        }




    }
}