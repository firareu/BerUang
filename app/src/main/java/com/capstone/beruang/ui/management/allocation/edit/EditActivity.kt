package com.capstone.beruang.ui.management.allocation.edit

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.beruang.R
import com.capstone.beruang.data.Allocation
import com.capstone.beruang.data.DatabaseContract
import com.capstone.beruang.data.DatabaseHelper
import com.capstone.beruang.databinding.ActivityEditBinding
import com.capstone.beruang.ui.management.allocation.AllocationFragment
import com.capstone.beruang.ui.management.allocation.AllocationViewModel

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
}