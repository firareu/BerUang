package com.capstone.beruang.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Allocation(
    var id: Int = 0,
    var allocation_name: String,
    var percent: Float?,
    var total: Float?
): Parcelable
