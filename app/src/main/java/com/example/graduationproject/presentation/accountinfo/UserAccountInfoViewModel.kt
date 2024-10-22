package com.example.graduationproject.presentation.accountinfo

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.R
import com.example.graduationproject.data.ReturnedUserData
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
class UserAccountInfoViewModel @Inject constructor(val addProviderRepository: AddProviderRepository,val dataStoreToken: DataStoreToken):ViewModel() {
       var returnedUserData: ReturnedUserData? by mutableStateOf<ReturnedUserData?>(null)
    var userName by mutableStateOf("")


    var address by mutableStateOf("")
    var emailN by mutableStateOf("")
    var phone by mutableStateOf("")
    var selectedCityValue by mutableStateOf("")
    var imageUri by mutableStateOf<Uri?>(null)
    var accounType by mutableStateOf(UserType.OwnerPerson)



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

    val Emailregex = "^[a-zA-Z]{3,}.*@.*\\.[a-zA-Z]+".toRegex()


    val fullnameregex = "^[a-zA-Z]{3,}(\\s+[a-zA-Z]{3,})+".toRegex()

    val phoneNumberregex = "^01[0|1|2|5]\\d{8}$".toRegex()


    init {
            getUserData()


    }
    fun updateUserData() {
viewModelScope.launch (Dispatchers.IO){
    Log.d("updateUserData", "updateUserData: $imageUri")
    addProviderRepository.updateUserData(
//        token = dataStoreToken.getToken(),
        address = address,
        city = selectedCityValue,
        email = emailN,
        image = imageUri!!,
        phone = phone,
        username = userName,
    )


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



fun getUserData() {
    viewModelScope.launch(Dispatchers.IO) {
        try {
            Log.d("whoo", "getUserData: ${ dataStoreToken.getToken()}")


            returnedUserData = addProviderRepository.getUserData(
            )
            withContext (Dispatchers.Main){
                returnedUserData?.let {
                    userName = it.username
                    emailN = it.email
                    selectedCityValue = it.city
                    address = it.address
                    phone = it.phone
                    imageUri = Uri.parse(Constant.BASE_URL + it.image)
                }
                accounType = UserType.OwnerPerson


            }
        } catch (e: Exception) {
            Log.d(
                "EEERRROOORRR",
                "User Error: ${e.cause} ||  ${e.message} ||  ${e.localizedMessage} ||  ${e.stackTrace} ||  ${e.suppressed} ||  ${e} || "
            )
        }
    }
}

}