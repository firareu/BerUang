package com.capstone.beruang.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class AllocationResponse(

    @field:SerializedName("allocation")
    val allocation: List<ListAllocationItem?>? = null,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

@Parcelize
data class ListAllocationItem(

    @field:SerializedName("amount")
    val amount: Float? = null,

    @field:SerializedName("percentage")
    val percentage: Float? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("category")
    val category: String? = null
):Parcelable