package com.capstone.beruang.ui.management.allocation.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.capstone.beruang.R
import com.capstone.beruang.data.repository.PreferenceManager
import com.capstone.beruang.data.repository.UserRepository
import com.capstone.beruang.databinding.ActivityAddOutcomeBinding
import com.capstone.beruang.databinding.ActivityDetailAllocationBinding
import com.capstone.beruang.ui.login.LoginActivity
import com.capstone.beruang.ui.management.allocation.AllocationViewModel

class AddOutcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddOutcomeBinding
    private lateinit var allocation: Spinner
    private lateinit var viewModel: AddOutcomeViewModel
    private val userRepository: UserRepository by lazy {
        UserRepository(PreferenceManager.getInstance(this))
    }
    private lateinit var userId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddOutcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        allocation = findViewById(R.id.categoryallocation)
//        viewModel = ViewModelProvider(this).get(AddOutcomeViewModel::class.java)
        userId = userRepository.getUserId().toString()

        /*viewModel.allocations(userId).observe(this) { allocationResponse ->
            val genderOptions = allocationResponse
            val adapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            allocation.adapter = adapter
        }*/
        setupAction()
    }

    private fun setupAction() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}