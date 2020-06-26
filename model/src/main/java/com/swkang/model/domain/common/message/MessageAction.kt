package com.swkang.model.domain.common.message

import androidx.annotation.StringRes
import com.swkang.model.base.redux.Action

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
sealed class MessageAction : Action

object HandledMessageAction : MessageAction()

data class ShowToastMessageAction(
    @StringRes val messageResId: Int = 0,
    val messageStr: String? = null
) : MessageAction()