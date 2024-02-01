package com.example.graduationproject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class UserViewModel : ViewModel() {

    // LOGIN
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    fun onEmailChanged(email1: String) {
        email = email1.trim()
    }

    fun onPasswordChanged(password1: String) {
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


    var userNameError by mutableStateOf(false)
    var cityError by mutableStateOf(false)
    var addressError by mutableStateOf(false)
    var emailNError by mutableStateOf(false)
    var phoneError by mutableStateOf(false)
    var passwordNError by mutableStateOf(false)
    var passwordConfError by mutableStateOf(false)


    fun onUserNameChanged(userName1: String) {
        userName = userName1
        userNameError = !userName1.matches(fullnameregex)
    }

    fun onCityChanged(city1: String) {
        city = city1.trim()
        cityError = city1.isEmpty()
    }

    fun onAddressChanged(address1: String) {
        address = address1
        addressError = address1.isEmpty()
    }

    fun onEmailChangedN(email1: String) {
        emailN = email1.trim()
        emailNError = !email1.matches(Emailregex)
    }

    fun onPhoneChanged(phone1: String) {
        if (phone1.length < 12) {
            phone = phone1.trim()
            phoneError = !phone1.matches(phoneNumberregex)
        }
    }

    fun onPasswordChangedN(password1: String) {
        passwordN = password1.trim()
        passwordNError = !password1.matches(passwordregex)
    }

    fun passwordConfChanged(passwordConf1: String) {
        passwordConf = passwordConf1.trim()
        passwordConfError = (passwordConf1 != passwordN) || (!passwordConf1.matches(passwordregex))
    }

    val phoneNumberregex = "^01[0|1|2|5]\\d{8}$".toRegex()
    val Emailregex = "^[a-zA-Z]{4,}.*@.*\\.[a-zA-Z]+".toRegex()
    val fullnameregex = "^[a-zA-Z]{4,} [a-zA-Z]{4,}".toRegex()
    val passwordregex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$".toRegex()


    fun onSignupClick() {
        if (!userName.matches(fullnameregex)) {
            userNameError = true
        }
        if (city.isEmpty()) {
            cityError = true
        }
        if (address.isEmpty()) {
            addressError = true
        }
        if (!email.matches(Emailregex)) {
            emailNError = true
        }
        if (!phone.matches(phoneNumberregex)) {
            phoneError = true
        }
        if (!passwordN.matches(passwordregex)) {
            passwordNError = true
        }
        if (passwordConf != passwordN || !passwordConf.matches(passwordregex)) {
            passwordConfError = true
        }
        if (!userNameError && !cityError && !addressError && !emailNError && !phoneError && !passwordNError && !passwordConfError) {
            // Handle the signup response
        }
    }

}