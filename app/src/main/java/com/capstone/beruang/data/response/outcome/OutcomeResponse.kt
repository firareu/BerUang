package com.capstone.beruang.data.response.outcome

import com.google.gson.annotations.SerializedName

data class OutcomeResponse(

	@field:SerializedName("outcome")
	val outcome: List<OutcomeItem?>? = null
)

data class OutcomeItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("amount")
	val amount: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("outcomeId")
	val outcomeId: String? = null,

	@field:SerializedName("allocationId")
	val allocationId: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)
