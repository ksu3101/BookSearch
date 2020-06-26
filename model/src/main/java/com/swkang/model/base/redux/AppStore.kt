package com.swkang.model.base.redux

import com.swkang.model.domain.booksearch.BookSearchState
import com.swkang.model.domain.common.message.MessageState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class AppStore(
    appState: AppState,
    reducer: Reducer<AppState>,
    middleWares: List<MiddleWare<AppState>>
) : Store<AppState> {
    private val stateEmitter: BehaviorSubject<AppState> = BehaviorSubject.create()
    private var state: AppState = appState
    private var dispatcher: Dispatcher = { action: Action ->
        state = reducer.reduce(getState(), action)
        stateEmitter.onNext(state)
    }

    init {
        dispatcher = middleWares.foldRight(dispatcher) { nextMiddleware, prevDispatcher ->
            nextMiddleware.create(this, prevDispatcher)
        }
    }

    override fun getState(): AppState = state

    override fun dispatch(action: Action) {
        dispatcher(action)
    }

    override fun stateListener(): Observable<AppState> =
        stateEmitter.hide().observeOn(AndroidSchedulers.mainThread())

}

data class AppState(
    val messageState: MessageState,
    val bookSearchState: BookSearchState
) : State {

    fun getSubStates(): List<State> {
        return listOf(
            messageState,
            bookSearchState
        )
    }
}
