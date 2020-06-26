package com.swkang.model.domain.booksearch

import com.swkang.model.base.redux.Action
import com.swkang.model.base.redux.ActionProcessor
import com.swkang.model.base.redux.AppState
import com.swkang.model.base.redux.Store
import com.swkang.model.domain.common.message.MessageAction
import com.swkang.model.domain.common.message.ShowToastMessageAction
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class BookSearchActionProcessor(
    val repo: BookSearchRepository
): ActionProcessor<AppState> {

    override fun run(action: Observable<Action>, store: Store<AppState>): Observable<out Action> {
        return action.compose(actionProcessor)
    }

    private val actionProcessor = createActionProcessor { shared ->
        arrayOf(

        )
    }

    private val requestBookSearch = actionTransformer<RequestBookSearchAction> { action ->
        repo.requestBookSearch(action.query, action.page)
            .map<Action> {
                BookPageResultAction(
                    action.query,
                    action.page,
                    it.meta.isEnd,
                    it.documents
                )
            }
            .onErrorReturn { handleError(it) }
            .toObservable()
    }

    private fun handleError(throwable: Throwable):MessageAction {
        return ShowToastMessageAction(messageStr = throwable.message)
    }

}


inline fun createActionProcessor(crossinline merger: (Observable<Action>) -> Array<Observable<Action>>): ObservableTransformer<Action, Action> =
    ObservableTransformer {
        it.publish { shared ->
            Observable.mergeArray<Action>(*merger(shared))
        }
    }

inline fun <T : Action> actionTransformer(crossinline body: (T) -> Observable<Action>): ObservableTransformer<T, Action> {
    return ObservableTransformer { actionObservable ->
        actionObservable.flatMap {
            body(it)
        }
    }
}