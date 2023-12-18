package com.capstone.beruang.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.capstone.beruang.data.ArticleRepository
import com.capstone.beruang.data.response.article.ArticlesItem
import kotlinx.coroutines.launch

class ArticleViewModel(private val repository: ArticleRepository) : ViewModel() {

    // LiveData for the list of articles
    private val _articleList = MutableLiveData<List<ArticlesItem>>()
    val articleList: LiveData<List<ArticlesItem>> = _articleList

    // LiveData for loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // LiveData for error state
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        // Load articles when the ViewModel is created
        loadArticles()
    }

    private fun loadArticles() {
        // Show loading state
        _isLoading.value = true

        // Use viewModelScope to launch a coroutine
        viewModelScope.launch {
            try {
                // Fetch articles from the repository
                repository.getArticles { articles, error ->
                    if (articles != null) {
                        // Update the LiveData with the list of articles
                        _articleList.postValue(articles)
                    } else {
                        // Handle the error
                        _error.postValue(error ?: "Unknown error")
                    }
                    // Hide loading state
                    _isLoading.postValue(false)
                }
            } catch (e: Exception) {
                // Handle the error
                _isLoading.postValue(false)
                _error.postValue("Error fetching articles: ${e.message}")
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