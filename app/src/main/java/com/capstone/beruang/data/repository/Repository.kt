package com.capstone.beruang.data.repository

import androidx.lifecycle.liveData
import com.capstone.beruang.data.Result
import com.capstone.beruang.data.response.allocation.AllocationItem
import com.capstone.beruang.data.retrofit.ApiService

class Repository constructor(
    val apiService: ApiService
) {

    fun getAllAllocations(userId: String) = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllAllocations(userId)
            emit(Result.Success(response))
        } catch (e: Exception) {
            // Handle exceptions if necessary
        }
    }

    fun addAllocation(allocation: AllocationItem) = liveData {
        try {
            val response = apiService.addAllocation(allocation)
            emit(response)
        } catch (e: Exception) {
            // Handle exceptions if necessary
        }
    }

    fun updateAllocation(id: Int, allocation: AllocationItem) = liveData {
        try {
            val response = apiService.updateAllocation(id, allocation)
            emit(response)
        } catch (e: Exception) {
            // Handle exceptions if necessary
        }
    }

    fun deleteAllocation(id: Int) = liveData {
        try {
            apiService.deleteAllocation(id)
            // If deletion successful, emit some success response or flag
            emit(true)
        } catch (e: Exception) {
            // Handle exceptions if necessary
        }
    }


    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(apiService: ApiService): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(apiService)
            }.also { instance = it }
    }
}
