package com.capstone.beruang.ui.management.allocation.edit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.beruang.data.dataclass.Allocation
import com.capstone.beruang.data.repository.Repository
import com.capstone.beruang.data.retrofit.ApiService
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EditViewModel : ViewModel() {
    lateinit var apiService: ApiService

    private val alokasiList: MutableList<Allocation> = mutableListOf()

    private val _totalAllocation = MutableLiveData<Float>()
    val totalAllocation: LiveData<Float>
        get() = _totalAllocation

    private val _salary = MutableLiveData<Float>()
    val salary: LiveData<Float>
        get() = _salary

    private val _allocationList = MutableLiveData<List<Allocation>>()
   val allocationList: LiveData<List<Allocation>>
        get() = _allocationList

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

    fun parseDate(date: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.parse(date)?: Date()
    }
    private fun calculateAllocations(salary: Float) {
        val total = alokasiList.sumByDouble { (it.percent ?: 0f).toDouble() }.toFloat() * salary / 100f
        _totalAllocation.value = total
    }


    fun addData(allocation: Allocation) {
        alokasiList.add(allocation)
        _salary.value?.let { calculateAllocations(it) }
    }

}
