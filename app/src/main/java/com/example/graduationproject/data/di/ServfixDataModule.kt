package com.example.graduationproject.data.di

import com.example.graduationproject.data.retrofit.ApiService
import com.example.graduationproject.utils.DataStoreToken
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServfixDataModule {
    @Provides
    @Singleton
    fun provideApiService(): Retrofit {
        return Retrofit.Builder().baseUrl("https://p2kjdbr8-8000.uks1.devtunnels.ms")
            .addConverterFactory(GsonConverterFactory.create()).build()

    }

    @Provides
    fun provideApiInstance(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }
    //  var mAuth: FirebaseAuth = FirebaseAuth.getInstance();

    @Provides
    @Singleton
    fun provideFirebaseAuth():FirebaseAuth{
        return  FirebaseAuth.getInstance()
    }
    @Provides
    fun provideDataStoreToken(): DataStoreToken {
        return DataStoreToken()
    }
}