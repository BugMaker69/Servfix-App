package com.example.graduationproject.data.repositories

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.example.graduationproject.MyApplication
import com.example.graduationproject.data.AllNotification
import com.example.graduationproject.data.GetPostData
import com.example.graduationproject.data.GetPostsForProvider
import com.example.graduationproject.data.GetWorks
import com.example.graduationproject.data.LoginRequest
import com.example.graduationproject.data.LoginResponse
import com.example.graduationproject.data.NewOldPassword
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.ReturnedUserData
import com.example.graduationproject.data.retrofit.RetrofitClient
import com.example.graduationproject.utils.DataStoreToken
import com.example.graduationproject.utils.FileUtils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class AddProviderRepository {

    val connectionError = MutableLiveData("")
    val serverResponse = MutableLiveData("")

    var dataStoreToken = DataStoreToken()
    fun restAddProductVariables() {
        connectionError.value = ""
        serverResponse.value = ""
    }

    fun UploadProvider(
        address: String,
        city: String,
        email: String,
        fixed_salary: String,
        id_image: Uri,
        password: String,
        phone: String,
        profession: String,
        username: String
    ) {

        val fileToSend = prepareFilePart("id_image", id_image)
        val addressRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            address
        )
        val cityRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            city
        )
        val emailRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            email
        )
        val fixed_salaryRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            fixed_salary
        )
        val passwordRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            password
        )
        val phoneRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            phone
        )
        val professionRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            profession
        )
        val usernameRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            username
        )

        RetrofitClient.userRegisterationApiService().postRegisterProvider(
            address = addressRequestBody,
            city = cityRequestBody,
            email = emailRequestBody,
            fixed_salary = fixed_salaryRequestBody,
            password = passwordRequestBody,
            username = usernameRequestBody,
            profession = professionRequestBody,
            phone = phoneRequestBody,
            id_image = fileToSend
        ).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.body() != null && response.isSuccessful) {
                    try {

                        if (response.code() == 201) {
                            serverResponse.value = "uploaded"


                        } else {

                            connectionError.value = response.errorBody().toString()
                        }
                    } catch (e: Exception) {
                        connectionError.value = e.message.toString()
                    }
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                connectionError.value = t.message.toString()
            }
        })
    }

    private fun prepareFilePart(partName: String, fileUri: Uri): MultipartBody.Part {
        val file: File = FileUtils.getFile(MyApplication.getApplicationContext(), fileUri)
        val requestFile: RequestBody = RequestBody.create(
            MyApplication.getApplicationContext().contentResolver.getType(fileUri)
                ?.toMediaTypeOrNull(), file
        )
        return MultipartBody.Part.createFormData(partName, file.name, requestFile)
    }
    /*suspend fun uploadFilesToServer(context: Context, fileUris: List<Uri>): Boolean {
        val fileUploadService = RetrofitClient.createService(FileUploadService::class.java)

        // Convert URIs to file parts
        val fileParts = mutableListOf<MultipartBody.Part>()
        for (uri in fileUris) {
            val file = File(uri.path!!)
            val requestBody = file.asRequestBody(context.contentResolver.getType(uri)?.toMediaTypeOrNull())
            val filePart = MultipartBody.Part.createFormData("files", file.name, requestBody)
            fileParts.add(filePart)
        }*/


    suspend fun login(loginRequest: LoginRequest): LoginResponse? {
        val response = RetrofitClient.userRegisterationApiService().login(loginRequest)
        var loginResponse: LoginResponse? = null
        if (response.isSuccessful) {
            loginResponse = response.body()
            if (loginResponse != null) {
                Log.d("bqq", "login: ${loginResponse.refresh + " wait" + loginResponse.access}")
                // Save the tokens
                dataStoreToken.saveToken(loginResponse.access)

            } else {
                Log.d("bqq", "error1")
            }
        } else {
            Log.d(
                "bqq",
                "error2, HTTP status code: ${response.code()}, error body: ${
                    response.errorBody()?.string()
                }"
            )
        }
        return loginResponse
    }

    var getProviderData by mutableStateOf<ReturnedProviderData?>(null)
    var accessToken by mutableStateOf<LoginResponse?>(null)

    suspend fun getProviderData(token: String): ReturnedProviderData? {
        Log.d("Token Value From AddProviderRepo", "${dataStoreToken.getToken()} ")
        val response =
            RetrofitClient.userRegisterationApiService()
                .getReturnedProviderData("Bearer ${dataStoreToken.getToken()}")
        return response
    }

    suspend fun getUserData(token: String): ReturnedUserData? {
        Log.d("Token Value From AddProviderRepo", "${dataStoreToken.getToken()} ")
        val response =
            RetrofitClient.userRegisterationApiService()
                .getReturnedUserData("Bearer ${dataStoreToken.getToken()}")
        return response
    }


    fun updateProviderData(
        token: String,
        address: String,
        city: String,
        email: String,
        fixed_salary: String,
        image: Uri,
        password: String,
        phone: String,
        profession: String,
        username: String,
//        ratings : String,
//        service_id : Int,
//        user : Int,
    ) {

        val fileToSend = prepareFilePart("image", image)
        val addressRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            address
        )
        val cityRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            city
        )
        val emailRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            email
        )
        val fixed_salaryRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            fixed_salary
        )
        val passwordRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            password
        )
        val phoneRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            phone
        )
        val professionRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            profession
        )
        val usernameRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            username
        )
        /*        val ratingsRequestBody: RequestBody = RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    ratings
                )
                val service_idRequestBody: RequestBody = RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    service_id.toString()
                )
                val userRequestBody: RequestBody = RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    user.toString()
                )*/

        RetrofitClient.userRegisterationApiService().updateProviderData(
            token = "Bearer $dataStoreToken",
            address = addressRequestBody,
            city = cityRequestBody,
            email = emailRequestBody,
            fixed_salary = fixed_salaryRequestBody,
            password = passwordRequestBody,
            username = usernameRequestBody,
            profession = professionRequestBody,
            phone = phoneRequestBody,
            /*            ratings = ratingsRequestBody,
                        service_id = service_idRequestBody,
                        user = userRequestBody,*/
            image = fileToSend
        ).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.body() != null && response.isSuccessful) {
                    try {

                        if (response.code() == 201) {
                            serverResponse.value = "Updated"
                            Log.d(
                                "Updated",
                                "onResponse: Updated ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )

                        } else {
                            Log.d(
                                "Error",
                                "onResponse: Updated Error ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )

                            connectionError.value = response.errorBody().toString()
                        }
                    } catch (e: Exception) {
                        Log.d(
                            "Catched Error Updated",
                            "onResponse: Updated Catched Error  ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                        )

                        connectionError.value = e.message.toString()
                    }
                }
                Log.d("onFailure Error Updated", "onResponse: Updated Nothing Happen Why ")
                Log.d(
                    "onFailure Error Updated",
                    "onResponse: Updated onFailure Error ${response} ||  ${response.isSuccessful} ||  ${response.message()} ||  ${response.code()} ||  ${response.errorBody()} ||  ${response.headers()} ||  ${response.raw()} || "
                )

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(
                    "onFailure Error Updated",
                    "onResponse: Updated onFailure Error ${t} ||  ${t.message} ||  ${t.cause} ||  ${t.localizedMessage} ||  ${t.stackTrace} ||  ${t.suppressed} || "
                )

                connectionError.value = t.message.toString()
            }
        })
    }


    fun updateUserData(
        token: String,
        address: String,
        city: String,
        email: String,
        image: Uri,
        phone: String,
        username: String,
    ) {

        val fileToSend = prepareFilePart("image", image)
        val addressRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            address
        )
        val cityRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            city
        )
        val emailRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            email
        )
        val phoneRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            phone
        )
        val usernameRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            username
        )

        RetrofitClient.userRegisterationApiService().updateUserData(
            token = "Bearer $dataStoreToken",
            address = addressRequestBody,
            city = cityRequestBody,
            email = emailRequestBody,
            username = usernameRequestBody,
            phone = phoneRequestBody,
            image = fileToSend
        ).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.body() != null && response.isSuccessful) {
                    try {

                        if (response.code() == 201) {
                            serverResponse.value = "Updated"
                            Log.d(
                                "Updated",
                                "onResponse: Updated ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )

                        } else {
                            Log.d(
                                "Error",
                                "onResponse: Updated Error ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )

                            connectionError.value = response.errorBody().toString()
                        }
                    } catch (e: Exception) {
                        Log.d(
                            "Catched Error Updated",
                            "onResponse: Updated Catched Error  ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                        )

                        connectionError.value = e.message.toString()
                    }
                }
                Log.d("onFailure Error Updated", "onResponse: Updated Nothing Happen Why ")
                Log.d(
                    "onFailure Error Updated",
                    "onResponse: Updated onFailure Error ${response} ||  ${response.isSuccessful} ||  ${response.message()} ||  ${response.code()} ||  ${response.errorBody()} ||  ${response.headers()} ||  ${response.raw()} || "
                )

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(
                    "onFailure Error Updated",
                    "onResponse: Updated onFailure Error ${t} ||  ${t.message} ||  ${t.cause} ||  ${t.localizedMessage} ||  ${t.stackTrace} ||  ${t.suppressed} || "
                )

                connectionError.value = t.message.toString()
            }
        })
    }

    suspend fun shareCreatePost(
        token: String,
        city: String,
        service_name: String,
        problem_description: String,
        image: Uri
//        images: List<Uri>
    ) {

//        val photoParts = mutableListOf<MultipartBody.Part>()
//
//        images.forEachIndexed{ index, photoUri ->
//            val fileToSend = prepareFilePart("image", photoUri)
//            val file = File(photoUri.path)
//            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
//            val photoPart = MultipartBody.Part.createFormData("photo$index", file.name, requestFile)
//            photoParts.add(fileToSend)
//        }

        val fileToSend = prepareFilePart("image", image)


        val cityRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            city
        )

        val serviceNameRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            service_name
        )

        val problemDescriptionRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            problem_description
        )

        RetrofitClient.userRegisterationApiService().shareCreatePost(
            token = "Bearer $dataStoreToken",
            city = cityRequestBody,
            serviceName = serviceNameRequestBody,
            problemDescription = problemDescriptionRequestBody,
            image = fileToSend
        ).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.body() != null && response.isSuccessful) {
                    try {

                        if (response.code() == 201) {
                            serverResponse.value = "Updated"
                            Log.d(
                                "Updated",
                                "onResponse: Updated ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )

                        } else {
                            Log.d(
                                "Error",
                                "onResponse: Updated Error ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )

                            connectionError.value = response.errorBody().toString()
                        }
                    } catch (e: Exception) {
                        Log.d(
                            "Catched Error Updated",
                            "onResponse: Updated Catched Error  ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                        )

                        connectionError.value = e.message.toString()
                    }
                }
                Log.d("onFailure Error Updated", "onResponse: Updated Nothing Happen Why ")
                Log.d(
                    "onFailure Error Updated",
                    "onResponse: Updated onFailure Error ${response} ||  ${response.isSuccessful} ||  ${response.message()} ||  ${response.code()} ||  ${response.errorBody()} ||  ${response.headers()} ||  ${response.raw()} || "
                )

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(
                    "onFailure Error Updated",
                    "onResponse: Updated onFailure Error ${t} ||  ${t.message} ||  ${t.cause} ||  ${t.localizedMessage} ||  ${t.stackTrace} ||  ${t.suppressed} || "
                )

                connectionError.value = t.message.toString()
            }
        })

    }


    suspend fun getAllNotifications(): AllNotification{
        val response = RetrofitClient.userRegisterationApiService().getAllNotifications("Bearer ${dataStoreToken.getToken()}")
        return response
    }

    suspend fun getPostById(id:Int):GetPostData{
        val response = RetrofitClient.userRegisterationApiService().getPostById("Bearer ${dataStoreToken.getToken()}",id)
        return response
    }

    suspend fun getPostsForProvider():GetPostsForProvider{
       val response = RetrofitClient.userRegisterationApiService().getPostsForProvider("Bearer ${dataStoreToken.getToken()}")
        return response
    }

    suspend fun changePassword(newOldPassword: NewOldPassword){
        RetrofitClient.userRegisterationApiService().changePassword("Bearer ${dataStoreToken.getToken()}",
            newOldPassword
        ).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.body() != null && response.isSuccessful){
                    try {
                        if (response.code() == 200){
                            serverResponse.value = "Updated"
                            Log.d(
                                "Password Updated",
                                "onResponse: Updated ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )
                        }else{
                            connectionError.value=response.errorBody().toString()
                            Log.d(
                                "Password Error",
                                "onResponse: Updated Error ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )
                        }
                    }catch (e:Exception){
                        connectionError.value=e.message.toString()
                        Log.d(
                            "Catched Error Password Updated",
                            "onResponse: Updated Catched Error  ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                        )
                    }
                }
                else {
                    Log.d(
                        "Else onFailure Error Password Updated",
                        "onResponse: Updated Nothing Happen Why "
                    )
                    Log.d(
                        "Else onFailure Error Password Updated",
                        "onResponse: Updated onFailure Error ${response} ||  ${response.isSuccessful} ||  ${response.message()} ||  ${response.code()} ||  ${response.errorBody()} ||  ${response.headers()} ||  ${response.raw()} || "
                    )
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(
                    "onFailure Error Password Updated",
                    "onResponse: Updated onFailure Error ${t} ||  ${t.message} ||  ${t.cause} ||  ${t.localizedMessage} ||  ${t.stackTrace} ||  ${t.suppressed} || "
                )
                connectionError.value = t.message.toString()
            }
        })
    }
    suspend fun getAllWorks():GetWorks{
        val response = RetrofitClient.userRegisterationApiService().getAllWorks("Bearer ${dataStoreToken.getToken()}")
        return response
    }

    suspend fun deleteWork(id: Int){
        RetrofitClient.userRegisterationApiService().deleteWork("Bearer ${dataStoreToken.getToken()}",id)
    }

    suspend fun addWork(image: Uri){
        val fileToSend = prepareFilePart("image", image)
        RetrofitClient.userRegisterationApiService().addWork("Bearer ${dataStoreToken.getToken()}",fileToSend)
    }



}