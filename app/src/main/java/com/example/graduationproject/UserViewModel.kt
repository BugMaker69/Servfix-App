package com.example.graduationproject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class UserViewModel : ViewModel() {

    // LOGIN
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    fun onEmailChanged(email1: String){
        email = email1.trim()
    }

    fun onPasswordChanged(password1: String){
        password = password1.trim()
    }



    //  SIGNUP
    var userName by mutableStateOf("")
    var city by mutableStateOf("")
    var address by mutableStateOf("")
    var emailN by mutableStateOf("")
    var phone by mutableStateOf("")
    var passwordN by mutableStateOf("")
    var passwordConf by mutableStateOf("")

    fun onUserNameChanged(userName1: String){
        userName = userName1
    }

    fun onCityChanged(city1: String){
        city = city1.trim()
    }

    fun onAddressChanged(address1: String){
        address = address1
    }

    fun onEmailChangedN(email1: String){
        emailN = email1.trim()
    }

    fun onPhoneChanged(phone1: String){
        if (phone1.length<12)
            phone = phone1.trim()
    }

    fun onPasswordChangedN(password1: String){
        passwordN = password1.trim()
    }

    fun passwordConfChanged(passwordConf1: String){
        passwordConf = passwordConf1.trim()
    }

    val phoneNumberregex = "^01[0|1|2|5]\\d{8}$".toRegex()
    val Emailregex = "^[a-zA-Z]{4,}.*@.*\\.[a-zA-Z]+".toRegex()
    val fullnameregex = "^[a-zA-Z]{4,} [a-zA-Z]{4,}".toRegex()
    val passwordregex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$".toRegex()


    fun validatePhoneNumber(phoneNumber: String): Boolean {
        return phoneNumberregex.matches(phoneNumber)
    }

    fun validateEmail(email: String): Boolean {
        return Emailregex.matches(email)
    }


    fun validateFullName(fullName: String): Boolean {
        return fullnameregex.matches(fullName)
    }

    fun validatePassword(password: String): Boolean {
        return passwordregex.matches(password)
    }

}