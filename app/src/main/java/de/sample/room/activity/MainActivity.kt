package de.sample.room.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.sample.room.R
import de.sample.room.activity.gui.adapter.BookAdapter
import de.sample.room.activity.gui.adapter.RecyclerViewItemSpacer
import de.sample.room.activity.viewmodel.ViewModelBooks
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModelBooks: ViewModelBooks by lazy {
        ViewModelProvider(this)[ViewModelBooks::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapterBooks = BookAdapter(this)

        rvBookList.layoutManager = LinearLayoutManager(this)
        rvBookList.addItemDecoration(RecyclerViewItemSpacer())

        rvBookList.adapter = adapterBooks

        viewModelBooks.allBooks.observe(this, Observer {
            adapterBooks.updateBooks(it)
        })

        viewModelBooks.fetchBooks()
    }
}
