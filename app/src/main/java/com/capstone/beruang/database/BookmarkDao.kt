package com.capstone.beruang.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(fav: Bookmark)

    @Update
    fun update(fav: Bookmark)

    @Delete
    fun delete(fav: Bookmark)

    @Query("SELECT * from bookmark ORDER BY id ASC")
    fun getAllFavorite(): LiveData<List<Bookmark>>
}