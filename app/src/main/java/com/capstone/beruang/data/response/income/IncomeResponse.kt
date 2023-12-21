package com.capstone.beruang.data.response.income

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class IncomeResponse(

	@field:SerializedName("income")
	val income: List<IncomeItem?>? = null
)
@Parcelize
data class IncomeItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("incomeId")
	val incomeId: String? = null,

	@field:SerializedName("salary")
	val salary: Int? = null,

	@field:SerializedName("userId")
	val userId: String? = null
): Parcelable
