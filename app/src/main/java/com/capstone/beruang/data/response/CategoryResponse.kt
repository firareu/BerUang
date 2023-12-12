package com.capstone.beruang.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryResponse (
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("allocation_name")
    var allocation_name: String,

    /*@field:SerializedName("outcomeType")
    var outcomeType: String,*/

    @field:SerializedName("totaloutcome")
    var totaloutcome: Float?
): Parcelable