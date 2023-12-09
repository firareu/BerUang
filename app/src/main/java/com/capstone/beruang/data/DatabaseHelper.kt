package com.capstone.beruang.data

import android.content.ContentValues
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

    fun insertAllocation(allocation: Allocation): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(AllocationEntry.COLUMN_NAME, allocation.allocation_name)
        contentValues.put(AllocationEntry.COLUMN_PERCENT, allocation.percent)
        contentValues.put(AllocationEntry.COLUMN_TOTAL, allocation.total)

        val newRowId = db.insert(AllocationEntry.TABLE_NAME, null, contentValues)
        db.close()
        return newRowId
    }

    fun deleteAllocation(allocationId: Int): Int {
        val db = this.writableDatabase
        val selection = "${AllocationEntry.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(allocationId.toString())
        val deletedRows = db.delete(AllocationEntry.TABLE_NAME, selection, selectionArgs)
        db.close()
        return deletedRows
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Allocation.db"
    }
}
