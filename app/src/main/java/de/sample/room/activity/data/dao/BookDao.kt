package de.sample.room.activity.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.sample.room.activity.data.entity.Book

@Dao
interface BookDao {

    @Insert
    suspend fun addBook(book: Book)

    @Query("DELETE FROM books")
    suspend fun deleteAll()
}