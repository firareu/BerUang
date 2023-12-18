package com.capstone.beruang.data

import com.capstone.beruang.data.response.article.ArticleResponse
import com.capstone.beruang.data.response.article.ArticlesItem
import com.capstone.beruang.data.retrofit.ApiConfig2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleRepository {

    // Use Retrofit to get the API service
    private val apiService = ApiConfig2.getApiService()

    // Fetch articles from the API
    fun getArticles(callback: (List<ArticlesItem>?, String?) -> Unit) {
        val queryParams = mapOf(
            "country" to "us",
            "apiKey" to "80ba54addaa34c59b51b7690c556f49e"
        )

        apiService.getTopHeadlines(queryParams).enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                if (response.isSuccessful) {
                    val articleResponse = response.body()
                    val articles = articleResponse?.articles ?: emptyList()
                    callback(articles, null)
                } else {
                    callback(null, "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                callback(null, "Failure: ${t.message}")
            }
        })
    }
}