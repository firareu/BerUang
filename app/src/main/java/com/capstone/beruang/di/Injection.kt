package com.capstone.beruang.di

import android.content.Context
import com.capstone.beruang.data.repository.AllocationRepository
import com.capstone.beruang.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): AllocationRepository {
//        val database = UserRoomDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return AllocationRepository(apiService)
    }
}

/*
object Injection {
    fun provideRepository(context: Context): Repository {
        val database = UserRoomDatabase.getDatabase(context)
        //val apiService = ApiConfig.getApiService()
        return Repository(database,context)*/
