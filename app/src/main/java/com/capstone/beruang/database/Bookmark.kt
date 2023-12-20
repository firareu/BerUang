package com.capstone.beruang.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "bookmark")
@Parcelize
data class Bookmark(
    @PrimaryKey
    val id: String,
    val publishedAt: String,
    val author: String,
    val urlToImage: String,
    val description: String,
    val sourceName: String,
    val title: String,
    val url: String,
    val content: String
): Parcelable
