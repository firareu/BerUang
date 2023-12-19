package com.capstone.beruang.data.response

import com.google.gson.annotations.SerializedName

data class OutcomeResponse(

	@field:SerializedName("allocation")
	val allocation: List<AllocationItem?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class AllocationItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("amount")
	val amount: Any? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: String? = null
)
