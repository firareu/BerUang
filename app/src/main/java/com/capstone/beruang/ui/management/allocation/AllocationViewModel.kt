package com.capstone.beruang.ui.management.allocation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.beruang.data.response.ListAllocationItem
import com.capstone.beruang.data.repository.Repository
import com.capstone.beruang.data.retrofit.ApiService
import kotlinx.coroutines.launch

class AllocationViewModel(private val repository: Repository) : ViewModel() {

    lateinit var apiService: ApiService
    private val _dataFetchError = MutableLiveData<Boolean>()
    val dataFetchError: LiveData<Boolean>
        get() = _dataFetchError

    fun allocations() = repository.getAllAllocations()

    private val _salary = MutableLiveData<Float>()
    val salary: LiveData<Float>
        get() = _salary

    /*suspend fun getSalaryFromApi(date: String, userId: String) {
        viewModelScope.launch {
            try {
                val incomeResponse = apiService.getSalary(date, userId)
                val salary = incomeResponse.data?.amount ?: 0f
                _salary.value = salary.toFloat()
            } catch (e: Exception) {

            }
        }
    }*/

    /*suspend fun getSalaryFromApi(date: String, userId: String) {
        viewModelScope.launch {
            try {
                // Pastikan apiService telah diinisialisasi sebelum penggunaan
                val incomeResponse = apiService.getSalary(date, userId)
                Log.d("SalaryResponse", "Response: $incomeResponse")

                val salary = incomeResponse.data?.salary ?: 0f
                _salary.value = salary.toFloat()
            } catch (e: Exception) {
                Log.e("SalaryResponse", "Error fetching salary: ${e.message}")
            }
        }
    }*/

    /*suspend fun getSalaryFromApi(date: String, userId: String) {
        viewModelScope.launch {
            try {
                // Pastikan apiService telah diinisialisasi sebelum penggunaan
                val incomeResponse = apiService.getSalary(userId)
                Log.d("SalaryResponse", "Response: $incomeResponse")

                // Mengambil List<Data?>? dari IncomeResponse
                val dataList = incomeResponse.data

                // Menghitung total salary dari setiap Data di dalam dataList
                val totalSalary = dataList?.sumOf { it?.salary ?: 0 } ?: 0f

                // Mengubah totalSalary ke tipe Float dan menetapkannya ke _salary
                _salary.value = totalSalary.toFloat()
            } catch (e: Exception) {
                Log.e("SalaryResponse", "Error fetching salary: ${e.message}")
            }
        }
    }*/
    suspend fun getSalaryFromApi(userId: String, date: String) {
        viewModelScope.launch {
            try {
                // Pastikan apiService telah diinisialisasi sebelum penggunaan
                val incomeResponse = apiService.getSalary(userId)
                Log.d("SalaryResponse", "Response: $incomeResponse")

                // Mengambil List<Data?>? dari IncomeResponse
                val dataList = incomeResponse.data

                // Filter dataList berdasarkan date yang diberikan
                val filteredList = dataList?.filter { it?.date == date }

                // Menghitung total salary dari setiap Data di dalam filteredList
                val totalSalary = filteredList?.sumOf { it?.salary ?: 0 } ?: 0f

                // Mengubah totalSalary ke tipe Float dan menetapkannya ke _salary
                _salary.value = totalSalary.toFloat()
            } catch (e: Exception) {
                Log.e("SalaryResponse", "Error fetching salary: ${e.message}")
            }
        }
    }




    fun addAllocation(allocation: ListAllocationItem) {
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

    fun updateAllocation(id: Int, allocation: ListAllocationItem) {
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



/*class AllocationViewModel : ViewModel() {

    private val _allocations = MutableLiveData<List<Allocation>>()
    val allocations: LiveData<List<Allocation>> get() = _allocations

    // Function untuk mengambil data dari database
    fun loadAllocationsFromDatabase(context: Context) {
        val databaseHelper = DatabaseHelper(context)
        val db = databaseHelper.readableDatabase

        val projection = arrayOf(
            DatabaseContract.AllocationEntry.COLUMN_ID,
            DatabaseContract.AllocationEntry.COLUMN_NAME,
            DatabaseContract.AllocationEntry.COLUMN_PERCENT,
            DatabaseContract.AllocationEntry.COLUMN_TOTAL
        )

        val cursor = db.query(
            DatabaseContract.AllocationEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val allocations = mutableListOf<Allocation>()

        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.AllocationEntry.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(DatabaseContract.AllocationEntry.COLUMN_NAME))
                val percent = getFloat(getColumnIndexOrThrow(DatabaseContract.AllocationEntry.COLUMN_PERCENT))
                val total = getFloat(getColumnIndexOrThrow(DatabaseContract.AllocationEntry.COLUMN_TOTAL))

                val allocation = Allocation(id, name, percent, total)
                allocations.add(allocation)
            }
        }

        _allocations.postValue(allocations)
    }
}*/
