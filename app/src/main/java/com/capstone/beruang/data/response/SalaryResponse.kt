package com.capstone.beruang.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SalaryResponse (
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("salary")
    var salary: Float?,

    @field:SerializedName("date")
    var date: String?
): Parcelable