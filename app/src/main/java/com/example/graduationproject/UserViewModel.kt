package com.example.graduationproject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
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


    //  Forget Password

    var phoneChange by mutableStateOf("")
    var newPassword by mutableStateOf("")
    var newPasswordConf by mutableStateOf("")

    var newPasswordNError by mutableStateOf(false)
    var newPasswordConfError by mutableStateOf(false)

    val isNewPasswordFocused = mutableStateOf(false)

    fun onNewPhoneChanged(phone1: String) {
        if (phone1.length < 12) {
            phoneChange = phone1.trim()
            phoneError = !phone1.matches(phoneNumberregex)
        }
    }

    fun onNewPasswordChanged(password1: String) {
        newPassword = password1.trim()
        newPasswordNError = !password1.matches(passwordregex)
    }

    fun newPasswordConfChanged(passwordConf1: String) {
        newPasswordConf = passwordConf1.trim()
        newPasswordConfError =
            (passwordConf1 != newPassword) || (!passwordConf1.matches(passwordregex))
    }


    //  SIGNUP
    var userName by mutableStateOf("")

    //    var city by mutableStateOf("")
    var address by mutableStateOf("")
    var emailN by mutableStateOf("")
    var phone by mutableStateOf("")
    var passwordN by mutableStateOf("")
    var passwordConf by mutableStateOf("")

    var eyeIconPress by mutableStateOf(false)
    var eyeIconPressC by mutableStateOf(false)

    //  DropdownMenu Of City Inside SignUp
    var expanded by mutableStateOf(false)
    var selectedCityIndex by mutableStateOf(-1)
    var selectedCityValue by mutableStateOf("")
    var textfieldSize by mutableStateOf(Size.Zero)


    var userNameError by mutableStateOf(false)
    var cityError by mutableStateOf(false)
    var addressError by mutableStateOf(false)
    var emailNError by mutableStateOf(false)
    var phoneError by mutableStateOf(false)
    var passwordNError by mutableStateOf(false)
    var passwordConfError by mutableStateOf(false)


    val isPasswordFocused = mutableStateOf(false)
    val isUsernameFocused = mutableStateOf(false)
    val isPhoneNumberFocused = mutableStateOf(false)
    val isEmailFocused = mutableStateOf(false)

    val passwordRequirements = listOf(
        R.string.password_requirement_length to { s: String -> s.length >= 8 },
        R.string.password_requirement_capital to { s: String -> s.any { it.isUpperCase() } },
        R.string.password_requirement_special to { s: String -> s.any { !it.isLetterOrDigit() } }
    )

    val usernameRequirements = listOf(
        R.string.username_requirement_words to { s: String ->
            s.split(" ")
                .filter { it.all { c -> c.isLetter() || c.isWhitespace() } && it.length >= 4 }.size >= 2
        })

    val phoneNumberRequirements = listOf(
        R.string.phone_requirement_start to { s: String -> s.matches("^01[0|1|2|5][0-9]*\$".toRegex()) },
        R.string.phone_requirement_length to { s: String -> s.length == 11 }
    )

    val emailRequirements = listOf(
        R.string.email_requirement_format to { s: String -> s.matches("^[a-zA-Z]{4,}.*@.*\\.[a-zA-Z]+".toRegex()) }
    )


    fun onUserNameChanged(userName1: String) {
        userName = userName1
        userNameError = !userName1.matches(fullnameregex)
    }

//    fun onCityChanged(city1: String) {
//        city = city1.trim()
//        cityError = city1.isEmpty()
//    }

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
//    val passwordregex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9\\s\\t\\n\\r])[A-Za-z\\d[^A-Za-z0-9\\s\\t\\n\\r]]{8,}$"
//        .toRegex()


    fun onNextFirstSignupClick() {
        if (!userName.matches(fullnameregex)) {
            userNameError = true
        }
        if (selectedCityValue.isEmpty() && selectedCityIndex == -1) {
            cityError = true
        }
        if (address.isEmpty()) {
            addressError = true
        }

        if (!phone.matches(phoneNumberregex)) {
            phoneError = true
        }

//        if (!userNameError && !cityError && !addressError && !emailNError && !phoneError && !passwordNError && !passwordConfError) {
//            // Handle the signup response
//        }
    }

    fun onNextSecondSignupClick() {

        if (!email.matches(Emailregex)) {
            emailNError = true
        }
        if (!passwordN.matches(passwordregex)) {
            passwordNError = true
        }
        if (passwordConf != passwordN || !passwordConf.matches(passwordregex)) {
            passwordConfError = true
        }

    }


}