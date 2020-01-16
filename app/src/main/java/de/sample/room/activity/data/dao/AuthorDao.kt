package de.sample.room.activity.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.sample.room.activity.data.entity.Author
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface AuthorDao{

    @Query("SELECT * FROM authors")
    suspend fun getAllAuthors(): List<Author>

    @Insert
    suspend fun addAuthor(author: Author)

    @Query("DELETE FROM authors")
    suspend fun deleteAll()
}