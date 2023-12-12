package com.capstone.beruang.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class AllocationResponse(

    @field:SerializedName("listAllocation")
    val listAllocation: List<ListAllocationItem>,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("allocation")
    val allocation: ListAllocationItem
)
@Parcelize
data class ListAllocationItem (
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("allocation_name")
    var allocation_name: String,

    @field:SerializedName("percent")
    var percent: Float?,

    @field:SerializedName("total")
    var total: Float?
): Parcelable