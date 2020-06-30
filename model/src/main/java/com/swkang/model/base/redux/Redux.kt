package com.swkang.model.base.redux

import io.reactivex.rxjava3.core.Observable


interface Action

interface State

interface Reducer<S : State> {
    fun reduce(oldState: S, resultAction: Action): S
}

typealias Dispatcher = (Action) -> Unit

interface Store<S: State> {
    fun getState(): S

    fun dispatch(action: Action)

    fun stateListener(): Observable<S>
}

interface MiddleWare<S: State> {
    fun create(store: Store<S>, next: Dispatcher): Dispatcher
}