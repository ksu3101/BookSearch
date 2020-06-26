package com.swkang.model.domain.common.message

import androidx.annotation.StringRes
import com.swkang.model.base.redux.State

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
sealed class MessageState : State

object HandledMessageState : MessageState()

data class ShowToastMessageState(
    @StringRes val messageResId: Int = 0,
    val messageStr: String? = null
) : MessageState()