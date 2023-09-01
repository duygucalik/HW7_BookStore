package com.example.hw7_bookstore.ui.Books.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw7_bookstore.common.loadImage
import com.example.hw7_bookstore.data.model.Book
import com.example.hw7_bookstore.databinding.ItemBookBinding

class BooksAdapter (
    private val bookListener: BookListener
) : ListAdapter<Book, BooksAdapter.BookViewHolder>(ProductDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder(
            ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            bookListener
        )

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) =
        holder.bind(getItem(position))

    class BookViewHolder(
        private val binding: ItemBookBinding,
        private val booksListener: BookListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) = with(binding) {
            tvTitle.text = book.name
            tvPrice.text = "${book.price} ₺"

            ivBook.loadImage(book.imageUrl)

            root.setOnClickListener {
                booksListener.onBookClick(book.id ?: 1)
            }
        }
    }

    class ProductDiffCallBack : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }

    interface BookListener {
        fun onBookClick(id: Int)
    }
}