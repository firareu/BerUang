package com.capstone.beruang.ui.management

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ManagementViewModel : ViewModel() {

    private val _loadingVisibility = MutableLiveData<Int>()
    val loadingVisibility: LiveData<Int> get() = _loadingVisibility

    private val _errorVisibility = MutableLiveData<Int>()
    val errorVisibility: LiveData<Int> get() = _errorVisibility

    fun setLoadingVisibility(visibility: Int) {
        _loadingVisibility.value = visibility
    }
    fun setErrorVisibility(visibility: Int) {
        _errorVisibility.value = visibility
    }

}