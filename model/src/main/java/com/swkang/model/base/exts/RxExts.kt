package com.swkang.model.base.exts

import android.util.Log
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.redux.State
import io.reactivex.Observable

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */

inline fun <reified S> Observable<S>.isStateType(): Observable<S> {
    Log.e("isStateType", "/// isStateType ${S::class}")
    return ofType<S>(S::class.java)
}
