package com.capstone.beruang.data.response.article

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TagResponse(
	val tagResponse: List<TagResponseItem>
) : Parcelable

@Parcelize
data class TagResponseItem(
	val image: String,
	val id: Int,
	val title: String
) : Parcelable
