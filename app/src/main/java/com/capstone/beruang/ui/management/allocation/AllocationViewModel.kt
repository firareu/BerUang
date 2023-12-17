package com.capstone.beruang.ui.management.allocation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.beruang.data.fakedata.FakeDataGenerator
import com.capstone.beruang.data.response.ListAllocationItem
import com.capstone.beruang.data.repository.AllocationRepository
import com.capstone.beruang.data.response.AllocationResponse
import kotlinx.coroutines.launch

class AllocationViewModel(private val repository: AllocationRepository) : ViewModel() {

    private val _dataFetchError = MutableLiveData<Boolean>()
    val dataFetchError: LiveData<Boolean>
        get() = _dataFetchError


    fun allocations() = repository.getAllAllocations()

    fun getFakeAllocations(): List<ListAllocationItem> {
        return FakeDataGenerator.generateFakeAllocations()
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
