package de.sample.room.activity.data.repository

import android.content.Context
import de.sample.room.activity.data.database.BookDatabase
import kotlinx.coroutines.CoroutineScope

class BookRepository(context: Context, couroutineScope: CoroutineScope) {

    private val bookDatabase = BookDatabase.getDatabase(context, coroutineScope = couroutineScope)
    private val bookDao = bookDatabase.bookDao()

    suspend fun getAllBooks() = bookDao.getAllBooks()
}