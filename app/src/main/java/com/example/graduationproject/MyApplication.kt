package com.example.graduationproject

import android.app.Application
import android.content.Context

class MyApplication:  Application() {
    init {
        application =this
    }
    companion object{
        private lateinit var application:Application
        fun getApplicationContext(): Context = application.applicationContext
    }
}