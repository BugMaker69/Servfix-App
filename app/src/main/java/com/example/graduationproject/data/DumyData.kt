package com.example.graduationproject.data

data class ServiceProviderCard(
    val name: String = "Ahmed Ramadan",
    val location: String = "Alexandria",
    val rate: Double = 5.1,
    val transactionsNum: Int = 100,
    val fee: Double = 400.00,
    val feeType: String = "Fixed Fee"
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name, "${name.first()}"
        )
        return matchingCombinations.any { it.contains(query, ignoreCase = true) }
    }

}


