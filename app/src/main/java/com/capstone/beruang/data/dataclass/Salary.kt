package com.capstone.beruang.data.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Salary(
    var id: Int = 0,
    val salary: Float?
): Parcelable
