package com.example.graduationproject.data

import com.google.gson.annotations.SerializedName

data class ServiceProviderCard(
    val name: String = "Ahmed Ramadan",
    val location: String = "Alexandria",
    val rate: Double = 5.1,
    val transactionsNum: Int = 100,
    val fee: Double = 400.00,
    val feeType: String = "Fixed Fee",
    val category: String = "smith"
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name, "${name.first()}"
        )
        return matchingCombinations.any { it.contains(query, ignoreCase = true) }
    }

}
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
data class ServiceProviderSearch (

    @SerializedName("phone"        ) var phone       : String,
    @SerializedName("username"     ) var username    : String,
    @SerializedName("password"     ) var password    : String,
    @SerializedName("email"        ) var email       : String,
    @SerializedName("fixed_salary" ) var fixedSalary : String,
    @SerializedName("ratings"      ) var ratings     : String,
    @SerializedName("city"         ) var city        : String,
    @SerializedName("address"      ) var address     : String,
    @SerializedName("profession"   ) var profession  : String,
    @SerializedName("service_id"   ) var serviceId   : Int

)
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
    val fixed_salary: Double,
    val id_image: String,
    val password: String,
    val phone: String,
    val profession: String,
    val username: String
)

data class ReturnedProviderData(
    val address: String,
    val city: String,
    val email: String,
    val fixed_salary: String,
    val id: Int,
    val image: Any,
    val password: String,
    val phone: String,
    val profession: String,
    val ratings: String,
    val service_id: Int,
    val user: Int,
    val username: String
)