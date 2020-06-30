package com.swkang.model.base

import androidx.lifecycle.ViewModel
import com.swkang.model.base.redux.State
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
abstract class BaseViewModel<S: State> : ViewModel(), RxDisposer {
    private lateinit var compositeDisposable: CompositeDisposable

    abstract fun render(state: S): Boolean

    override fun addDisposer(disposable: Disposable) {
        if (!::compositeDisposable.isInitialized) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable.add(disposable)
    }

    override fun dispose() {
        if (::compositeDisposable.isInitialized) {
            compositeDisposable.dispose()
        }
    }
}