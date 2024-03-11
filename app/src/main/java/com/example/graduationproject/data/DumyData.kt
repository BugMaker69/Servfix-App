package com.example.graduationproject.data

import com.google.gson.annotations.SerializedName

data class ServiceProviderCard(
    val name: String = "Ahmed Ramadan",
    val location: String = "Alexandria",
    val rate: Double = 5.1,
    val transactionsNum: Int = 100,
    val fee: Double = 400.00,
    val feeType: String = "Fixed Fee",
    val category:String="smith"
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name, "${name.first()}"
        )
        return matchingCombinations.any { it.contains(query, ignoreCase = true) }
    }

}

data class Register(
    @SerializedName("username") val userName:String="",
    val password:String,
    val email:String,
    val address:String,
    val phone :String,
    val city :String,
    )


