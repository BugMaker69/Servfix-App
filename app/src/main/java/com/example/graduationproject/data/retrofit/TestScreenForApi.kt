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
import com.example.graduationproject.data.ProviderData
import com.example.graduationproject.data.Register
import com.example.graduationproject.data.RequsetUpdateData
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.ReturnedUserData
import kotlinx.coroutines.launch


@SuppressLint("DiscouragedApi")
@Composable
fun TestScreenForApi() {
    var accessToken by remember { mutableStateOf<LoginResponse?>(null) }
    val coroutineScope = rememberCoroutineScope()
    var returnUpdateUserData by remember { mutableStateOf<ReturnedUserData?>(null) }
    var returnUpdateProviderData by remember { mutableStateOf<ReturnedProviderData?>(null) }
    var getuserData by remember { mutableStateOf<ReturnedUserData?>(null) }
    var getProviderData by remember { mutableStateOf<ReturnedProviderData?>(null) }
    var isClicked by remember { mutableStateOf(false) }

    val registerUserData = Register(
        userName = "ahmedd0005",
        password = "ahmed3020",
        email = "ahmed0005@gmail.com",
        address = "alex",
        phone = "01210437593",
        city = "alex"
    )

    val registerProviderData = ProviderData(
        username = "Omar007",
        password = "#Omar007#",
        email = "omar007@gmail.com",
        address = "alex",
        phone = "01111111111",
        city = "alex",
        fixed_salary = 500.0,
        id_image = "ramadan.jpg",
        profession = "Carpenter",
    )

    val loginData = LoginRequest(
        username = "ahmedd0005",
        password = "ahmed3020",
    )

    val providerLoginData = LoginRequest(
        username = "Omar007",
        password = "#Omar007#",
    )

    val updatedUserData = RequsetUpdateData(
        username = "Omar01",
        email = "omar01@gmail.com",
        phone = "0121222222",
        address = "alexandria",
        city = "alex",
        image = ""
    )

    val updatedProviderData = ReturnedProviderData(
        phone = "0122222222",
        username = "Omar005",
        password = "#Omar007#",
        email = "omar005@gmail.com",
        fixed_salary = "200",
        image = "",
        ratings = "4.4",
        city = "alex",
        address = "alexandria",
        id = 1,
        user = 5,
        profession = "Electricity",
        service_id = 5
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

                // Provider REGISTER
                RetrofitClient.userRegisterationApiService()
                    .postRegisterProvider(registerProviderData)
                Log.d("Register TestScreenForApi", "TestScreenForApi: Register Provider Finish")

                // Provider LOGIN
                val result = RetrofitClient.userRegisterationApiService().login(providerLoginData)
                Log.d("Token TestScreenForApi", "TestScreenForApi: Token Finish ${accessToken}")

                if (result.isSuccessful) {
                    val loginResponse = result.body()
                    accessToken = loginResponse
                    if (loginResponse != null) {
                        Log.d("LogIn Succ", "login Token : ${loginResponse.access}")
                    } else {
                        Log.d("Errorrr", "Blabla")
                    }
                } else {
                    Log.d("Unhandle Error", "HTTP status code: ${result.code()}")

                }

                // Provider DATA
                getProviderData = accessToken?.access?.let {
                    RetrofitClient.userRegisterationApiService()
                        .getReturnedProviderData("Bearer $it")
                }
                Log.d(
                    "Get Provider Data TestScreenForApi",
                    "TestScreenForApi:  Provider Data ${getuserData!!.user} || ${getuserData!!.username} || ${getuserData!!.phone} || ${getuserData!!.email} || ${getuserData!!.city} || ${getuserData!!.image}"
                )


                // UPDATE Provider DATA
                returnUpdateProviderData = accessToken?.access?.let {
                    RetrofitClient.userRegisterationApiService().updateProviderData(
                        "Bearer $it",
                        updatedProviderData
                    )
                }
                Log.d(
                    "Get UpdatedUser Data TestScreenForApi",
                    "TestScreenForApi: Updated UserData ${returnUpdateUserData!!.user} || ${returnUpdateUserData!!.username} || ${returnUpdateUserData!!.phone} || ${returnUpdateUserData!!.email} || ${returnUpdateUserData!!.city} || ${returnUpdateUserData!!.image}"
                )


                // USER REGISTER
                RetrofitClient.userRegisterationApiService().postRegister(registerUserData)
                Log.d("Register TestScreenForApi", "TestScreenForApi: Register Finish")

                // USER LOGIN
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

                // USER DATA
                getuserData = accessToken?.access?.let {
                    RetrofitClient.userRegisterationApiService().getReturnedUserData("Bearer $it")
                }

                Log.d(
                    "Get User Data TestScreenForApi",
                    "TestScreenForApi: UserData ${getuserData!!.user} || ${getuserData!!.email} || ${getuserData!!.city} || ${getuserData!!.image}"
                )

                // UPDATE USER DATA
                returnUpdateUserData = accessToken?.access?.let {
                    RetrofitClient.userRegisterationApiService().updateUserData(
                        "Bearer $it",
                        updatedUserData
                    )
                }
                Log.d(
                    "Get UpdatedUser Data LoginScreen2",
                    "LoginScreen2: Updated UserData ${returnUpdateUserData!!.user} || ${returnUpdateUserData!!.username} || ${returnUpdateUserData!!.phone} || ${returnUpdateUserData!!.email} || ${returnUpdateUserData!!.city} || ${returnUpdateUserData!!.image}"
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


