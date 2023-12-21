package com.capstone.beruang.data.response.allocation

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class AllocationResponse(

	@field:SerializedName("allocation")
	val allocation: List<AllocationItem?>? = null
)

@Parcelize
data class AllocationItem(

	@field:SerializedName("amount")
	val amount: Int? = null,

	@field:SerializedName("allocationId")
	val allocationId: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("precentage")
	val precentage: Float? = null,

	@field:SerializedName("userId")
	val userId: String? = null
): Parcelable
