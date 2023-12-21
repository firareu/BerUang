package com.capstone.beruang.ui.management.allocation.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.beruang.data.dataclass.Allocation
import com.capstone.beruang.data.repository.Repository
import com.capstone.beruang.data.response.allocation.AllocationItem

class AddOutcomeViewModel(private val repository: Repository) : ViewModel(){
    private val _categoryList = MutableLiveData<List<String>>()
    val categoryList: LiveData<List<String>>
        get() = _categoryList

    fun fetchAllocations(userId: String) {
        repository.getAllAllocations(userId).observeForever { allocationResponse ->
            allocationResponse?.let { response ->
                /*val allocationItems = response.allocation.orEmpty()
                val categories = allocationItems.mapNotNull { it.category }
                _categoryList.value = categories*/
            }
        }
    }

}