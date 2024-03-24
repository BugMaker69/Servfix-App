package com.example.graduationproject.data

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.graduationproject.data.retrofit.RetrofitClient
import com.example.graduationproject.utils.FileUtils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class AddProviderRepository constructor(private val ctx: Context) {

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
        val file: File = FileUtils.getFile(ctx, fileUri)
        val requestFile: RequestBody = RequestBody.create(
            ctx.contentResolver.getType(fileUri)!!.toMediaTypeOrNull(), file
        )
        return MultipartBody.Part.createFormData(partName, file.name, requestFile)
    }

}