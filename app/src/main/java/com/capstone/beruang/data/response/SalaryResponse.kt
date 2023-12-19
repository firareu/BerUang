package com.capstone.beruang.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class SalaryResponse (
    val error: Boolean,
    val message: String,
    val incomes: Incomes
)
@Parcelize
data class Incomes(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("salary")
    var salary: Float?,

    @field:SerializedName("date")
    var date: String?
):Parcelable