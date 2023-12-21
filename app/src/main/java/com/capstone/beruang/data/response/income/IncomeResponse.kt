package com.capstone.beruang.data.response.income

import com.google.gson.annotations.SerializedName

data class IncomeResponse(

	@field:SerializedName("data")
	val data: List<Data?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("incomeId")
	val incomeId: String? = null,

	@field:SerializedName("salary")
	val salary: Int? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)
