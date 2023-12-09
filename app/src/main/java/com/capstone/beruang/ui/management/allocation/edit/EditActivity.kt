package com.capstone.beruang.ui.management.allocation.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.beruang.R
import com.capstone.beruang.data.Allocation
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
        setUpClickListener()
        binding.btnSubmit.setOnClickListener {
            // Tambahkan log atau pesan toast untuk memeriksa apakah fungsi ini dijalankan
            Log.d("EditActivity", "Button Submit Clicked!")

            navigateToAllocationFragment()
        }

    }

    private fun navigateToAllocationFragment() {
        val intentfav = Intent(this, AllocationFragment::class.java)
        startActivity(intentfav)
    }

    private fun setUpClickListener() {
        val tvAdd = findViewById<TextView>(R.id.tv_add)
        tvAdd.setOnClickListener {
            val namaAlokasi = "" // Ganti dengan nilai yang sesuai dari EditText nama alokasi
            val persentase = "" // Ganti dengan nilai yang sesuai dari EditText persentase

            val allocation = Allocation(0, namaAlokasi, persentase.toFloatOrNull(), null)
            editViewModel.addData(allocation)
            adapter.notifyDataSetChanged()
        }
    }

    private fun loadDataFromDatabase() {
        viewModel.loadAllocationsFromDatabase(this)
        viewModel.allocations.observe(this, { allocations ->
            adapter.submitList(ArrayList(allocations))
        })
    }

    private fun setUpRecyclerView() {
        // Inisialisasi adapter dengan menyediakan aktivitas (this) dan DatabaseHelper
//        adapter = EditAdapter(this, databaseHelper)
        val recyclerView: RecyclerView = findViewById(R.id.rv_Allocation)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setOnItemClickCallback(this)
    }


    override fun onItemClicked(data: Allocation) {
        // Lakukan tindakan yang diperlukan saat item diklik (misalnya, edit data)
        val id = data.id // Mendapatkan ID dari item yang diklik
        val name = "" // Ganti dengan nilai yang sesuai dari EditText nama alokasi
        val percent = "" // Ganti dengan nilai yang sesuai dari EditText persentase

        editViewModel.updateData(id, name, percent.toFloatOrNull() ?: 0f)
        adapter.notifyDataSetChanged()

    }

    /*override fun onClick(view: View) {
        if (view.id == R.id.btn_submit) {
            *//*val needsValue = binding.edtNeeds.text.toString().toFloatOrNull() ?: 0f
            val lifestyleValue = binding.edtLifestyle.text.toString().toFloatOrNull() ?: 0f
            val goalsValue = binding.edtGoals.text.toString().toFloatOrNull() ?: 0f
            val salary = binding.edtSalary.text.toString().toFloatOrNull() ?: 0f

            editViewModel.calculateAllocations(salary, needsValue, lifestyleValue, goalsValue)

            val needsAllocation = Allocation(1, "Needs", needsValue, editViewModel.needsAllocation.value)
            val lifestyleAllocation = Allocation(2, "Lifestyle", lifestyleValue, editViewModel.lifestyleAllocation.value)
            val goalsAllocation = Allocation(3, "Goals", goalsValue, editViewModel.goalsAllocation.value)

            *//**//*Salary(1, salary)*//**//*
            val allocations = arrayListOf(needsAllocation, lifestyleAllocation, goalsAllocation)

            // Simpan data ke database menggunakan DatabaseHelper
            for (allocation in allocations) {
                saveAllocationToDatabase(allocation)
            }*//*

            val intentfav = Intent(this, AllocationFragment::class.java)
            startActivity(intentfav)
        }
    }*/

    private fun setupAction() {
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