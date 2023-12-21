package com.capstone.beruang.data.repository

import androidx.lifecycle.liveData
import com.capstone.beruang.data.Result
import com.capstone.beruang.data.response.LoginResponse
import com.capstone.beruang.data.response.RegisterResponse
import com.capstone.beruang.data.retrofit.ApiConfig3
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.http.Body

class UserRepository (private val preferenceManager: PreferenceManager) {

    private val apiService = ApiConfig3.getApiService()

    fun isUserLoggedIn(): Boolean {
        return preferenceManager.getBoolean(PreferenceManager.KEY_IS_USER_LOGGED_IN, false)
    }
    fun logoutUser() {
        preferenceManager.putBoolean(PreferenceManager.KEY_IS_USER_LOGGED_IN, false)
    }

    fun getUserId(): String? {
        return preferenceManager.getUid()
    }

    suspend fun registerUser(name: String, email: String, password: String, dob: String, gender: String): RegisterResponse? {
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
                /*preferenceManager.setUserLoggedIn(true)
                response.body()*/
                val loginResponse = response.body()
                loginResponse?.userId?.let { uid ->
                    preferenceManager.setUid(uid) // Simpan UID ke PreferenceManager
                    preferenceManager.setUserLoggedIn(true)
                }
                loginResponse // Mengembalikan LoginResponse
            } else {
                // Handle login error
                null
            }

        } catch (e: Exception) {
            // Handle exception
            null
        }
    }

    fun getAllAllocations(userId: String) = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllAllocations(userId)
            emit(Result.Success(response))
        } catch (e: Exception) {
            // Handle exceptions if necessary
        }
    }
}