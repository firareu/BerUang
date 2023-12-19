package com.capstone.beruang.di

import android.content.Context
import com.capstone.beruang.data.repository.Repository
import com.example.submission.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
//        val database = UserRoomDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return Repository(apiService)
    }
}

/*
object Injection {
    fun provideRepository(context: Context): Repository {
        val database = UserRoomDatabase.getDatabase(context)
        //val apiService = ApiConfig.getApiService()
        return Repository(database,context)*/
