package com.example.graduationproject

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class UserTypeViewModel : ViewModel() {
    val userType = mutableStateOf<UserType?>(null)
}

enum class UserType {
    OwnerPerson,
    HirePerson
}

