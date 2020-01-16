package de.sample.room.activity.gui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.sample.room.R
import de.sample.room.activity.data.entity.Book

class BookAdapter(private val context: Context): RecyclerView.Adapter<BookAdapter.ViewHolderCardBook>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private val coverUrls = context.resources.getStringArray(R.array.book_cover_urls)

    private var books: List<Book> = emptyList()

    fun updateBooks(books: List<Book>){
        this.books = books
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCardBook =
        ViewHolderCardBook(inflater.inflate(R.layout.card_book, parent, false))

    override fun onBindViewHolder(holder: ViewHolderCardBook, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.count()

    inner class ViewHolderCardBook(itemView: View): RecyclerView.ViewHolder(itemView){

        private val ivBookCover: ImageView = itemView.findViewById(R.id.ivBookCover)
        private val tvBookTitle: AppCompatTextView = itemView.findViewById(R.id.tvBookTitle)
        private val tvBookAuthorName: AppCompatTextView = itemView.findViewById(R.id.tvBookAuthorName)

        fun bind( book: Book ){

            Glide.with(context).load(coverUrls[book.urlIndex]).into(ivBookCover)
            tvBookTitle.text = book.title
            tvBookAuthorName.text = book.authorId.toString()
        }
    }
}

