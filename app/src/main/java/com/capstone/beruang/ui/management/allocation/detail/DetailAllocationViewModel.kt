package com.capstone.beruang.ui.management.allocation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.beruang.data.response.outcome.OutcomeItem
import com.capstone.beruang.data.retrofit.ApiService
import kotlinx.coroutines.launch

class DetailAllocationViewModel : ViewModel() {
    lateinit var apiService: ApiService

    private val _outcomeData = MutableLiveData<List<OutcomeItem>>()
    val outcomeData: LiveData<List<OutcomeItem>>
        get() = _outcomeData

    private val _errorState = MutableLiveData<String>()
    val errorState: LiveData<String>
        get() = _errorState

    private val _totalOutcomeAmount = MutableLiveData<Int>()
    val totalOutcomeData: LiveData<Int>
        get() = _totalOutcomeAmount

    fun fetchOutcomeData(dataCategory: String, userId:String) {
        viewModelScope.launch {
            try {
                val response = apiService.getOutcome(userId)
                    val allocationList = response.outcome.orEmpty()
                        .filterNotNull()
                        .filter { it.category == dataCategory }

                    _outcomeData.value = allocationList

            } catch (e: Exception) {
                _errorState.value = "Failed to fetch outcome data: ${e.message}"
            }
        }
    }

    fun fetchTotalOutcomeData(dataCategory: String, userId: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getOutcome(userId)
                val allocationList = response.outcome.orEmpty()
                    .filterNotNull()
                    .filter { it.category == dataCategory }
                val totalAmount = allocationList.sumOf { it?.amount ?: 0 }

                _totalOutcomeAmount.value = totalAmount

            } catch (e: Exception) {
                _errorState.value = "Failed to fetch outcome data: ${e.message}"
            }
        }
    }
}

