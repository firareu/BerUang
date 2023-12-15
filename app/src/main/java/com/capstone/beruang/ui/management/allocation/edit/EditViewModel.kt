package com.capstone.beruang.ui.management.allocation.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.beruang.data.Allocation

class EditViewModel : ViewModel() {

    private val alokasiList: MutableList<Allocation> = mutableListOf()

    private val _totalAllocation = MutableLiveData<Float>()
    val totalAllocation: LiveData<Float>
        get() = _totalAllocation

    fun addData(allocation: Allocation) {
        alokasiList.add(allocation)
    }

    fun getData(): List<Allocation> {
        return alokasiList
    }

    fun updateData(id: Int, name: String, percent: Float) {
        for (allocation in alokasiList) {
            if (allocation.id == id) {
                allocation.allocation_name = name
                allocation.percent = percent
                // Jika Anda ingin melakukan update terhadap total juga, tambahkan logika di sini
                break
            }
        }
    }

    fun calculateAllocations(salary: Float, percent: Float) {
        val totalAllocation = (salary * percent) / 100

        // Simpan total alokasi ke objek Allocation yang sesuai
        for (allocation in alokasiList) {
            if (allocation.percent == percent) {
                allocation.total = totalAllocation
            }
        }

        _totalAllocation.value = totalAllocation
    }

    /*private val _needsAllocation = MutableLiveData<Float>()
    val needsAllocation: LiveData<Float>
        get() = _needsAllocation

    private val _lifestyleAllocation = MutableLiveData<Float>()
    val lifestyleAllocation: LiveData<Float>
        get() = _lifestyleAllocation

    private val _goalsAllocation = MutableLiveData<Float>()
    val goalsAllocation: LiveData<Float>
        get() = _goalsAllocation

    fun calculateAllocations(salary: Float, needs: Float, lifestyle: Float, goals: Float){
        val needsAllocation = (salary * needs) / 100
        _needsAllocation.value = needsAllocation

        val lifestyleAllocation = (salary * lifestyle) / 100
        _lifestyleAllocation.value = lifestyleAllocation

        val goalsAllocation = (salary * goals) / 100
        _goalsAllocation.value = goalsAllocation
    }*/
}
