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
import com.capstone.beruang.data.response.ListAllocationItem
import com.capstone.beruang.data.retrofit.ApiService
//import com.capstone.beruang.data.retrofit.ApiServiceFactory
import com.capstone.beruang.databinding.ActivityEditBinding
import com.capstone.beruang.ui.management.allocation.AllocationAdapter
import com.capstone.beruang.ui.management.allocation.AllocationViewModel
import com.example.submission.data.retrofit.ApiConfig
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
    private val allocationAdapter: AllocationAdapter = AllocationAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editViewModel = ViewModelProvider(this).get(EditViewModel::class.java)


        apiService = ApiConfig.getApiService()
        editViewModel.apiService = apiService

        adapter = EditAdapter(apiService)
        binding.rvAllocation.layoutManager = LinearLayoutManager(this)
        binding.rvAllocation.setHasFixedSize(true)
        binding.rvAllocation.adapter = adapter

        getAndSetSalary()
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
    private fun getAndSetSalary() {
        lifecycleScope.launch {
            try {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH) + 1
                val currentDateString = "$year-$month"

                editViewModel.getSalaryFromApi(currentDateString)

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
                val response = apiService.getAllAllocations()
                if (!response.error!!) {
                    val allocations = response.allocation.orEmpty().filterNotNull() // Menghapus nilai null dari list

                    runOnUiThread {
                        adapter.submitList(allocations)
                    }
                } else {
                    Log.e("EditActivity", "Error fetching allocations: ${response.message}")
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

    override fun onItemClicked(data: ListAllocationItem) {
        val id = data.id
//        deleteAllocationFromApi(id)
    }
}



/*
class EditActivity : AppCompatActivity(), EditAdapter.OnItemClickCallback {
    private lateinit var binding: ActivityEditBinding
    private lateinit var editViewModel: EditViewModel
    private lateinit var adapter: EditAdapter
    private lateinit var viewModel: AllocationViewModel
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editViewModel = ViewModelProvider(this).get(EditViewModel::class.java)
        viewModel = ViewModelProvider(this).get(AllocationViewModel::class.java)
        databaseHelper = DatabaseHelper(this)

        setUpRecyclerView()
        loadDataFromDatabase()
        setupAction()
    }
    private fun addNewAllocation() {
        val allocation = Allocation(
            allocation_name = "",
            percent = null,
            total = null
        )

        // Menambahkan alokasi baru ke daftar yang ditampilkan oleh adapter
        adapter.addAllocation(allocation)
        adapter.notifyItemInserted(adapter.itemCount - 1)
    }

    private fun saveAllocationData() {
        val edtNameAlokasi = findViewById<EditText>(R.id.edt_nameallocation)
        val edtPersentase = findViewById<EditText>(R.id.edt_percent)

        val namaAlokasi = edtNameAlokasi.text.toString()
        val persentase = edtPersentase.text.toString()

        val allocation = Allocation(
            allocation_name = namaAlokasi,
            percent = persentase.toFloatOrNull(),
            total = null // Atur total ke null jika belum dihitung
        )

        val existingAllocations = viewModel.allocations.value

        if (existingAllocations.isNullOrEmpty()) {
            removeAllDataFromDatabaseAndSave(allocation)
        } else {
            val allocationsList = existingAllocations.toMutableList()
            allocationsList.add(allocation)

            saveAllocationToDatabase(allocationsList)
        }
    }
    private fun navigateToAllocationFragment() {
        val intentfav = Intent(this, AllocationFragment::class.java)
        startActivity(intentfav)
    }

    private fun saveAllocationToDatabase(allocations: List<Allocation>) {
        val db = databaseHelper.writableDatabase
        db.beginTransaction()

        try {
            for (allocation in allocations) {
                val values = ContentValues().apply {
                    put(DatabaseContract.AllocationEntry.COLUMN_NAME, allocation.allocation_name)
                    put(DatabaseContract.AllocationEntry.COLUMN_PERCENT, allocation.percent)
                    put(DatabaseContract.AllocationEntry.COLUMN_TOTAL, allocation.total)
                }

                val newRowId = db.insert(DatabaseContract.AllocationEntry.TABLE_NAME, null, values)
                Log.d("EditActivity", "New Row ID: $newRowId")
            }
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.e("EditActivity", "Error saving allocations to database: ${e.message}")
        } finally {
            db.endTransaction()
        }
    }

    private fun removeAllDataFromDatabaseAndSave(allocation: Allocation) {
        val db = databaseHelper.writableDatabase

        db.delete(DatabaseContract.AllocationEntry.TABLE_NAME, null, null)

        val values = ContentValues().apply {
            put(DatabaseContract.AllocationEntry.COLUMN_NAME, allocation.allocation_name)
            put(DatabaseContract.AllocationEntry.COLUMN_PERCENT, allocation.percent)
            put(DatabaseContract.AllocationEntry.COLUMN_TOTAL, allocation.total)
        }

        val newRowId = db.insert(DatabaseContract.AllocationEntry.TABLE_NAME, null, values)
        Log.d("EditActivity", "New Row ID: $newRowId")
    }

    private fun loadDataFromDatabase() {
        viewModel.loadAllocationsFromDatabase(this)
        viewModel.allocations.observe(this, { allocations ->
            adapter.submitList(ArrayList(allocations))
        })
    }

    private fun setUpRecyclerView() {
        adapter = EditAdapter(databaseHelper)
        val recyclerView: RecyclerView = findViewById(R.id.rv_Allocation)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setOnItemClickCallback(this)
    }



    override fun onItemClicked(data: Allocation) {
        val id = data.id
        val name = ""
        val percent = ""

        editViewModel.updateData(id, name, percent.toFloatOrNull() ?: 0f)
        adapter.notifyDataSetChanged()

    }

    private fun setupAction() {
        binding.tvAdd.setOnClickListener {
            Log.d("EditActivity", "Button Add Clicked!")
            addNewAllocation()
        }
        binding.btnSubmit.setOnClickListener {
            Log.d("EditActivity", "Button Submit Clicked!")
            saveAllocationData()
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
                adapter.updateSalary(salary)
            }

            override fun afterTextChanged(s: Editable?) {
                // Not needed
            }
        })
    }
}*/


