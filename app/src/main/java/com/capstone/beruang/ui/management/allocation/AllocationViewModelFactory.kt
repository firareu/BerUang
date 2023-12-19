package com.capstone.beruang.ui.management.allocation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.beruang.data.repository.Repository
import com.capstone.beruang.di.Injection


class AllocationViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AllocationViewModel::class.java) -> {
                AllocationViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: AllocationViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): AllocationViewModelFactory {
            if (INSTANCE == null) {
                synchronized(AllocationViewModelFactory::class.java) {
                    INSTANCE = AllocationViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as AllocationViewModelFactory
        }
    }
}

