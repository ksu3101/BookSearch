package com.swkang.model.base

import io.reactivex.rxjava3.disposables.Disposable

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
interface RxDisposer {
    fun addDisposer(disposable: Disposable)

    fun dispose()
}