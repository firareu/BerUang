package com.capstone.beruang.data

import com.capstone.beruang.data.response.article.NewsResponse
import com.capstone.beruang.data.response.article.NewsResponseItem
import com.capstone.beruang.data.retrofit.ApiConfig2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ArticleRepository {

    private val apiService = ApiConfig2.getApiService()

    suspend fun getArticles(): Pair<List<NewsResponseItem>?, String?> {
        return try {
            withContext(Dispatchers.IO) {
                val response: Response<NewsResponse> = apiService.getNews()
                if (response.isSuccessful) {
                    val articleResponse = response.body()
                    val articles = articleResponse?.newsResponse.orEmpty()
                    articles to null
                } else {
                    emptyList<NewsResponseItem>() to "Error: ${response.message()}"
                }
            }
        } catch (t: Throwable) {
            emptyList<NewsResponseItem>() to "Failure: ${t.message}"
        }
    }


}
