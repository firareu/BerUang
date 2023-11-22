package com.capstone.beruang.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.capstone.beruang.data.DatabaseContract.AllocationEntry

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_ENTRIES = "CREATE TABLE ${AllocationEntry.TABLE_NAME} (" +
                "${AllocationEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${AllocationEntry.COLUMN_NAME} TEXT," +
                "${AllocationEntry.COLUMN_PERCENT} REAL," +
                "${AllocationEntry.COLUMN_TOTAL} INTEGER)"

        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${AllocationEntry.TABLE_NAME}")
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Allocation.db"
    }
}
