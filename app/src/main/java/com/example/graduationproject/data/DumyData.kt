package com.example.graduationproject.data

import com.google.gson.annotations.SerializedName



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

//data class MoreTokn(
//    val access: String
//)

data class ChatListItem(
    val terminate_id: Int,
    val id: Int,
    val name: String,
    val image: String? = null,
    val content: String? = null,
    val unseenMessages: String? = null,
    val phone: String? = null
)


data class ChatDetails(

    @SerializedName("content") var content: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("unseen_messages") var unseenMessages: String? = null


)

data class TerminateMessage(@SerializedName("message") var message: String)
data class SendChatMessage(@SerializedName("content") var content: String)
data class Chat(

    @SerializedName("id") var id: Int,
    @SerializedName("content") var content: String,
    @SerializedName("timestamp") var timestamp: String,
    @SerializedName("is_seen") var isSeen: Boolean,
    @SerializedName("sender") var sender: Int,
    @SerializedName("recipient") var recipient: Int

)

data class SearchProviders(@SerializedName("providers") var providers: List<ReturnedProviderData>)
data class ViewProfileData(

    @SerializedName("provider") var provider: Provider? = Provider(),
    @SerializedName("works") var works: ArrayList<Works> = arrayListOf(),
    @SerializedName("is_favourite") var isFavourite: Boolean = false


)

data class Works(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("image") var image: String? = null

)

data class Image(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("image") var image: String? = null
)

data class RefreshRequest(val refresh: String)
data class RefreshResponse(val access: String)
data class Provider(

    @SerializedName("phone") var phone: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("fixed_salary") var fixedSalary: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("ratings") var ratings: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("user") var user: Int? = null,
    @SerializedName("profession") var profession: String? = null,
    @SerializedName("service_id") var serviceId: Int? = null

)

//data class LogOutResponse(val detail: String)
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

//data class RequsetUpdateData(
//    val address: String,
//    val city: String,
//    val email: String,
//    val image: Any,
//    val phone: String,
//)
//
//data class UserData(
//    val email: String,
//    val image: Any,
//    val password: String,
//    val phone: String,
//    val username: String
//)
//
//data class ProviderData(
//    val address: String,
//    val city: String,
//    val email: String,
//    val fixed_salary: String,
//    val id_image: String,
//    val password: String,
//    val phone: String,
//    val profession: String,
//    val username: String
//)

data class Test(val details: String)
data class Test2(val detail: String)


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

data class ServicesCategories(

    @SerializedName("service") var service: ArrayList<Services> = arrayListOf()

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
    val created_at: String,
    val id: Int,
    val is_seen: Boolean,
    val message: String,
    val post: Int,
    val recipient1: Int,
    val recipient2: Any
)

/*data class AllNotificationItem(
    val id: Int,
    val message: String,
    val post: Int
)*/

class AllNotification : ArrayList<AllNotificationItem>()

data class AllNotificationItemSpecific(
    val created_at: String,
    val id: Int,
    val image: String,
    val message: String,
    val post: Int,
    val provider_recipient: Int,
    val user_recipient: Any
)

class AllNotificationSpecific : ArrayList<AllNotificationItemSpecific>()

class AllNotifications(
    val allNotificationItem: List<AllNotificationItem>,
    val allNotificationItemSpecific: List<AllNotificationItemSpecific>,
)

data class Email(
    val email: String
)


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

data class SpecificNotificationItemByIdItem(
    val id: Int,
    val image: String,
    val message: String
)


//class SpecificNotificationItemById : ArrayList<SpecificNotificationItemByIdItem>()


/*data class GetWorksItem(
    val id: Int,
    val image: String,
    val provider_id: Int
)

class GetWorks : ArrayList<GetWorksItem>()*/

data class GetWorksItem(
    val id: Int,
    val image: String
)

data class Rate(
    val rating: Int,
)

class GetWorks : ArrayList<GetWorksItem>()


sealed class ChatContactList {
    data class UserResponse(val accepted_users: List<AcceptedUsers>) : ChatContactList()
    data class ProviderResponse(val accepted_providers: List<AcceptedProviders>) : ChatContactList()
}


//data class GetChatListForUsers(
//
//    @SerializedName("accepted_providers") var acceptedProviderUsers: ArrayList<AcceptedProviders> = arrayListOf()
//
//)

data class AcceptedProviders(

    @SerializedName("provider_id") var terminate_id: Int,


    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("image") var image: String? = null,
    @SerializedName("phone") var phone: String


)

data class AcceptedUsers(
    @SerializedName("user_id") var terminate_id: Int,
    @SerializedName("id") var id: Int,
    @SerializedName("username") var name: String,
    @SerializedName("image") var image: String? = null
)

data class GetPostsForProviderItem(
    val created_at: String,
    val id: Int,
    val image: String,
    val is_accepted: Boolean,
    val is_rejected: Boolean,
    val message: String,
    val provider: Int,
    val user: Int
)

/*data class GetPostsForProviderItem(
    val created_at: String,
    val id: Int,
    val image: String,
    val message: String,
    val provider: Int,
    val user: Int
)*/

class GetPostsForProvider : ArrayList<GetPostsForProviderItem>()


data class GeneralPostAccept(
    var details: String
)

/*data class GeneralPostAccept(
    val id: Int,
    val post: Int,
    val provider: Int,
    val status: String,
    val timestamp: String,
    val user: Int
)*/

data class PostStatus(
    val message: String
)

data class ForgetResetPassword(
    val confirmPassword: String,
    val password: String
)