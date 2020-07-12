package com.swkang.booksearch.base

import androidx.appcompat.app.AppCompatActivity
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.redux.AppStore
import com.swkang.model.domain.common.message.HandledMessageAction
import com.swkang.model.domain.common.message.MessageState
import com.swkang.model.domain.common.message.ShowToastMessageState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var appStore: AppStore
    @Inject
    lateinit var messageHelper: MessageHelper
    private val compositeDisposable = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        handleAppState()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun handleAppState() {
        compositeDisposable.add(
            appStore.stateListener()
                .flatMap { Observable.fromIterable(it.getSubStates()) }
                .ofType(MessageState::class.java)
                .distinctUntilChanged()
                .doOnNext { appStore.dispatch(HandledMessageAction) }
                .subscribe { handleMessageState(it) }
        )
    }

    private fun handleMessageState(state: MessageState) {
        when (state) {
            is ShowToastMessageState -> {
                messageHelper.showToast(state.messageResId)
            }

        }
    }

}