package com.example.restaurants.di

import android.app.Application
import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val application = Application()

    @Singleton
    @Provides
    fun getContext(): Context {
        return application
    }

    @Singleton
    @Provides
    fun getReference(): FirebaseFirestore {
        return Firebase.firestore
    }

}