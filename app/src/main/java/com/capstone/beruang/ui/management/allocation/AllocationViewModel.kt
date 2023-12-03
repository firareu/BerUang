package com.capstone.beruang.ui.management.allocation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.beruang.data.Allocation
import com.capstone.beruang.data.DatabaseContract
import com.capstone.beruang.data.DatabaseHelper

class AllocationViewModel : ViewModel() {

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
}
