package com.example.graduationproject.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.graduationproject.MyApplication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreToken {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        val TOKEN = stringPreferencesKey("token")
        val TOKEN2 = stringPreferencesKey("token2")
        val LOGIN = booleanPreferencesKey("login")
        val TYPE= stringPreferencesKey("type")


    }
    val userType: Flow<String> =  MyApplication.getApplicationContext().dataStore.data.map { preferences ->
        preferences[TYPE] ?: ""
    }
//    suspend fun saveUserType(name: String) {
//        MyApplication.getApplicationContext(). dataStore.edit { preferences ->
//            preferences[TYPE] = name
//        }
//    }

    suspend fun getLogin():Boolean=
        MyApplication.getApplicationContext().dataStore.data.first()[LOGIN] ?: false

    suspend fun saveLoginState(state:Boolean){
    MyApplication.getApplicationContext().dataStore.edit { preferences ->
        preferences[LOGIN] = state

    }

    }
    suspend fun getUserType(): String=
        MyApplication.getApplicationContext().dataStore.data.first()[TYPE] ?: ""


    suspend fun saveUserType(name:String){
        MyApplication.getApplicationContext().dataStore.edit { preferences ->
            preferences[TYPE] = name

        }
    }




    suspend fun getToken(): String =
        MyApplication.getApplicationContext().dataStore.data.first()[TOKEN] ?: ""

    suspend fun saveToken(name: String) {

        MyApplication.getApplicationContext().dataStore.edit { preferences ->
            preferences[TOKEN] = name

        }

    }

    suspend fun saveToken2(name: String) {
        MyApplication.getApplicationContext().dataStore.edit { preferences ->
            preferences[TOKEN2] = name

        }

    }

    suspend fun getToken2(): String =
        MyApplication.getApplicationContext().dataStore.data.first()[TOKEN2] ?: ""

}