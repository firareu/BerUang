package com.capstone.beruang.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.capstone.beruang.data.ArticleRepository
import com.capstone.beruang.data.response.article.NewsResponseItem
import kotlinx.coroutines.launch

class ArticleViewModel(private val repository: ArticleRepository) : ViewModel() {

    private val _articleList = MutableLiveData<List<NewsResponseItem>>()
    val articleList: LiveData<List<NewsResponseItem>> = _articleList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        loadArticles()
    }

    private fun loadArticles() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val (articles, error) = repository.getArticles()
                _articleList.value = articles
                _error.value = error
            } catch (e: Exception) {
                _error.value = "Error fetching articles: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}


class ArticleViewModelFactory(private val repository: ArticleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArticleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}