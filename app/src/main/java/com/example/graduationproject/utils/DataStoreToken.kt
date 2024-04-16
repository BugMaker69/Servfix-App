package com.example.graduationproject.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.graduationproject.MyApplication
import kotlinx.coroutines.flow.first

class DataStoreToken {
    companion object{
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        val TOKEN= stringPreferencesKey("token")

    }
     suspend fun getToken (): String = MyApplication.getApplicationContext().dataStore.data.first()[TOKEN]?:""

     suspend fun saveToken(name:String){
         MyApplication.getApplicationContext().dataStore.edit {preferences->
            preferences[TOKEN]=name

        }

    }
}