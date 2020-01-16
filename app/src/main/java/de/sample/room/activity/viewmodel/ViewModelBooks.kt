package de.sample.room.activity.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import de.sample.room.activity.data.entity.Book
import de.sample.room.activity.data.repository.BookRepository
import kotlinx.coroutines.launch

class ViewModelBooks(application: Application): AndroidViewModel(application) {

    private val bookRepository = BookRepository(application.applicationContext, viewModelScope)

    val allBooks = MutableLiveData<List<Book>>(emptyList())

    fun fetchBooks() = viewModelScope.launch {

        allBooks.postValue(bookRepository.getAllBooks())
    }
}