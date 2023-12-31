package com.capstone.beruang.data.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article (
    val title: String,
    val image: Int,
    val content: String,
): Parcelable