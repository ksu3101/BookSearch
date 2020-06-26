package com.swkang.model.base.helper

import androidx.annotation.StringRes

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
interface ResourceHelper {

    fun getString(@StringRes strResId: Int): String

    fun getFormatString(@StringRes strResId: Int, vararg args: Any?): String

}