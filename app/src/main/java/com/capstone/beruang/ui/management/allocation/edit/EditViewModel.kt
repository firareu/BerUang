package com.capstone.beruang.ui.management.allocation.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.beruang.data.dataclass.Allocation
import com.capstone.beruang.data.repository.Repository
import com.capstone.beruang.data.retrofit.ApiService
import kotlinx.coroutines.launch

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

    suspend fun getSalaryFromApi(date: String) {
        viewModelScope.launch {
            try {
                /*val salaryResponse = apiService.getSalary(date)
                val salary = salaryResponse.incomes?.salary ?: 0f
                _salary.value = salary*/
            } catch (e: Exception) {

            }
        }
    }
    private fun calculateAllocations(salary: Float) {
        // Lakukan perhitungan alokasi di sini berdasarkan nilai gaji
        // Contoh sederhana, bagi gaji dengan jumlah alokasi dalam list
        val total = alokasiList.sumByDouble { (it.percent ?: 0f).toDouble() }.toFloat() * salary / 100f
        _totalAllocation.value = total
    }

    // Fungsi untuk menambahkan data alokasi ke dalam list
    fun addData(allocation: Allocation) {
        alokasiList.add(allocation)
        // Saat data alokasi ditambahkan, hitung kembali alokasi dengan gaji yang tersedia
        _salary.value?.let { calculateAllocations(it) }
    }


    /*lateinit var apiService: ApiService

    private val alokasiList: MutableList<Allocation> = mutableListOf()

    private val _totalAllocation = MutableLiveData<Float>()
    val totalAllocation: LiveData<Float>
        get() = _totalAllocation


    suspend fun prepareFakeSalary() {
        try {
            val salaryResponse = apiService.getSalary()
            val fakeSalary = salaryResponse.salary ?: 0f
            setFakeSalary(fakeSalary)
        } catch (e: Exception) {
            // Tangani kesalahan jika gagal mendapatkan data gaji
            // ...
        }
    }

    // Fungsi untuk menyiapkan data gaji palsu
    fun setFakeSalary(fakeSalary: Float) {
        var salary: Float = 0f
        salary = fakeSalary
        // Lakukan pembaruan total alokasi di sini jika diperlukan
    }


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
    }*/

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
