package com.example.graduationproject.data.retrofit

import com.example.graduationproject.data.LoginRequest
import com.example.graduationproject.data.LoginResponse
import com.example.graduationproject.data.Register
import com.example.graduationproject.data.RequsetUpdateData
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.ReturnedUserData
import com.example.graduationproject.data.ServicesCategories
import com.example.graduationproject.data.ViewProfileData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @POST("/api/register/")
    suspend fun postRegister(@Body register: Register): Response<Register>

    @GET("/api/get_service/")
    suspend fun getServices(): ServicesCategories

    @POST("/api/token/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
    @GET("api/selec_provider/{id}")
    suspend fun viewProviderProfile(@Path("id") id: Int,@Header("Authorization") token: String): ViewProfileData

    @GET("api/userinfo/")
    suspend fun getReturnedUserData(@Header("Authorization") token: String): ReturnedUserData

    @GET("api/all/{id}")
    suspend fun getProvidersSearch(@Path("id") id: Int): List<ReturnedProviderData>

    @PUT("api/userinfo/update")
    fun updateUserData(
        @Header("Authorization") token: String,

        @Part("username") username: RequestBody,
        @Part("email") email: RequestBody,
        @Part("address") address: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("city") city: RequestBody,
        @Part image: MultipartBody.Part
    ): Call<ResponseBody>

    @Multipart
    @POST("/api/provider_register/")
    fun postRegisterProvider(
        @Part("username") username: RequestBody,
        @Part("password") password: RequestBody,
        @Part("email") email: RequestBody,
        @Part("address") address: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("city") city: RequestBody,
        @Part("profession") profession: RequestBody,
        @Part("fixed_salary") fixed_salary: RequestBody,
        @Part id_image: MultipartBody.Part
    ): Call<ResponseBody>

    @GET("api/providerinfo/")
    suspend fun getReturnedProviderData(@Header("Authorization") token: String): ReturnedProviderData

    @Multipart
    @PUT("api/providerinfo/update")
    fun updateProviderData(
        @Header("Authorization") token: String,

        @Part("username") username: RequestBody,
        @Part("password") password: RequestBody,
        @Part("email") email: RequestBody,
        @Part("address") address: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("city") city: RequestBody,
        @Part("profession") profession: RequestBody,
/*        @Part("ratings") ratings: RequestBody,
        @Part("service_id") service_id: RequestBody,
        @Part("user") user: RequestBody,*/
        @Part("fixed_salary") fixed_salary: RequestBody,
        @Part image: MultipartBody.Part
    ): Call<ResponseBody>


}