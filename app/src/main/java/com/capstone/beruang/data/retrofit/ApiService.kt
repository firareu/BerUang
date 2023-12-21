package com.capstone.beruang.data.retrofit

import com.capstone.beruang.data.fakedata.FakeDataGenerator
import com.capstone.beruang.data.response.AllocationResponse
import com.capstone.beruang.data.response.CategoryResponse
import com.capstone.beruang.data.response.ListAllocationItem
import com.capstone.beruang.data.response.LoginResponse
import com.capstone.beruang.data.response.RegisterResponse
import com.capstone.beruang.data.response.SalaryResponse
import com.capstone.beruang.data.response.article.ArticleResponse
import com.capstone.beruang.data.response.article.NewsResponseItem
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    //mengambil seluruh data alokasi
    /*@GET("allocations")
    suspend fun getAllAllocations(): AllocationResponse*/
    @GET("allocations")
    suspend fun getAllAllocations(): AllocationResponse {
        return FakeDataGenerator.generateFakeAllocationResponse()
    }

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

    /*// Mendapatkan data gaji
    @GET("salaries")
    suspend fun getSalary(): SalaryResponse

    // Membuat data baru dengan informasi id, salary, dan date (bulan)
    @POST("salaries/create")
    suspend fun createOrUpdateSalary(@Body updatedSalary: SalaryResponse): SalaryResponse

    // Memperbarui data berdasarkan tanggal (bulan)
    @PUT("salaries/update/{date}")
    suspend fun updateSalaryByDate(
        @Path("date") date: String,
        @Body updatedSalary: SalaryResponse
    ): SalaryResponse*/

    @GET("salaries")
    suspend fun getSalary(): SalaryResponse {
        return FakeDataGenerator.generateFakeSalary()
    }

    // Mendapatkan data kategori
    @GET("categories")
    suspend fun getCategories(): List<CategoryResponse>

    // Membuat data kategori baru
    @POST("categories")
    suspend fun createCategory(@Body category: CategoryResponse): CategoryResponse

    // Mendapatkan data kategori berdasarkan ID
    @GET("categories/{id}")
    suspend fun getCategoryById(@Path("id") id: Int): CategoryResponse

    // Memperbarui data kategori berdasarkan ID
    @PUT("categories/{id}")
    suspend fun updateCategoryById(
        @Path("id") id: Int,
        @Body updatedCategory: CategoryResponse
    ): CategoryResponse

    // Menghapus data kategori berdasarkan ID
    @DELETE("categories/{id}")
    suspend fun deleteCategoryById(@Path("id") id: Int): Unit

    //Article
    @GET("/v2/top-headlines")
    fun getTopHeadlines(@QueryMap params: Map<String, String>): Call<ArticleResponse>
    @POST("auth/login") // Adjust the endpoint accordingly
    suspend fun loginUser(@Body requestBody: RequestBody): Response<LoginResponse>

    @POST("auth/register")
    suspend fun registerUser(@Body requestBody: RequestBody): Response<RegisterResponse>

    @GET("recommend")
    suspend fun getNews(): Response<List<NewsResponseItem>>

}