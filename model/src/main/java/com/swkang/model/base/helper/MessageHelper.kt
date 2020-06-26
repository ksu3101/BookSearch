package com.swkang.model.base.helper

import androidx.annotation.StringRes

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
interface MessageHelper {

    fun showToast(
        @StringRes msgResId: Int,
        msgStr: String? = null
    )

}