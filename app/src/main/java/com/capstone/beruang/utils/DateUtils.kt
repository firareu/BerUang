package com.capstone.beruang.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtils {
    // Fungsi untuk mendapatkan tanggal (bulan) saat ini dalam format yang diinginkan
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentMonth(): String {
        val currentDateTime = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM") // Format tanggal (bulan) yang diinginkan
        return currentDateTime.format(formatter)
    }
}