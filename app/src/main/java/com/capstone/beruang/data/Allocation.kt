package com.capstone.beruang.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
@Entity
data class Allocation(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var allocation_name: String,
    var percent: Float?,
    var total: Float?
): Parcelable
