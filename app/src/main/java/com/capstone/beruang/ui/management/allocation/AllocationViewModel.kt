package com.capstone.beruang.ui.management.allocation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.capstone.beruang.data.repository.Repository
import com.capstone.beruang.data.repository.UserRepository
import com.capstone.beruang.data.retrofit.ApiService
import com.capstone.beruang.data.Result
import com.capstone.beruang.data.response.allocation.AllocationItem
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AllocationViewModel(private val repository: Repository) : ViewModel() {

    lateinit var apiService: ApiService
    fun allocations(userId: String) = repository.getAllAllocations(userId)


    private val _salary = MutableLiveData<Float>()
    val salary: LiveData<Float>
        get() = _salary

    suspend fun getSalaryFromApi(userId: String, date: String) {
        viewModelScope.launch {
            try {
                val incomeResponse = apiService.getSalary(userId)
                val dataList = incomeResponse.income
                val latestDate = dataList?.maxByOrNull { parseDate(it?.date ?: "") }?.date
                val filteredByLatestDate = dataList?.filter { it?.date == latestDate }
                val totalSalary = filteredByLatestDate?.sumOf { it?.salary ?: 0 } ?: 0f
                _salary.value = totalSalary.toFloat()
            } catch (e: Exception) {
                Log.e("SalaryResponse", "Error fetching salary: ${e.message}")
            }
        }
    }

    fun parseDate(date: String): Date{
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.parse(date)?:Date()
    }


    fun addAllocation(allocation: AllocationItem) {
        viewModelScope.launch {
            try {
                repository.addAllocation(allocation)
                // Handle response if necessary
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error when adding allocation
            }
        }
    }

    fun updateAllocation(id: Int, allocation: AllocationItem) {
        viewModelScope.launch {
            try {
                repository.updateAllocation(id, allocation)
                // Handle response if necessary
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error when updating allocation
            }
        }
    }

    fun deleteAllocation(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteAllocation(id)
                // Handle response if necessary
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error when deleting allocation
            }
        }
    }
}
