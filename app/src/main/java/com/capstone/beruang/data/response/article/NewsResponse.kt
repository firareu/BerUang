package com.capstone.beruang.data.response.article

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResponse(

    @field:SerializedName("NewsResponse")
    val newsResponse: List<NewsResponseItem>
): Parcelable

@Parcelize
data class NewsResponseItem(

    @field:SerializedName("Indeks")
    val indeks: Int,

    @field:SerializedName("Category")
    val category: String,

    @field:SerializedName("Content")
    val content: String,

    @field:SerializedName("Headline")
    val headline: String,

    @field:SerializedName("Author")
    val author: String,

    @field:SerializedName("Image")
    val image: String,

    @field:SerializedName("Date")
    val date: String,

    @field:SerializedName("Link")
    val link: String,

    var isBookmarked: Boolean = false
): Parcelable