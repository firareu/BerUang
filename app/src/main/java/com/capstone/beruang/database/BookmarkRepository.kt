package com.capstone.beruang.database

import android.app.Application
import androidx.lifecycle.LiveData

class BookmarkRepository(application: Application) {
    private val favDao: BookmarkDao

    init {
        val db = BookmarkRoomDatabase.getDatabase(application)
        favDao = db.favDao()
    }

    fun getAllFavorites(): LiveData<List<Bookmark>> = favDao.getAllFavorite()


    suspend fun insert(fav: Bookmark) {
        favDao.insert(fav)
    }

    suspend fun delete(fav: Bookmark) {
        favDao.delete(fav)
    }
}