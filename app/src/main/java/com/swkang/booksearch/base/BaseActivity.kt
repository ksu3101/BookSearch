package com.swkang.booksearch.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.redux.AppStore
import com.swkang.model.domain.common.message.HandledMessageAction
import com.swkang.model.domain.common.message.MessageState
import com.swkang.model.domain.common.message.ShowToastMessageState
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var appStore: AppStore

    @Inject
    lateinit var messageHelper: MessageHelper
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        handleAppState()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

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