package com.capstone.beruang.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Bookmark::class], version = 1)
abstract class BookmarkRoomDatabase : RoomDatabase() {
    abstract fun favDao(): BookmarkDao

    companion object {
        @Volatile
        private var INSTANCE: BookmarkRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): BookmarkRoomDatabase {
            if (INSTANCE == null) {
                synchronized(BookmarkRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BookmarkRoomDatabase::class.java, "fav_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as BookmarkRoomDatabase
        }
    }
}