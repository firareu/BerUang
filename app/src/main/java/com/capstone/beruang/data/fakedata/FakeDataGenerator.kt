package com.capstone.beruang.data.fakedata

import com.capstone.beruang.data.response.AllocationResponse
import com.capstone.beruang.data.response.ListAllocationItem

object FakeDataGenerator {
    val fakeAllocations = mutableListOf<ListAllocationItem>()
    fun generateFakeAllocationResponse(): AllocationResponse {
        val fakeList = generateFakeAllocations()
        val fakeAllocation = ListAllocationItem(1, "Fake Allocation", 50f, 1000f)

        return AllocationResponse(fakeList, false, "Success", fakeAllocation)
    }

    fun generateFakeAllocations(): List<ListAllocationItem> {


        fakeAllocations.add(ListAllocationItem(1, "Needs", 50f, 500000f))
        fakeAllocations.add(ListAllocationItem(2, "Lifestyle", 30f, 300000f))
        fakeAllocations.add(ListAllocationItem(3, "Goals", 20f, 200000f))

        return fakeAllocations
    }

    // Fungsi untuk membuat data palsu (Create)
    fun addFakeAllocation(allocation: ListAllocationItem) {
        fakeAllocations.add(allocation)
    }

    // Fungsi untuk mendapatkan semua data (Read)
    fun getAllFakeAllocations(): List<ListAllocationItem> {
        return fakeAllocations.toList()
    }

    // Fungsi untuk mengupdate data (Update)
    fun updateFakeAllocation(position: Int, updatedAllocation: ListAllocationItem) {
        if (position in fakeAllocations.indices) {
            fakeAllocations[position] = updatedAllocation
        }
    }

    // Fungsi untuk menghapus data (Delete)
    fun deleteFakeAllocation(position: Int) {
        if (position in fakeAllocations.indices) {
            fakeAllocations.removeAt(position)
        }
    }

}