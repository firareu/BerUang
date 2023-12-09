package com.capstone.beruang.ui.management.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditViewModel : ViewModel() {
    private val _needsAllocation = MutableLiveData<Float>()
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
    }
}