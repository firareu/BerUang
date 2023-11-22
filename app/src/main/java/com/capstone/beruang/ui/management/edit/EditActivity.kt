package com.capstone.beruang.ui.management.edit

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.capstone.beruang.R
import com.capstone.beruang.data.Allocation
import com.capstone.beruang.data.DatabaseContract
import com.capstone.beruang.data.DatabaseHelper
import com.capstone.beruang.databinding.ActivityEditBinding
import com.capstone.beruang.ui.management.allocation.AllocationFragment
import com.capstone.beruang.ui.management.allocation.AllocationViewModel

class EditActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEditBinding
    private lateinit var viewModel: AllocationViewModel
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(AllocationViewModel::class.java)

        // Inisialisasi databaseHelper
        databaseHelper = DatabaseHelper(this)

        setupAction()
        binding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_submit) {
            val salary = binding.edtSalary.text.toString()
            val needsValue = binding.edtNeeds.text.toString().toFloatOrNull() ?: 0f
            val lifestyleValue = binding.edtLifestyle.text.toString().toFloatOrNull() ?: 0f
            val goalsValue = binding.edtGoals.text.toString().toFloatOrNull() ?: 0f

            val needsAllocation = Allocation(1, "Needs", needsValue, null)
            val lifestyleAllocation = Allocation(2, "Lifestyle", lifestyleValue, null)
            val goalsAllocation = Allocation(3, "Goals", goalsValue, null)

            val allocations = arrayListOf(needsAllocation, lifestyleAllocation, goalsAllocation)

            // Simpan data ke database menggunakan DatabaseHelper
            for (allocation in allocations) {
                saveAllocationToDatabase(allocation)
            }

            val intentfav = Intent(this, AllocationFragment::class.java)
            startActivity(intentfav)

            // Menutup Activity Edit setelah menyimpan data
            /*finish()*/

            /*viewModel.setAllocations(allocations)

            val allocationFragment = AllocationFragment()
            val bundle = Bundle()
            allocationFragment.arguments = bundle

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_allocation, allocationFragment)
                .commit()

            val existingFragment = supportFragmentManager.findFragmentById(R.id.frame_allocation)
            if (existingFragment is AllocationFragment) {
                existingFragment.setFragmentData(allocations) // Kirim data yang baru saja dikirim ke fragment
            }*/
        }
    }

    private fun saveAllocationToDatabase(allocation: Allocation) {
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseContract.AllocationEntry.COLUMN_PERCENT, allocation.percent)
            put(DatabaseContract.AllocationEntry.COLUMN_TOTAL, allocation.total)
        }

        val selection = "${DatabaseContract.AllocationEntry.COLUMN_NAME} = ?"
        val selectionArgs = arrayOf(allocation.allocation_name)

        // Lakukan update jika data sudah ada di database
        val updatedRows = db.update(
            DatabaseContract.AllocationEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )

        // Jika data belum ada di database, lakukan operasi insert
        if (updatedRows == 0) {
            values.put(DatabaseContract.AllocationEntry.COLUMN_NAME, allocation.allocation_name)
            val newRowId = db.insert(DatabaseContract.AllocationEntry.TABLE_NAME, null, values)
            Log.d("EditActivity", "New Row ID: $newRowId")
        } else {
            Log.d("EditActivity", "Data Updated: $updatedRows rows updated")
        }
    }



    private fun setupAction() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}