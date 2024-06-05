package com.example.graduationproject.data.retrofit

import com.example.graduationproject.data.AllNotification
import com.example.graduationproject.data.AllNotificationSpecific
import com.example.graduationproject.data.Chat
import com.example.graduationproject.data.ChatContactList
import com.example.graduationproject.data.ChatDetails
import com.example.graduationproject.data.Email
import com.example.graduationproject.data.FavouritesList
import com.example.graduationproject.data.ForgetResetPassword
import com.example.graduationproject.data.GeneralPostAccept
import com.example.graduationproject.data.GetChatListForUsers
import com.example.graduationproject.data.GetPostData
import com.example.graduationproject.data.GetPostsForProvider
import com.example.graduationproject.data.GetPostsForProviderItem
import com.example.graduationproject.data.GetWorks
import com.example.graduationproject.data.LoginRequest
import com.example.graduationproject.data.LoginResponse
import com.example.graduationproject.data.NewOldPassword
import com.example.graduationproject.data.PostStatus
import com.example.graduationproject.data.Rate
import com.example.graduationproject.data.RefreshRequest
import com.example.graduationproject.data.RefreshResponse
import com.example.graduationproject.data.Register
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.ReturnedUserData
import com.example.graduationproject.data.SearchProviders
import com.example.graduationproject.data.SendChatMessage
import com.example.graduationproject.data.ServicesCategories
import com.example.graduationproject.data.SpecificNotificationItemById
import com.example.graduationproject.data.TerminateMessage
import com.example.graduationproject.data.Test
import com.example.graduationproject.data.Test2
import com.example.graduationproject.data.ViewProfileData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("/notification/terminate_chat/{id}/")
    suspend fun terminateChat(@Header("Authorization") token: String, @Path("id") id: Int): TerminateMessage

    @DELETE("/notification/delete_message/{id}")
    suspend fun deleteChat(@Header("Authorization") token: String,@Path("id") id: Int)

    @POST("/notification/chat/{id}")
    suspend fun AddChat(@Header("Authorization") token: String,@Path("id") id: Int,@Body content:SendChatMessage)
    @GET("/notifi/Chatmessages")
    suspend fun getChatListDetails(@Header("Authorization") token: String): List<ChatDetails>

    @GET("/notifi/chatforspecificperson/{id}")
    suspend fun getChat(@Path("id") id: Int, @Header("Authorization") token: String): List<Chat>

    @GET("/notification/immediate-notifications/")
    suspend fun getAllSpecificNotifications(
        @Header("Authorization") token: String,
    ): AllNotificationSpecific

    @GET("/notification/provider/post/{id}/")
    suspend fun getSpecificNotificationById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): GetPostsForProviderItem

    @GET("/notifi/allnotification/")
    suspend fun getAllNotifications(
        @Header("Authorization") token: String,
    ): AllNotification

    @GET("/notification/get_post_by_id/{id}")
    suspend fun getPostById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): GetPostData

    @POST("/api/reset_password/{id}")
    fun resetPassword(
        @Body password: ForgetResetPassword,
        @Path("id") id: String
    ): Call<ResponseBody>

    @POST("/api/forgot_password/")
    fun forgotPassword(
        @Body email: Email,
    ): Call<ResponseBody>

    @DELETE("/api/delete-account/")
    suspend fun deleteAccount(
        @Header("Authorization") token: String,
    )

    @POST("/api/reviews/{id}")
    suspend fun addReview(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body rate: Rate
    ):Response<Test2>


//    @DELETE("/api/delete-account/{password}")
//    suspend fun deleteAccount(@Header("Authorization") token: String, @Path("password") password: String)

    @GET("/notification/get_accepted_users_and_providers")
    suspend fun getChatContactList(@Header("Authorization") token: String): ChatContactList


//    @GET("/notification/accepted-users-and-providers/")
//    suspend fun getChatListForProviders(@Header("Authorization") token: String): GetChatListForProviders

    @POST("/api/register/")
    suspend fun postRegister(@Body register: Register): Response<Register>

    @GET("/api/get_service/")
    suspend fun getServices(): ServicesCategories

    @GET("/api/show_all_favourites")
    suspend fun showFavourites(@Header("Authorization") token: String): FavouritesList


    @POST("/api/favourite/{id}")
    suspend fun addFavorite(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Response<Test>

    @DELETE("/api/delete_fav/{id}")
    suspend fun deleteFavorite(@Path("id") id: Int, @Header("Authorization") token: String)

    @POST("/api/token/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("api/selec_provider/{id}")
    suspend fun viewProviderProfile(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): ViewProfileData


    @GET("api/userinfo/")
    suspend fun getReturnedUserData(@Header("Authorization") token: String): ReturnedUserData

    @GET("/api/get_providers/{id}")
    suspend fun getProvidersSearch(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Query("keyword") keyword: String,
        @Query("minRate") rate: Int,
        @Query("place") place: String,
        @Query("page") page: Int

    ): SearchProviders

    @POST("api/token/refresh/")
    suspend fun moreToken(@Body refreshRequest: RefreshRequest): Response<RefreshResponse>

    @GET("api/all/{id}")
    suspend fun getProviders(@Path("id") id: Int, @Query("page") page: Int): SearchProviders

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

    @Multipart
    @POST("/notification/post_create")
    fun shareCreatePost(
        @Header("Authorization") token: String,

        @Part("city") city: RequestBody,
        @Part("service_name") service_name: RequestBody,
        @Part("problem_description") problem_description: RequestBody,

//        @Part image:MultipartBody.Part
        @Part image: List<MultipartBody.Part>
    ): Call<ResponseBody>


    @Multipart
    @POST("/notification/post/create/{id}/")
    fun shareSpecificPost(
        @Header("Authorization") token: String,

        @Part("message") message: RequestBody,
        @Path("id") id: Int,
        @Part image: List<MultipartBody.Part>
    ): Call<ResponseBody>

    @GET("/notification/provider/posts/")
    suspend fun getPostsForProvider(
        @Header("Authorization") token: String
    ): GetPostsForProvider

    @POST("/notification/post_accept/{id}")
    suspend fun acceptPost(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): GeneralPostAccept

    @POST("/notification/post/accept/{post_id}/")
    suspend fun acceptPostForSpecificProvider(
        @Header("Authorization") token: String,
        @Path("post_id") post_id: Int
    ): PostStatus

    @POST("/notification/post/reject/{post_id}/")
    suspend fun rejectPostForSpecificProvider(
        @Header("Authorization") token: String,
        @Path("post_id") post_id: Int
    ): PostStatus


    @POST("/api/change_password")
    fun changePassword(
        @Header("Authorization") token: String,
        @Body changePassword: NewOldPassword
    ): Call<ResponseBody>

    @GET("/api/all_work")
    suspend fun getAllWorks(
        @Header("Authorization") token: String
    ): GetWorks

    @DELETE("/api/delete_work/{work_id}")
    fun deleteWork(
        @Header("Authorization") token: String,
        @Path("work_id") work_id: Int
    ): Call<ResponseBody>

    @Multipart
    @POST("/api/add_work/")
    fun addWork(
        @Header("Authorization") token: String,
        @Part images: List<MultipartBody.Part>
    ): Call<ResponseBody>


}