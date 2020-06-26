package com.swkang.model.base.redux

import io.reactivex.Observable

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
interface ActionProcessor<S: State> {
    fun run(action: Observable<Action>, store: Store<S>): Observable<out Action>
}