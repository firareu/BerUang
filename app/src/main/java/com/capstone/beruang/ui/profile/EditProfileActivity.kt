package com.capstone.beruang.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.capstone.beruang.R
import com.capstone.beruang.databinding.ActivityEditBinding
import com.capstone.beruang.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.btnSubmit.setOnClickListener {
            Log.d("EditProfileActivity", "Button Submit Clicked!")
//            saveAllocationData()
//            navigateToAllocationFragment()
            finish()
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}