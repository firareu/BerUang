package com.capstone.beruang.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.beruang.data.ArticleRepository

class ArticleViewModel(private val repository: ArticleRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is article Fragment"
    }
    val text: LiveData<String> = _text
}