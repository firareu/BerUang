package com.capstone.beruang.data

import android.provider.BaseColumns

object DatabaseContract {
    // Kontrak untuk tabel Allocations
    class AllocationEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "allocations"
            const val COLUMN_ID = "id"
            const val COLUMN_NAME = "allocation_name"
            const val COLUMN_PERCENT = "percent"
            const val COLUMN_TOTAL = "total"
        }
    }


}
