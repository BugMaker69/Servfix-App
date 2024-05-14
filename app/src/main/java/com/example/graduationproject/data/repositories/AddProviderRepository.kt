package com.example.graduationproject.data.repositories

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.example.graduationproject.MyApplication
import com.example.graduationproject.data.AllNotification
import com.example.graduationproject.data.GeneralPostAccept
import com.example.graduationproject.data.GetPostData
import com.example.graduationproject.data.GetPostsForProvider
import com.example.graduationproject.data.GetWorks
import com.example.graduationproject.data.LoginRequest
import com.example.graduationproject.data.LoginResponse
import com.example.graduationproject.data.NewOldPassword
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.ReturnedUserData
import com.example.graduationproject.data.retrofit.ApiService
import com.example.graduationproject.presentation.common.UserType
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
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddProviderRepository @Inject constructor(
    val dataStoreToken: DataStoreToken,
    val apiService: ApiService
) {

    val connectionError = MutableLiveData("")
    val serverResponse = MutableLiveData("")

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

        apiService.postRegisterProvider(
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

    /*    private fun prepareFilePart(partName: String,fileRealPath: String,fileUri: Uri): MultipartBody.Part {
            val file: File = File(fileRealPath)
            val requestFile: RequestBody = RequestBody.create(
                MyApplication.getApplicationContext().contentResolver.getType(fileUri)!!
                    .toMediaTypeOrNull(), file)
            return MultipartBody.Part.createFormData(partName, file.name, requestFile)
        }*/


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
        val response = apiService.login(loginRequest)
        var loginResponse: LoginResponse? = null
        if (response.isSuccessful) {
            loginResponse = response.body()
            if (loginResponse != null) {
                Log.d("bqq", "login: ${loginResponse.refresh + " wait" + loginResponse.access}")
                // Save the tokens
                dataStoreToken.saveToken(loginResponse.access)

                dataStoreToken.saveToken2(loginResponse.refresh)
             //   dataStoreToken.saveLoginState(true)
                Log.d("byy", "login: ${dataStoreToken.getToken2().toString()}")

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

    suspend fun getProviderData(): ReturnedProviderData? {
        Log.d("Token Value From AddProviderRepo", "${dataStoreToken.getToken()} ")

        val response =
            apiService.getReturnedProviderData("Bearer ${dataStoreToken.getToken()}")
        dataStoreToken.saveUserType(UserType.HirePerson.name)
        Log.d("lmno", "getProviderData: ${dataStoreToken.getUserType()}")

        return response
    }

    suspend fun getUserData(): ReturnedUserData? {
        Log.d("Token Value From AddProviderRepo", "${dataStoreToken.getToken()} ")

        val response =
            apiService.getReturnedUserData("Bearer ${dataStoreToken.getToken()}")
        dataStoreToken.saveUserType(UserType.OwnerPerson.name)

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


        apiService.updateProviderData(
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

        apiService.updateUserData(
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
//        image: Uri
//        fileUri: Uri,
//        fileRealPath: List<String>,
        image: List<Uri>,
    ) {

        val photoParts = mutableListOf<MultipartBody.Part>()

        image.forEachIndexed { index, photoUri ->
            val fileToSend = prepareFilePart("image", photoUri)
//            val fileToSend = prepareFilePart("image",fileRealPath[index] ,photoUri)
//            val file = File(photoUri.path)
//            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
//            val photoPart = MultipartBody.Part.createFormData("photo$index", file.name, requestFile)
            photoParts.add(fileToSend)
        }

//        val fileToSend = prepareFilePart("image", image)


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
        Log.d(
            "Before Send",
            "onResponse:  ${image} ||     ${token} ||  ${dataStoreToken} ||   ${dataStoreToken.getToken()} ||   ${photoParts} ||  ${city} ||  ${service_name} ||  ${problem_description}"
        )

        Log.d(
            "Before Send",
            "onResponse: ||  ${cityRequestBody} ||  ${serviceNameRequestBody} ||  ${problemDescriptionRequestBody}"
        )
        apiService.shareCreatePost(
            token = "Bearer ${dataStoreToken.getToken()}",
            city = cityRequestBody,
            service_name = serviceNameRequestBody,
            problem_description = problemDescriptionRequestBody,
            image = photoParts.toList()
        ).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                /*                Log.d(
                                    "WHY ERRor HERRE",
                                    "onResponse: ERRor ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                                )*/
                if (response.body() != null && response.isSuccessful) {
                    try {

                        if (response.code() == 201) {
                            serverResponse.value = "Updated"
                            Log.d(
                                "Post Created Successfully",
                                "onResponse: Post Created ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )

                        } else {
                            Log.d(
                                "Error",
                                "onResponse: Create Post Error ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )

                            connectionError.value = response.errorBody().toString()
                        }
                    } catch (e: Exception) {
                        Log.d(
                            "Catched Error Created",
                            "onResponse: Post Catched Error  ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                        )

                        connectionError.value = e.message.toString()
                    }
                } else {
                    Log.d("onFailure Error Post Created", "onResponse: Updated Nothing Happen Why ")
                    Log.d(
                        "onFailure Error Post Created",
                        "onResponse: Post Created onFailure Error ${response} ||  ${response.isSuccessful} ||  ${response.message()} ||  ${response.code()} ||  ${response.errorBody()} ||  ${response.headers()} ||  ${response.raw()} || "
                    )
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(
                    "onFailure Error Updated",
                    "onResponse: Post Created onFailure Error ${t} ||  ${t.message} ||  ${t.cause} ||  ${t.localizedMessage} ||  ${t.stackTrace} ||  ${t.suppressed} || "
                )

                connectionError.value = t.message.toString()
            }
        })

    }



    suspend fun shareSpecificPost(
        token: String,
        message: String,
        id: Int,
//        fileUri: Uri,
//        fileRealPath: List<String>,
        image: List<Uri>,
    ) {

        val photoParts = mutableListOf<MultipartBody.Part>()

        image.forEachIndexed { index, photoUri ->
            val fileToSend = prepareFilePart("image", photoUri)
            photoParts.add(fileToSend)
        }

        val problemDescriptionRequestBody: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            message
        )
        Log.d(
            "Before Send",
            "onResponse:  ${image} ||     ${token} ||  ${dataStoreToken} ||   ${dataStoreToken.getToken()} ||   ${photoParts} ||  ${message} "
        )

        apiService.shareSpecificPost(
            token = "Bearer ${dataStoreToken.getToken()}",
            message = problemDescriptionRequestBody,
            id = id,
            image = photoParts.toList(),
        ).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.body() != null && response.isSuccessful) {
                    try {

                        if (response.code() == 201) {
                            Log.d(
                                "Post Created Successfully",
                                "onResponse: Post Created ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )

                        } else {
                            Log.d(
                                "Error",
                                "onResponse: Create Post Error ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )

                        }
                    } catch (e: Exception) {
                        Log.d(
                            "Catched Error Created",
                            "onResponse: Post Catched Error  ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                        )

                    }
                } else {
                    Log.d("onFailure Error Post Created", "onResponse: Updated Nothing Happen Why ")
                    Log.d(
                        "onFailure Error Post Created",
                        "onResponse: Post Created onFailure Error ${response} ||  ${response.isSuccessful} ||  ${response.message()} ||  ${response.code()} ||  ${response.errorBody()} ||  ${response.headers()} ||  ${response.raw()} || "
                    )
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(
                    "onFailure Error Updated",
                    "onResponse: Post Created onFailure Error ${t} ||  ${t.message} ||  ${t.cause} ||  ${t.localizedMessage} ||  ${t.stackTrace} ||  ${t.suppressed} || "
                )

            }
        })

    }





    suspend fun changePassword(newOldPassword: NewOldPassword) {
        apiService.changePassword(
            "Bearer ${dataStoreToken.getToken()}",
            newOldPassword
        ).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.body() != null && response.isSuccessful) {
                    try {
                        if (response.code() == 200) {
                            serverResponse.value = "Updated"
                            Log.d(
                                "Password Updated",
                                "onResponse: Updated ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )
                        } else {
                            connectionError.value = response.errorBody().toString()
                            Log.d(
                                "Password Error",
                                "onResponse: Updated Error ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )
                        }
                    } catch (e: Exception) {
                        connectionError.value = e.message.toString()
                        Log.d(
                            "Catched Error Password Updated",
                            "onResponse: Updated Catched Error  ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                        )
                    }
                } else {
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

    suspend fun getAllWorks(): GetWorks {
        val response = apiService.getAllWorks("Bearer ${dataStoreToken.getToken()}")
        return response
    }

    suspend fun deleteWork(id: Int) {
        apiService.deleteWork("Bearer ${dataStoreToken.getToken()}", id)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                    if (response.body() != null && response.isSuccessful) {
                        try {

                            if (response.code() == 201) {
                                Log.d(
                                    "Work deleted Successfully",
                                    "onResponse: Work deleted Successfully ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                                )

                            } else {
                                Log.d(
                                    "Error",
                                    "onResponse: Work deleted Error ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                                )

                            }
                        } catch (e: Exception) {
                            Log.d(
                                "Catched Error Created",
                                "onResponse: Work deleted Error  ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )

                        }
                    } else {
                        Log.d("onFailure Error Work deleted", "onResponse: Updated Nothing Happen Why ")
                        Log.d(
                            "onFailure Error Work deleted",
                            "onResponse: Work deleted onFailure Error ${response} ||  ${response.isSuccessful} ||  ${response.message()} ||  ${response.code()} ||  ${response.errorBody()} ||  ${response.headers()} ||  ${response.raw()} || "
                        )
                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d(
                        "onFailure Error Updated",
                        "onResponse: Work deleted onFailure Error ${t} ||  ${t.message} ||  ${t.cause} ||  ${t.localizedMessage} ||  ${t.stackTrace} ||  ${t.suppressed} || "
                    )

                }
            })

    }

    suspend fun addWork(images: List<Uri>) {

        val photoParts = mutableListOf<MultipartBody.Part>()

        images.forEachIndexed { index, photoUri ->
            val fileToSend = prepareFilePart("images", photoUri)
            photoParts.add(fileToSend)
        }

        apiService.addWork("Bearer ${dataStoreToken.getToken()}", photoParts)
            .enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.body() != null && response.isSuccessful) {
                    try {

                        if (response.code() == 201) {
                            Log.d(
                                "Work Added Successfully",
                                "onResponse: Work Added Successfully ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )

                        } else {
                            Log.d(
                                "Error",
                                "onResponse: Work Added Error ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                            )

                        }
                    } catch (e: Exception) {
                        Log.d(
                            "Catched Error Created",
                            "onResponse: Work Added Error  ${response} ||  ${response.code()} ||  ${response.body()} ||  ${response.errorBody()} ||  ${response.isSuccessful} ||  ||  ${response.message()} ||  ||  ${response.headers()} ||  ||  ${response.raw()} || "
                        )

                    }
                } else {
                    Log.d("onFailure Error Work Added", "onResponse: Updated Nothing Happen Why ")
                    Log.d(
                        "onFailure Error Work Added",
                        "onResponse: Work Added onFailure Error ${response} ||  ${response.isSuccessful} ||  ${response.message()} ||  ${response.code()} ||  ${response.errorBody()} ||  ${response.headers()} ||  ${response.raw()} || "
                    )
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(
                    "onFailure Error Updated",
                    "onResponse: Work Added onFailure Error ${t} ||  ${t.message} ||  ${t.cause} ||  ${t.localizedMessage} ||  ${t.stackTrace} ||  ${t.suppressed} || "
                )

            }
        })

    }
    suspend fun deleteAccount(){
        apiService.deleteAccount("Bearer ${dataStoreToken.getToken()}")
     //   Log.d("wwwww", "deleteAccount: ${ apiService.deleteAccount("Bearer ${dataStoreToken.getToken()}",password).body()}")
    }


}

