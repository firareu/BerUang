package com.capstone.beruang.ui.management.allocation.edit

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.beruang.R
import com.capstone.beruang.data.repository.PreferenceManager
import com.capstone.beruang.data.repository.UserRepository
import com.capstone.beruang.data.response.allocation.AllocationItem
import com.capstone.beruang.data.retrofit.ApiConfig
import com.capstone.beruang.data.retrofit.ApiService
import com.capstone.beruang.databinding.ActivityEditBinding
import com.capstone.beruang.ui.management.allocation.AllocationAdapter
import com.capstone.beruang.ui.management.allocation.AllocationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

class EditActivity : AppCompatActivity(), EditAdapter.OnItemClickCallback {
    private lateinit var binding: ActivityEditBinding
    private lateinit var viewModel: AllocationViewModel
    private lateinit var editViewModel: EditViewModel
    private lateinit var apiService: ApiService
    private lateinit var adapter: EditAdapter
//    private val allocationAdapter: AllocationAdapter = AllocationAdapter()
    private val userRepository: UserRepository by lazy {
        UserRepository(PreferenceManager.getInstance(this))
    }
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        editViewModel = ViewModelProvider(this).get(EditViewModel::class.java)

//        userId = intent.getStringExtra("USER_ID") ?: ""
        apiService = ApiConfig.getApiService()
        editViewModel.apiService = apiService

        adapter = EditAdapter(apiService)
        binding.rvAllocation.layoutManager = LinearLayoutManager(this)
        binding.rvAllocation.setHasFixedSize(true)
        binding.rvAllocation.adapter = adapter
        editViewModel.apiService = ApiConfig.getApiService()
        userId = userRepository.getUserId().toString()
        getAndSetSalary(userId)
        setUpRecyclerView()
        loadDataFromApi()
        setupAction()
    }

    private fun deleteAllocationFromApi(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                apiService.deleteAllocation(id)
                // Lakukan sesuatu setelah penghapusan berhasil
            } catch (e: Exception) {
                // Tangani kesalahan jika gagal menghapus
                Log.e("EditActivity", "Failed to delete allocation: ${e.message}")
            }
        }
    }
    private fun getAndSetSalary(userId: String) {
        lifecycleScope.launch {
            try {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH) + 1
                val currentDateString = "$year-$month"

                editViewModel.getSalaryFromApi(userId, currentDateString)

                // Dapatkan nilai gaji dari LiveData _salary
                editViewModel.salary.observe(this@EditActivity) { salary ->
                    salary?.let {
                        Log.e("salary1", salary.toString())

                        val TVMoney = getString(R.string.rupiah, salary.toInt())
                        Log.e("salary2", TVMoney)

                        binding.edtSalary.setText(TVMoney)
                    }
                }
            } catch (e: Exception) {
                Log.e("salary", "Error getting salary: ${e.message}")
            }
        }
    }

    private fun loadDataFromApi() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                userId = userRepository.getUserId().toString()
                val response = apiService.getAllAllocations(userId)
                val allocations = response.allocation.orEmpty().filterNotNull() // Menghapus nilai null dari list

                runOnUiThread {
                    adapter.submitList(allocations)
                }
            } catch (e: Exception) {
                Log.e("EditActivity", "Error: ${e.message}")
            }
        }
    }



    private fun setUpRecyclerView() {
        adapter = EditAdapter(apiService)
        val recyclerView: RecyclerView = findViewById(R.id.rv_Allocation)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setOnItemClickCallback(this)
    }

    private fun setupAction() {
        binding.tvAdd.setOnClickListener {
            Log.d("EditActivity", "Button Add Clicked!")
//            addNewAllocation()
        }
        binding.btnSubmit.setOnClickListener {
            Log.d("EditActivity", "Button Submit Clicked!")
//            saveAllocationData()
//            navigateToAllocationFragment()
            finish()
        }
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.edtSalary.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val salary = binding.edtSalary.text.toString().toFloatOrNull() ?: 0f
//                adapter.updateSalary(salary)
            }

            override fun afterTextChanged(s: Editable?) {
                // Not needed
            }
        })
    }

    override fun onItemClicked(data: AllocationItem) {
        val id = data.allocationId
//        deleteAllocationFromApi(id)
    }
}