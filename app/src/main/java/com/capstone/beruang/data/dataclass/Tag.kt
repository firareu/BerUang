package com.capstone.beruang.data.dataclass

import android.os.Parcelable
import com.capstone.beruang.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tag (
    val id: Int,
    val title: String,
    val image: Int
): Parcelable{
    /*companion object {
        fun getTag(): List<Tag> {
            return listOf(
                Tag("sports", "Sports", R.drawable.for_article),
                Tag("entertainment", "Entertainment", R.drawable.for_article),
                Tag("health", "Health", R.drawable.for_article),
                Tag("business", "Business", R.drawable.for_article),
                Tag("technology", "Technology", R.drawable.for_article),
                Tag("science", "Science", R.drawable.for_article)
            )
        }
    }*/
}