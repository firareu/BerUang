package com.capstone.beruang.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Allocation(
    var id: Int = 0,
    val allocation_name: String,
    val percent: Float?,
    val total: Float?
): Parcelable
