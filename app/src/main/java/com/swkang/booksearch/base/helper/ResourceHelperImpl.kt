package com.swkang.booksearch.base.helper

import android.content.Context
import com.swkang.model.base.helper.ResourceHelper

/**
 * @author kangsungwoo
 * @since 6/19/2020
 */
class ResourceHelperImpl(
    val context: Context
) : ResourceHelper {

    override fun getString(strResId: Int): String = context.getString(strResId)

    override fun getFormatString(strResId: Int, vararg args: Any?): String =
        context.getString(strResId, *args)

}