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

//object Injection2 {
//
//    private var userRepository: UserRepository? = null
//
//    fun provideRepository(context: Context): UserRepository {
//        val pref = PreferenceManager.getInstance(context.dataStore)
//        val isLoggedIn = pref.getBoolean(PreferenceManager.KEY_IS_USER_LOGGED_IN, false)
//        val apiService = ApiConfig3.getApiService(isLoggedIn.token)
//
//        if (userRepository == null) {
//            userRepository = UserRepository.getInstance(pref)
//        }
//
//        // Optionally, refresh the repository if the user is not logged in or you have specific conditions
//
//        return userRepository!!
//    }
//
//}
