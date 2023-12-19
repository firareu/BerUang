package com.capstone.beruang.data.retrofit

import com.capstone.beruang.data.fakedata.FakeDataGenerator
import com.capstone.beruang.data.response.AllocationResponse
import com.capstone.beruang.data.response.CategoryResponse
import com.capstone.beruang.data.response.ListAllocationItem
import com.capstone.beruang.data.response.OutcomeResponse
import com.capstone.beruang.data.response.SalaryResponse
import com.capstone.beruang.data.response.TagResponse
import retrofit2.http.*

interface ApiService {
    @GET("tag")
    suspend fun getTags(): List<TagResponse>

    //mengambil seluruh data alokasi
    @GET("allocations")
    suspend fun getAllAllocations(): AllocationResponse

    // Menambahkan data alokasi
    @POST("allocations")
    suspend fun addAllocation(@Body allocation: ListAllocationItem): AllocationResponse

    // Memperbarui data alokasi berdasarkan ID
    @PUT("allocations/{id}")
    suspend fun updateAllocation(
        @Path("id") id: Int,
        @Body allocation: ListAllocationItem
    ): AllocationResponse

    // Menghapus data alokasi berdasarkan ID
    @DELETE("allocations/{id}")
    suspend fun deleteAllocation(@Path("id") id: Int): Unit

    // Mendapatkan data gaji
    @GET("incomes")
    suspend fun getSalary(@Query("date") date: String): SalaryResponse

    // Membuat data baru dengan informasi id, salary, dan date (bulan)
    @POST("incomes/create")
    suspend fun createOrUpdateSalary(@Body updatedSalary: SalaryResponse): SalaryResponse

    // Memperbarui data berdasarkan tanggal (bulan)
    @PUT("incomes/update/{date}")
    suspend fun updateSalaryByDate(
        @Path("date") date: String,
        @Body updatedSalary: SalaryResponse
    ): SalaryResponse

    // Mendapatkan data kategori
    @GET("outcomes")
    suspend fun getOutcome(): OutcomeResponse

    // Membuat data outcomes baru
    @POST("outcomes")
    suspend fun createOutcome(@Body outcomes: OutcomeResponse): OutcomeResponse

    // Mendapatkan data outcomes berdasarkan ID
    @GET("outcomes/{id}")
    suspend fun getOutcomeById(@Path("id") id: Int): OutcomeResponse

    // Memperbarui data outcomes berdasarkan ID
    @PUT("outcomes/{id}")
    suspend fun updateOutcomeById(
        @Path("id") id: Int,
        @Body updatedOutcome: OutcomeResponse
    ): OutcomeResponse

    // Menghapus data outcomes berdasarkan ID
    @DELETE("outcomes/{id}")
    suspend fun deleteOutcomeById(@Path("id") id: Int): Unit

}