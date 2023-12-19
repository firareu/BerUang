package com.capstone.beruang.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class ArticleResponse {
}

@Parcelize
data class TagResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("image")
    val image: String? = null
) : Parcelable