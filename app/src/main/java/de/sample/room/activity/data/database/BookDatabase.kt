package de.sample.room.activity.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import de.sample.room.activity.data.dao.AuthorDao
import de.sample.room.activity.data.dao.BookDao
import de.sample.room.activity.data.entity.Author
import de.sample.room.activity.data.entity.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Book::class, Author::class], version = 1)
abstract class BookDatabase: RoomDatabase() {

    abstract fun authorDao(): AuthorDao
    abstract fun bookDao(): BookDao

    companion object {

        private const val DB_NAME = "book_database"

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context, coroutineScope: CoroutineScope): BookDatabase {

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    DB_NAME
                ).addCallback(BookDatabaseCallback(coroutineScope)).build()

                INSTANCE = instance
                return instance
            }
        }
    }

    class BookDatabaseCallback(private val coroutineScope: CoroutineScope): RoomDatabase.Callback() {

        // This is called every time the db is opened (any app start)
        override fun onOpen(db: SupportSQLiteDatabase) {

            super.onOpen(db)

            INSTANCE?.let { database ->
                coroutineScope.launch {
                    populateDatabase(database.authorDao(), database.bookDao())
                }
            }
        }

        private suspend fun populateDatabase(authorDao: AuthorDao, bookDao: BookDao) {

            bookDao.deleteAll()
            authorDao.deleteAll()

            authorDao.addAuthor(Author(0, firstName = "Delia", lastName = "Owens"))
            authorDao.addAuthor(Author(1, firstName = "Michelle", lastName = "Obama"))
            authorDao.addAuthor(Author(2, firstName = "Mark", lastName = "Manson"))

            bookDao.addBook(Book(title = "Where the Crawdads Sing", releaseDate = "2018-08-14", authorId = 1, urlIndex = 0) )
            bookDao.addBook(Book(title = "Becoming", releaseDate = "2018-11-13", authorId = 2, urlIndex = 1) )
            bookDao.addBook(Book(title = "The Subtle Art of Not Giving a F*ck", releaseDate = "2016-09-13", authorId = 2, urlIndex = 2) )
        }
    }
}