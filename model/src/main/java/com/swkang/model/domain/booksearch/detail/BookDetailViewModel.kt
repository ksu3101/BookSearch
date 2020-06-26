package com.swkang.model.domain.booksearch.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.model.R
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.helper.ResourceHelper
import com.swkang.model.domain.booksearch.BookSearchState
import com.swkang.model.domain.booksearch.dto.Book
import java.text.DecimalFormat
import javax.inject.Inject

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class BookDetailViewModel @Inject constructor(
    val resourceHelper: ResourceHelper
) : BaseViewModel<BookSearchState>() {
    private lateinit var book: Book

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _contents = MutableLiveData<String>()
    val contents: LiveData<String>
        get() = _contents

    private val _dateTime = MutableLiveData<String>()
    val dateTime: LiveData<String>
        get() = _dateTime

    private val _authors = MutableLiveData<String>()
    val authors: LiveData<String>
        get() = _authors

    private val _publisher = MutableLiveData<String>()
    val publisher: LiveData<String>
        get() = _publisher

    private val _translator = MutableLiveData<String>()
    val translator: LiveData<String>
        get() = _translator

    private val _price = MutableLiveData<String>()
    val price: LiveData<String>
        get() = _price

    private val _salePrice = MutableLiveData<String>()
    val salePrice: LiveData<String>
        get() = _salePrice

    private val _thumbNail = MutableLiveData<String>()
    val thumbnail: LiveData<String>
        get() = _thumbNail

    fun setBook(book: Book) {
        this.book = book

        val emptyStringPlaceHolder =
            resourceHelper.getString(R.string.bookitem_contents_placeholder)
        val priceFormat = DecimalFormat("###,###")

        _title.value = book.title
        _contents.value =
            if (book.contents.isEmpty()) emptyStringPlaceHolder else book.contents
        _dateTime.value = book.getDateByFormatted()
        _authors.value =
            book.authors.foldIndexed("") { i, acc, s -> acc + s + if (i < book.authors.size - 1) ", " else "" }
        _publisher.value = if (book.publisher.isEmpty()) emptyStringPlaceHolder else book.publisher
        _translator.value =
            book.translators.foldIndexed("") { i, acc, s -> acc + s + if (i < book.translators.size - 1) ", " else "" }

        _price.value = resourceHelper.getFormatString(R.string.bookdetail_price, priceFormat.format(book.price))
        _salePrice.value = resourceHelper.getFormatString(R.string.bookdetail_saleprice, priceFormat.format(book.salePrice))
        _thumbNail.value = book.thumbnail
    }

    override fun render(state: BookSearchState): Boolean {
        return when (state) {
            else -> false
        }
    }

}