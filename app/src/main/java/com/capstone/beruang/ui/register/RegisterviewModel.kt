package com.capstone.beruang.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.beruang.data.repository.UserRepository
import com.capstone.beruang.data.response.RegisterResponse
import kotlinx.coroutines.launch

class RegistrationViewModel(private val repository: UserRepository) : ViewModel() {

    private val _registrationResult = MutableLiveData<RegisterResponse?>()
    val registrationResult: LiveData<RegisterResponse?> get() = _registrationResult

    fun registerUser(name: String, email: String, password: String, dob: String, gender: String) {
        viewModelScope.launch {
            val response = repository.registerUser(name, email, password, dob, gender)
            _registrationResult.value = response
        }
    }
}
