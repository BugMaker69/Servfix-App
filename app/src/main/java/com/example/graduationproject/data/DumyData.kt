package com.example.graduationproject.data

import com.google.gson.annotations.SerializedName


//"phone": "01210437593",
//"username": "ahmed3",
//"password": "pbkdf2_sha256$720000$hl4aP6I59Zbn2hXOoL2Bn9$Hym+ki7gkES5pmAz34YWCTl3Sn90qJMv4S9/DSl56jw=",
//"email": "ahmed3@gmail.com",
//"fixed_salary": "200",
//"image": null,
//"ratings": "4.00",
//"city": "alex",
//"address": "alex",
//"id": 4,
//"user": 5,
//"profession": "carpenter",
//"service_id": 2
//data class (val phone:String,val userName:String,val fixed_salary: String,val image: Any,val ratings: String,val city: String,val address: String,val profession: String)


data class Register(
    @SerializedName("username") val userName: String = "",
    val password: String,
    val email: String,
    val address: String,
    val phone: String,
    val city: String,
)


data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val refresh: String,
    val access: String
)
data class MoreTokn(
    val access: String
)
data class  SearchProviders(@SerializedName("providers" ) var providers:List<ReturnedProviderData>)
data class ViewProfileData (

    @SerializedName("provider" ) var provider : Provider?         = Provider(),
    @SerializedName("works"    ) var works    : ArrayList<Works> = arrayListOf()

)
data class Works (

    @SerializedName("id"    ) var id    : Int?    = null,
    @SerializedName("image" ) var image : String? = null

)

data class Image (
    @SerializedName("id") var id: Int? = null,
    @SerializedName("image") var image: String? = null
)

data class RefreshRequest(val refresh: String)
data class RefreshResponse(val access: String)
data class Provider (

    @SerializedName("phone"        ) var phone       : String? = null,
    @SerializedName("username"     ) var username    : String? = null,
    @SerializedName("password"     ) var password    : String? = null,
    @SerializedName("email"        ) var email       : String? = null,
    @SerializedName("fixed_salary" ) var fixedSalary : String? = null,
    @SerializedName("image"        ) var image       : String? = null,
    @SerializedName("ratings"      ) var ratings     : String? = null,
    @SerializedName("city"         ) var city        : String? = null,
    @SerializedName("address"      ) var address     : String? = null,
    @SerializedName("id"           ) var id          : Int?    = null,
    @SerializedName("user"         ) var user        : Int?    = null,
    @SerializedName("profession"   ) var profession  : String? = null,
    @SerializedName("service_id"   ) var serviceId   : Int?    = null

)

data class ReturnedUserData(
    val address: String,
    val city: String,
    val email: String,
    val id: Int,
    val image: Any,
    val password: String,
    val phone: String,
    val user: Int,
    val username: String
)

data class RequsetUpdateData(
    val address: String,
    val city: String,
    val email: String,
    val image: Any,
    val phone: String,
)

data class UserData(
    val email: String,
    val image: Any,
    val password: String,
    val phone: String,
    val username: String
)

data class ProviderData(
    val address: String,
    val city: String,
    val email: String,
    val fixed_salary: String,
    val id_image: String,
    val password: String,
    val phone: String,
    val profession: String,
    val username: String
)

data class Test(val details: String)

data class ReturnedProviderData(
    val address: String,
    val city: String,
    val email: String,
    val fixed_salary: String,
    val id: Int,
    var image: Any,
    val password: String,
    val phone: String,
    val profession: String,
    val ratings: String,
    val service_id: Int,
    val user: Int,
    val username: String
)
data class ServicesCategories (

    @SerializedName("service" ) var service : ArrayList<Services> = arrayListOf()

)
data class FavouritesList(
    @SerializedName("provider_favourite")
    val providerFavourite: List<ReturnedProviderData>,
)

data class Services(
    val name: String,
    val description: String,
    var image: String,
    val id: Int
)
data class AllNotificationItem(
    val id: Int,
    val message: String,
    val post: Int
)

class AllNotification : ArrayList<AllNotificationItem>()

data class GetPostDataItem(
    val city: String,
    val id: Int,
    val image: String,
    val problem_description: String,
    val service_name: String
)

class GetPostData : ArrayList<GetPostDataItem>()


data class NewOldPassword(
    val old_password: String,
    val new_password: String
)

data class GetWorksItem(
    val id: Int,
    val image: String,
    val provider_id: Int
)

class GetWorks : ArrayList<GetWorksItem>()


data class GetChatListForProviders(
    var accepted_users : ArrayList<AcceptedUsers> = arrayListOf()
)

data class GetChatListForUsers (

    @SerializedName("accepted_providers" ) var acceptedProviderUsers : ArrayList<AcceptedProviders> = arrayListOf()

)
data class AcceptedProviders (

    @SerializedName("provider_id" ) var id : Int,
    @SerializedName("name"        ) var name       : String,
    @SerializedName("image"       ) var image      : String?=null

)
data class AcceptedUsers( @SerializedName("user_id" )var id:Int, @SerializedName("username")var name:String   ,@SerializedName("image") var image:String?=null)
data class GetPostsForProviderItem(
    val created_at: String,
    val id: Int,
    val image: String,
    val message: String,
    val provider: Int,
    val user: Int
)

class GetPostsForProvider : ArrayList<GetPostsForProviderItem>()


data class GeneralPostAccept(
    val id: Int,
    val post: Int,
    val provider: Int,
    val status: String,
    val timestamp: String,
    val user: Int
)

data class PostStatus(
    val message: String
)