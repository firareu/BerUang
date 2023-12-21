package com.capstone.beruang.data.repository

import com.capstone.beruang.data.response.LoginResponse
import com.capstone.beruang.data.response.RegisterResponse
import com.capstone.beruang.data.retrofit.ApiConfig3
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class UserRepository(

    private val preferenceManager: PreferenceManager
) {

    private val apiService = ApiConfig3.getApiService()

    fun isUserLoggedIn(): Boolean {
        return preferenceManager.getBoolean(PreferenceManager.KEY_IS_USER_LOGGED_IN, false)
    }

    fun logoutUser() {
        preferenceManager.putBoolean(PreferenceManager.KEY_IS_USER_LOGGED_IN, false)
    }


    suspend fun registerUser(
        name: String,
        email: String,
        password: String,
        dob: String,
        gender: String
    ): RegisterResponse? {
        val requestBody = RequestBody.create(
            "application/json".toMediaTypeOrNull(),
            """
            {
                "name": "$name",
                "email": "$email",
                "password": "$password",
                "dob": "$dob",
                "gender": "$gender"
            }
        """.trimIndent()
        )

        return try {
            val response = apiService.registerUser(requestBody)

            if (response.isSuccessful) {
                response.body()
            } else {
                // Handle error
                null
            }

        } catch (e: Exception) {
            // Handle exception
            null
        }
    }

    suspend fun loginUser(email: String, password: String): LoginResponse? {
        val requestBody = RequestBody.create(
            "application/json".toMediaTypeOrNull(),
            "{\"email\":\"$email\",\"password\":\"$password\"}"
        )

        return try {
            val response = apiService.loginUser(requestBody)

            if (response.isSuccessful) {
                preferenceManager.setUserLoggedIn(true)
                response.body()
            } else {
                // Handle login error
                null
            }

        } catch (e: Exception) {
            // Handle exception
            null
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(preferenceManager: PreferenceManager): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(preferenceManager)
            }.also { instance = it }

        fun refreshRepository() {
            instance = null
        }

    }
}