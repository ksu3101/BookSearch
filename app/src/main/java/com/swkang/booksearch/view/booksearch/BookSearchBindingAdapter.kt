package com.swkang.booksearch.view.booksearch

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swkang.booksearch.R
import com.swkang.booksearch.view.booksearch.search.BookItemsAdapter
import com.swkang.model.domain.booksearch.dto.Book

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
@BindingAdapter(value = ["bookItems", "onItemClicked"])
fun setUpBookItems(rv: RecyclerView, items: PagedList<Book>?, onItemClicked: (Book) -> Unit) {
    val adapter = BookItemsAdapter { onItemClicked(it) }
    rv.adapter = adapter
    adapter.submitList(items)
}

@BindingAdapter("bookThumbnail")
fun loadBookImage(iv: ImageView, url: String?) {
    Glide.with(iv.context)
        .load(url)
        .placeholder(R.drawable.img_placeholder)
        .into(iv)
}
