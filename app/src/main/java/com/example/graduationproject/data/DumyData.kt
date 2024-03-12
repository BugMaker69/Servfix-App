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


data class UserData(
    val email: String,
    val image: Any,
    val password: String,
    val phone: String,
    val username: String
)