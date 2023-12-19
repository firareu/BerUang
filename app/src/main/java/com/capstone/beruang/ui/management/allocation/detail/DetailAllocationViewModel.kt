package com.capstone.beruang.ui.management.allocation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.beruang.data.response.AllocationItem
import com.capstone.beruang.data.retrofit.ApiService
import kotlinx.coroutines.launch

class DetailAllocationViewModel : ViewModel() {
    lateinit var apiService: ApiService

    private val _outcomeData = MutableLiveData<List<AllocationItem>>()
    val outcomeData: LiveData<List<AllocationItem>>
        get() = _outcomeData

    private val _errorState = MutableLiveData<String>()
    val errorState: LiveData<String>
        get() = _errorState

    fun fetchOutcomeData(dataCategory: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getOutcome()
                if (!response.error!!) {
                    val allocationList = response.allocation.orEmpty()
                        .filterNotNull()
                        .filter { it.category == dataCategory }

                    _outcomeData.value = allocationList
                } else {
                    _errorState.value = "Outcome data is empty"
                }
            } catch (e: Exception) {
                _errorState.value = "Failed to fetch outcome data: ${e.message}"
            }
        }
    }
}

