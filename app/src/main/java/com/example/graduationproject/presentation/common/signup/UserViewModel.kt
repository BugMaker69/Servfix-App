package com.example.graduationproject.presentation.common.signup

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.R
import com.example.graduationproject.data.LoginRequest
import com.example.graduationproject.data.Register
import com.example.graduationproject.data.retrofit.RetrofitClient
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class UserViewModel() : ViewModel() {
    private val apiService = RetrofitClient.userRegisterationApiService()


    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verificationID by mutableStateOf("")
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance();
    var otpText by mutableStateOf("")
    var sucsess by mutableStateOf(false)
    var failed by mutableStateOf(false)
    var mobileAnimation by mutableStateOf(true)
    val countdownTime = MutableStateFlow(70)
    val resendEnabled = MutableStateFlow(true)

    // LOGIN
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var emailError by mutableStateOf(false)
    var passwordError by mutableStateOf(false)

    var isPasswordForget by mutableStateOf(false)

    fun onEmailChanged(email1: String) {
        email = email1.trim()
        emailError = email1.isEmpty()
    }

    fun onPasswordChanged(password1: String) {
        password = password1.trim()
        passwordError = password1.isEmpty()
    }

    fun onLoginClick() {
        if (email.isEmpty()) {
            emailError = true
        }
        if (password.isEmpty()) {
            passwordError = true
        }

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


    val isPasswordNFocused = mutableStateOf(false)
    val isUsernameFocused = mutableStateOf(false)
    val isPhoneNumberFocused = mutableStateOf(false)
    val isEmailNFocused = mutableStateOf(false)


    //  Third SignUp Page

    var fixedSalary by mutableStateOf("")

    //  DropdownMenu Of Service Inside SignUp
    var expandedService by mutableStateOf(false)
    var selectedServiceIndex by mutableStateOf(-1)
    var selectedServiceValue by mutableStateOf("")
    var textfieldServiceSize by mutableStateOf(Size.Zero)


    //  Forget Password

    var phoneChange by mutableStateOf("")
    var newPassword by mutableStateOf("")
    var newPasswordConf by mutableStateOf("")

    var newPasswordNError by mutableStateOf(false)
    var newPasswordConfError by mutableStateOf(false)
    val isNewPasswordFocused = mutableStateOf(false)


    fun startCountdown() {
        // If the resend button is enabled, start the countdown
        if (resendEnabled.value) {
            resendEnabled.value = false
            countdownTime.value = 70

            // Launch a new coroutine in viewModelScope
            viewModelScope.launch {
                while (countdownTime.value > 0) {
                    delay(1000L)
                    countdownTime.value--
                }
                resendEnabled.value = true
            }
        }
    }

    fun updateOtpText(newText: String) {
        if (newText.length <= 6) {
            otpText = newText
        }
    }

    fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
            verificationID,
            otpText
        ),
        auth: FirebaseAuth = mAuth,
        activity: Activity,
    ) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->

                if (task.isSuccessful) {

                    mobileAnimation = false
                    failed = false
                    sucsess = true


                    //   Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
                } else {
                    // Sign in failed, display a message
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        mobileAnimation = false
                        sucsess = false
                        failed = true
                        Log.d("haha", "error: ")


                    }
                }
            }
    }

    val phoneNumberregex = "^01[0|1|2|5]\\d{8}$".toRegex()
    val Emailregex = "^[a-zA-Z]{4,}.*@.*\\.[a-zA-Z]+".toRegex()
    val fullnameregex = "^[a-zA-Z]{4,} [a-zA-Z]{4,}".toRegex()
    val passwordregex = "^(?=.*[A-Z])(?=.*[^\\s\\w])[A-Za-z\\d[^\\s\\w]]{8,}$".toRegex()


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

    fun onFixedSalaryChanged(fixedSalary1: String) {
        fixedSalary = fixedSalary1
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
        passwordConfError =
            (passwordConf1 != passwordN) || (!passwordConf1.matches(passwordregex))
    }

//    val passwordregex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$".toRegex()
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


    fun callbackmaker() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(p0: FirebaseException) {

            }

            override fun onCodeSent(
                verificationId: String,
                p1: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationId, p1)
                Log.d("OtpViewModel", "checkk")

                verificationID = verificationId
                Log.d("yalo", "onCodeSent:$verificationId and $verificationID ")
            }
        }
    }

    fun onNextSecondSignupClick() {

        if (!emailN.matches(Emailregex)) {
            emailNError = true
        }
        if (!passwordN.matches(passwordregex)) {
            passwordNError = true
        }
        if (passwordConf != passwordN || !passwordConf.matches(passwordregex)) {
            passwordConfError = true
        }

    }

    fun onFinishSignupClick() {
        callbackmaker()
    }


    fun sendVerificationCode(
        number: String? = "+2" + phone,
        auth: FirebaseAuth? = mAuth,
        activity: Activity?,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks?
    ) {
        if (number != null && auth != null && activity != null && callbacks != null) {
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(number)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(callbacks)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        } else {
            Log.d("soon", "sendVerificationCode: ")
        }
    }
    fun registerUser() {
        val register = Register(
            userName = userName,
            password = passwordN,
            email = emailN,
            address = address,
            phone = phone,
            city = selectedCityValue
        )

        viewModelScope.launch {
            val response = apiService.postRegister(register)
            Log.d("boolo", "registerUser: maybe")


            // Check the response
            if (response.isSuccessful) {
                Log.d("boolo", "registerUser: done")
                val registeredUser = response.body()
            } else {
                Log.d("boolo", "registerUser: not done. Status code: ${response.code()}, Error message: ${response.message()}")

            }
        }
    }
    fun login(){
        val loginRequest = LoginRequest(username = email, password = password)
        viewModelScope.launch {
            val response = apiService.login(loginRequest)

            if (response.isSuccessful) {
                val loginResponse = response.body()
                if (loginResponse != null) {
                    Log.d("bqq", "login: ${loginResponse.refresh+" wait"+ loginResponse.access}")
                    // Save the tokens
                } else {
                    Log.d("bqq", "error1")
                }
            } else {
                Log.d("bqq", "error2, HTTP status code: ${response.code()}, error body: ${response.errorBody()?.string()}")

            }
        }

    }
}

