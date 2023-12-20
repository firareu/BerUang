package com.capstone.beruang.data.repository

import com.capstone.beruang.data.response.RegisterResponse
import com.capstone.beruang.data.retrofit.ApiConfig3
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class UserRepository {

    private val apiService = ApiConfig3.getApiService()

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


}