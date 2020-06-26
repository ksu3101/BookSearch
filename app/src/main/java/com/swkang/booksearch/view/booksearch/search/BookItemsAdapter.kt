package com.swkang.booksearch.view.booksearch.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swkang.booksearch.R
import com.swkang.model.domain.booksearch.dto.Book
import kotlinx.android.synthetic.main.booksearch_itemview.view.*
import java.text.DecimalFormat

typealias OnItemClicked = (Book) -> Unit

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class BookItemsAdapter(
    private val onItemClicked: OnItemClicked
) : PagedListAdapter<Book, BookItemsAdapter.BookViewHolder>(bookDiffCalllback) {
    companion object {
        val bookDiffCalllback = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder.create(parent, onItemClicked)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        return holder.bind(getElement(position))
    }

    fun getElement(position: Int): Book {
        return super.getItem(position)
            ?: throw NullPointerException("[$position] element may not be Null.")
    }

    /**
     * ViewHolder
     */
    class BookViewHolder(
        view: View,
        val onItemClicked: (Book) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun bind(book: Book) {
            val res = itemView.resources
            val priceFormat = DecimalFormat("###,###")

            itemView.setOnClickListener { onItemClicked(book) }
            itemView.bookTitle.text = book.title
            itemView.bookContents.text =
                if (book.contents.isEmpty()) res.getString(R.string.bookitem_contents_placeholder) else book.contents
            itemView.bookPrice.text =
                res.getString(
                    R.string.bookitem_price,
                    priceFormat.format(book.price),
                    if (book.salePrice > 0) priceFormat.format(book.salePrice) else 0
                )
            itemView.bookAuthorDate.text = res.getString(
                R.string.bookitem_author_date,
                if (book.authors.isEmpty()) "" else book.authors[0],
                book.getDateByFormatted()
            )
            Glide.with(itemView.context)
                .load(book.thumbnail)
                .placeholder(R.drawable.img_placeholder)
                .into(itemView.bookThumbnail)

        }

        companion object {
            fun create(parent: ViewGroup, onItemClicked: OnItemClicked): BookViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.booksearch_itemview, parent, false)
                return BookViewHolder(view, onItemClicked)
            }
        }
    }
}

