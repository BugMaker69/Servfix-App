package com.example.graduationproject

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@SuppressLint("SuspiciousIndentation")
class OtpViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    var otpText by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var message by mutableStateOf("")
    var verificationID by mutableStateOf("")
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance();
    var sucsess by mutableStateOf(false)
    var failed by mutableStateOf(false)
    lateinit var callbacks: OnVerificationStateChangedCallbacks
    var mobileAnimation by mutableStateOf(true)
    val countdownTime = MutableStateFlow(70)
    val resendEnabled = MutableStateFlow(true)

    // Coroutine scope tied to this ViewModel. When the ViewModel is cleared, all coroutines in this scope will be cancelled.

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
    init {
        val x = savedStateHandle.get<String>("phoneNum") ?: "empty"
        Log.d("haha", "onnnn")
        getPhoneNum(x)
    }
    fun callbackmaker(){
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                message = "Verification successful"
                Log.d("OtpViewModel", "$message")
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                message = "Fail to verify user : \n" + p0.message
                Log.d("OtpViewModel", "$message")
            }

            override fun onCodeSent(
                verificationId: String,
                p1: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationId, p1)
                Log.d("OtpViewModel", "checkk")

                verificationID = verificationId
            }
        }
    }



    fun getPhoneNum(x: String) {
        phoneNumber = "+2$x"

    }

    fun updateOtpText(newText: String) {
        if (newText.length <= 6) {
            otpText = newText
        }
    }

    fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
        auth: FirebaseAuth,
        activity: Activity,
    ) {
        // on below line signing with credentials.
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                // displaying toast message when
                // verification is successful
                if (task.isSuccessful) {
                    message = "Verification successful"
                    Log.d("haha", "$message: ")
                    mobileAnimation=false
                    failed=false
                    sucsess=true



                    //   Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
                } else {
                    // Sign in failed, display a message
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        mobileAnimation=false
                        sucsess=false
                        failed=true
                        Log.d("haha", "error: ")

                        // The verification code
                        // entered was invalid
//                        Toast.makeText(
//                            context,
//                            "Verification failed.." + (task.exception as FirebaseAuthInvalidCredentialsException).message,
//                            Toast.LENGTH_SHORT
//                        ).show()
                    }
                }
            }
    }


    fun sendVerificationCode(
        number: String?,
        auth: FirebaseAuth?,
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

}
