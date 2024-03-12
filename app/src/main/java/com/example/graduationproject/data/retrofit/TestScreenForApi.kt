package com.example.graduationproject.data.retrofit

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.graduationproject.data.LoginRequest
import com.example.graduationproject.data.LoginResponse
import com.example.graduationproject.data.Register
import com.example.graduationproject.data.ReturnedUserData
import kotlinx.coroutines.launch


@SuppressLint("DiscouragedApi")
@Composable
fun TestScreenForApi() {
    var accessToken by remember { mutableStateOf<LoginResponse?>(null) }
    val coroutineScope = rememberCoroutineScope()
    var getuserData by remember { mutableStateOf<ReturnedUserData?>(null) }
    var isClicked by remember { mutableStateOf(false) }
    val registerUserData = Register(
        userName = "ahmedd0003",
        password = "ahmed3020",
        email = "ahmed0003@gmail.com",
        address = "alex",
        phone = "01210437593",
        city = "alex"
    )

    val loginData = LoginRequest(
        username = "ahmedd0003",
        password = "ahmed3020",
    )

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Button(onClick = {

            coroutineScope.launch {
                RetrofitClient.userRegisterationApiService().postRegister(registerUserData)
                Log.d("Register LoginScreen2", "LoginScreen2: Register Finish")


                val response = RetrofitClient.userRegisterationApiService().login(loginData)
                Log.d("Token TestScreenForApi", "TestScreenForApi: Token Finish ${accessToken}")

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    accessToken = loginResponse
                    if (loginResponse != null) {
                        Log.d("LogIn Succ", "login Token : ${loginResponse.access}")
                    } else {
                        Log.d("Errorrr", "Blabla")
                    }
                } else {
                    Log.d("Unhandle Error", "HTTP status code: ${response.code()}")

                }

                getuserData = accessToken?.access?.let {
                    RetrofitClient.userRegisterationApiService().getReturnedUserData("Bearer $it")
                }
                Log.d(
                    "Get User Data TestScreenForApi",
                    "TestScreenForApi: UserData ${getuserData!!.user} || ${getuserData!!.email} || ${getuserData!!.city} || ${getuserData!!.image}"
                )

            }

            isClicked = true
        }, enabled = !isClicked) {
            if (isClicked)
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            else
                Text(text = "Update User")
        }

    }


}

@Preview(showBackground = true)
@Composable
fun TestScreenForApiPreview() {
    TestScreenForApi()
}


