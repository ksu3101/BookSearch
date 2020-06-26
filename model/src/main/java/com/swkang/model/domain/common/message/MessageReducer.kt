package com.swkang.model.domain.common.message

import com.swkang.model.base.redux.Action
import com.swkang.model.base.redux.Reducer

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class MessageReducer : Reducer<MessageState> {

    override fun reduce(oldState: MessageState, resultAction: Action): MessageState {
        return when (resultAction) {
            is HandledMessageAction -> HandledMessageState
            is ShowToastMessageAction -> {
                ShowToastMessageState(resultAction.messageResId, resultAction.messageStr)
            }
            else -> oldState
        }
    }

}