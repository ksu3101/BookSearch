package com.swkang.model.base.redux

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
* @author kangsungwoo
* @since 6/25/2020
*/
class ActionProcessorMiddleware<S : State>(
    private val actionProcessor: ActionProcessor<S>
) : MiddleWare<S> {
    override fun create(store: Store<S>, next: Dispatcher): Dispatcher {
        val actionEmitter:PublishSubject<Action> = PublishSubject.create()
        val disposable = actionProcessor.run(actionEmitter, store)
            .subscribe { action ->
                store.dispatch(action)
            }
        return { action ->
            next(action)
            actionEmitter.onNext(action)
        }
    }
}

class CombinedActionProcessor<S : State>(
    private val actionProcessors: Iterable<ActionProcessor<S>>
) : ActionProcessor<S> {
    override fun run(action: Observable<Action>, store: Store<S>): Observable<out Action> {
        return Observable.fromIterable(actionProcessors).flatMap { it.run(action, store) }
    }
}