package com.example.submission.data.retrofit

import com.capstone.beruang.data.retrofit.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            /*val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(requestHeaders)
            }*/
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
//                .addInterceptor(authInterceptor)
                .build()
            val retrofit: Retrofit by lazy {
                Retrofit.Builder()
                    .baseUrl("https://a7bdbd3b-b91f-4885-919a-656c8f7956aa.mock.pstmn.io")
//                    .baseUrl("https://beruang-406309.et.r.appspot.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
            return retrofit.create(ApiService::class.java)
        }
    }
}
