package de.sample.room.activity.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.sample.room.activity.data.entity.Author

@Dao
interface AuthorDao{

    @Insert
    suspend fun addAuthor(author: Author)

    @Query("DELETE FROM authors")
    suspend fun deleteAll()
}