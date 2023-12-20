package com.capstone.beruang.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.beruang.R
import com.capstone.beruang.data.response.RegisterResponse
import com.capstone.beruang.data.retrofit.ApiConfig3
import com.capstone.beruang.data.retrofit.ApiService
import com.capstone.beruang.databinding.ActivityRegisterBinding
import com.capstone.beruang.ui.login.LoginActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response

class RegisterActivity : AppCompatActivity(), DatePickerListener {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var apiService: ApiService
    private lateinit var ed_dob: TextInputEditText
    private lateinit var ed_gender: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiService = ApiConfig3.getApiService()

        ed_dob = findViewById(R.id.ed_dob)
        ed_gender = findViewById(R.id.spinnerGender)

        binding.redirectToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val genderOptions = arrayOf("male", "female")
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        ed_gender.adapter = adapter
        binding.regButton.setOnClickListener {
            val name = binding.edName.text.toString()
            val email = binding.edRegisEmail.text.toString()
            val password = binding.edPassword.text.toString()
            val dob = ed_dob.text.toString()
            val gender = ed_gender.selectedItem.toString()


            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && dob.isNotEmpty() && gender.isNotEmpty()) {
                registerUser(name, email, password, dob, gender)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(name: String, email: String, password: String, dob: String, gender: String) {
        val requestBody = """
            {
                "name": "$name",
                "email": "$email",
                "password": "$password",
                "dob": "$dob",
                "gender": "$gender"
            }
        """.trimIndent().toRequestBody("application/json".toMediaTypeOrNull())

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<RegisterResponse> = apiService.registerUser(requestBody)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish() // optional, close the registration activity
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registration failed: ${response.errorBody()?.string()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Error: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDateSet(selectedDate: String) {
        ed_dob.setText(selectedDate)
    }

    fun showDatePickerDialog(view: View) {
        val datePickerFragment = DatePickerFragment(ed_dob)
        datePickerFragment.setDatePickerListener(this)
        datePickerFragment.show(supportFragmentManager, "datePicker")
    }
}

interface DatePickerListener {
    fun onDateSet(selectedDate: String)
}