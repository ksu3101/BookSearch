package com.swkang.model.domain.booksearch.dto

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
data class Book(
    val title: String,
    val contents: String,
    val url: String,
    val isbn: String,
    @field:Json(name = "datetime") val dateTime: String,
    val authors: List<String>,
    val publisher: String,
    val translators: List<String>,
    val price: Int,
    @field:Json(name = "sale_price") val salePrice: Int,
    val thumbnail: String,
    val status: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(contents)
        parcel.writeString(url)
        parcel.writeString(isbn)
        parcel.writeString(dateTime)
        parcel.writeStringList(authors)
        parcel.writeString(publisher)
        parcel.writeStringList(translators)
        parcel.writeInt(price)
        parcel.writeInt(salePrice)
        parcel.writeString(thumbnail)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    /**
     * 2014-11-17T00:00:00.000+09:00
     * [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
     * to
     * YYYY.MM.DD
     */
    fun getDateByFormatted(): String {
        if (dateTime.isEmpty()) return ""
        val originDateFormat = SimpleDateFormat("YYYY-MM-DD'T'hh:mm:ss.SSSXXX", Locale.getDefault())
        val newDateFormat = SimpleDateFormat("YYYY.MM.dd", Locale.getDefault())
        return newDateFormat.format(originDateFormat.parse(dateTime))
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}
