package com.example.graduationproject.data.retrofit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface InternetObserver {
    fun observe() :Flow<Status>
    enum class Status{
        Available,
        NotAvailable,
        Loading
    }
}