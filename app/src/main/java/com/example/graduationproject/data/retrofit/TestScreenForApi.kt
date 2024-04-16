//package com.example.graduationproject.data.retrofit
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.drawable.BitmapDrawable
//import android.util.Log
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.core.content.ContextCompat
//import com.airbnb.lottie.compose.rememberLottieDynamicProperty
//import com.example.graduationproject.R
//import com.example.graduationproject.data.LoginRequest
//import com.example.graduationproject.data.LoginResponse
//import com.example.graduationproject.data.ProviderData
//import com.example.graduationproject.data.Register
//import com.example.graduationproject.data.RequsetUpdateData
//import com.example.graduationproject.data.ReturnedProviderData
//import com.example.graduationproject.data.ReturnedUserData
//import com.example.graduationproject.data.ServiceProviderSearch
//import kotlinx.coroutines.launch
//import okhttp3.MediaType
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.MultipartBody
//import okhttp3.RequestBody
//import java.io.File
//import java.io.FileOutputStream
//
//
//@SuppressLint("DiscouragedApi")
//@Composable
//fun TestScreenForApi() {
//    var accessToken by remember { mutableStateOf<LoginResponse?>(null) }
//    val coroutineScope = rememberCoroutineScope()
//    var returnUpdateUserData by remember { mutableStateOf<ReturnedUserData?>(null) }
//    var returnUpdateProviderData by remember { mutableStateOf<ReturnedProviderData?>(null) }
//    var getuserData by remember { mutableStateOf<ReturnedUserData?>(null) }
//    var getProviderData by remember { mutableStateOf<ReturnedProviderData?>(null) }
//    var isClicked by remember { mutableStateOf(false) }
//    var test by remember { mutableStateOf<List<ServiceProviderSearch>?>(null) }
//
//
//
//    val registerUserData = Register(
//        userName = "belal",
//        password = "#Test123",
//        email = "belal@gmail.com",
//        address = "alex",
//        phone = "01210437593",
//        city = "alex"
//    )
////
////    val registerProviderData = ProviderData(
////        username = "aly",
////        password = "#Aly23#",
////        email = "aly@gmail.com",
////        address = "test",
////        phone = "0130982311",
////        city = "Cairo",
////        fixed_salary = 300.00,
////        id_image = "ramadan.jpg",
////        profession = "electricity",
////    )
//    val addressPart =
//        RequestBody.create("text/plain".toMediaTypeOrNull(),"lol")
//    val cityPart = RequestBody.create("text/plain".toMediaTypeOrNull(), "registerProviderData.city")
//    val emailPart = RequestBody.create("text/plain".toMediaTypeOrNull(), "registerProviderData.email")
//    val fixedSalaryPart = RequestBody.create(
//        "text/plain".toMediaTypeOrNull(),
//       "200.00"
//    )
//    val context= LocalContext.current
//    val passwordPart =
//        RequestBody.create("text/plain".toMediaTypeOrNull()," registerProviderData.password")
//    val phonePart = RequestBody.create("text/plain".toMediaTypeOrNull(), "0100230301")
//    val professionPart =
//        RequestBody.create("text/plain".toMediaTypeOrNull(), "electricity")
//    val usernamePart =
//        RequestBody.create("text/plain".toMediaTypeOrNull(),"ahmed343242")
//    val imageFile = drawableToFile(context, R.drawable.person, "person.jpg")
//    val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), imageFile)
//
//// Create MultipartBody.Part for the image
//    val imagePart = MultipartBody.Part.createFormData("id_image", imageFile.name, requestFile)
//
//
//    val loginData = LoginRequest(
//        username = "ahmedd0005",
//        password = "ahmed3020",
//    )
//
//    val providerLoginData = LoginRequest(
//        username = "aly",
//        password = "#Aly23#",
//    )
//
//    val updatedUserData = RequsetUpdateData(
//        email = "omar01@gmail.com",
//        phone = "0121222222",
//        address = "alexandria",
//        city = "alex",
//        image = ""
//    )
//
////    val updatedProviderData = ReturnedProviderData(
////        phone = "0122222222",
////        username = "Omar005",
////        password = "#Omar007#",
////        email = "omar005@gmail.com",
////      //  fixed_salary = 200.0,
////        image = "",
////        ratings = "4.4",
////        city = "alex",
////        address = "alexandria",
////        id = 1,
////        user = 5,
////        profession = "Electricity",
////        service_id = 5
////    )
//
//    Column(
//        Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Button(onClick = {
//
//            coroutineScope.launch {
//                // Provider REGISTER
//                try{
//                 RetrofitClient.userRegisterationApiService()
//                        .postRegisterProvider(
//                            address = addressPart,
//                            city = cityPart,
//                            email = emailPart,
//                            fixed_salary = fixedSalaryPart,
//                            password = passwordPart,
//                            phone = phonePart,
//                            profession = professionPart,
//                            username = usernamePart,
//                            id_image = imagePart
//                        )
//                    Log.d("toot", "TestScreenForApi:${addressPart.contentLength()} ")
//                    Log.d("toot", "TestScreenForApi:${cityPart} ")
//                    Log.d("toot", "TestScreenForApi:${imagePart} ")
//
//
//
//                }catch (Ex:Exception)
//                {
//                    Log.d("errr", "TestScreenForApi:${Ex.message.toString()} ")
//
//                }
//
//
//
////
////
////                Log.d("wwwww", "TestScreenForApi: ${result.headers().get("id_image").toString()}")
//
//
//                    Log.d("Register done", "TestScreenForApi: Register Provider Finish")
//
//            }
//
//
//        }) {
//            Text(text = "Register")
//        }
//        Button(onClick = {
//            coroutineScope.launch {
//                //                // Provider LOGIN
//                val result = RetrofitClient.userRegisterationApiService().login(providerLoginData)
//                Log.d("token ?", "TestScreenForApi: Token Finish ${accessToken}")
//
//                if (result.isSuccessful) {
//                    val loginResponse = result.body()
//                    accessToken = loginResponse
//                    Log.d("login done ", "TestScreenForApi: Token Finish ${accessToken}")
//
//                    if (loginResponse != null) {
//                        Log.d("LogIn Succ", "login Token : ${loginResponse.access}")
//                    } else {
//                        Log.d("Errorrr", "Blabla")
//                    }
//                } else {
//                    Log.d("Unhandle Error", "HTTP status code: ${result.code()}")
//
//                }
//            }
//        }) {
//            Text(text = "login")
//        }
//
//
//        Button(onClick = {
//
//            coroutineScope.launch {
//
////                test =
////                        // Put Wanted Id
////                    RetrofitClient.userRegisterationApiService().getProvidersSearch(2)
//
//                Log.d(
//                    "Get Provider Data TestScreenForApi",
//                    //  Put data you want to see
//                    "TestScreenForApi:  Provider Data ${test!!} "
//                )
//
//
////                // Provider DATA
////                getProviderData = accessToken?.access?.let {
////                    RetrofitClient.userRegisterationApiService()
////                        .getReturnedProviderData("Bearer $it")
////                }
////                Log.d(
////                    "Get Provider Data TestScreenForApi",
////                    "TestScreenForApi:  Provider Data ${getuserData!!.user} || ${getuserData!!.username} || ${getuserData!!.phone} || ${getuserData!!.email} || ${getuserData!!.city} || ${getuserData!!.image}"
////                )
////
////
////                // UPDATE Provider DATA
////                returnUpdateProviderData = accessToken?.access?.let {
////                    RetrofitClient.userRegisterationApiService().updateProviderData(
////                        "Bearer $it",
////                        updatedProviderData
////                    )
////                }
////                Log.d(
////                    "Get UpdatedUser Data TestScreenForApi",
////                    "TestScreenForApi: Updated UserData ${returnUpdateUserData!!.user} || ${returnUpdateUserData!!.username} || ${returnUpdateUserData!!.phone} || ${returnUpdateUserData!!.email} || ${returnUpdateUserData!!.city} || ${returnUpdateUserData!!.image}"
////                )
////
//
////                // USER REGISTER
////                RetrofitClient.userRegisterationApiService().postRegister(registerUserData)
////                Log.d("Register TestScreenForApi", "TestScreenForApi: Register Finish")
//
//                // USER LOGIN
////                val response = RetrofitClient.userRegisterationApiService().login(loginData)
////                Log.d("Token TestScreenForApi", "TestScreenForApi: Token Finish ${accessToken}")
////
////                if (response.isSuccessful) {
////                    val loginResponse = response.body()
////                    accessToken = loginResponse
////                    if (loginResponse != null) {
////                        Log.d("LogIn Succ", "login Token : ${loginResponse.access}")
////                    } else {
////                        Log.d("Errorrr", "Blabla")
////                    }
////                } else {
////                    Log.d("Unhandle Error", "HTTP status code: ${response.code()}")
////
////                }
////
////                // USER DATA
////                getuserData = accessToken?.access?.let {
////                    RetrofitClient.userRegisterationApiService().getReturnedUserData("Bearer $it")
////                }
////
////
////                Log.d(
////                    "Get User Data TestScreenForApi",
////                    "TestScreenForApi: UserData ${getuserData!!.user} || ${getuserData!!.email} || ${getuserData!!.city} || ${getuserData!!.image}||${getuserData!!.id.toString()}"
////                )
////
////                // UPDATE USER DATA
////                returnUpdateUserData = accessToken?.access?.let {
////                    RetrofitClient.userRegisterationApiService().updateUserData(
////                        "Bearer $it",
////                        updatedUserData
////                    )
////                }
////                Log.d(
////                    "Get UpdatedUser Data LoginScreen2",
////                    "LoginScreen2: Updated UserData ${returnUpdateUserData!!.user} || ${returnUpdateUserData!!.username} || ${returnUpdateUserData!!.phone} || ${returnUpdateUserData!!.email} || ${returnUpdateUserData!!.city} || ${returnUpdateUserData!!.image}"
////                )
////
//            }
//
//            isClicked = true
//        }, enabled = !isClicked) {
//            if (isClicked)
//                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
//            else
//                Text(text = "Update User")
//        }
//    }
//}
//fun drawableToFile(context: Context, drawableId: Int, fileName: String): File {
//    // Get the drawable resource
//    val drawable = ContextCompat.getDrawable(context, drawableId)
//
//    // Create a file in the internal storage
//    val file = File(context.cacheDir, fileName)
//
//    // Convert drawable to Bitmap
//    val bitmap = (drawable as BitmapDrawable).bitmap
//
//    // Write the file
//    FileOutputStream(file).use { out ->
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
//    }
//    Log.d("fffff", "drawableToFile: $file")
//    return file
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun TestScreenForApiPreview() {
//    TestScreenForApi()
//}
//
