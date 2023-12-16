package com.capstone.beruang.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tag (
    val title: String,
    val image: Int
): Parcelable
