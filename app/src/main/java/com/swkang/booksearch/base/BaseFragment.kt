package com.swkang.booksearch.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.swkang.model.BR
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.redux.AppStore
import com.swkang.model.base.redux.State
import com.swkang.model.domain.booksearch.BookSearchState
import com.swkang.model.domain.booksearch.search.BookSearchViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
abstract class BaseFragment<S : State> : Fragment(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var store: AppStore
    private lateinit var binding: ViewDataBinding
    private lateinit var viewModel: BaseViewModel<S>
    private val compositeDisposable = CompositeDisposable()

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun createViewModel(): BaseViewModel<S>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = createViewModel()
        compositeDisposable.clear()

        val share = store.stateListener()
            .flatMap { Observable.fromIterable(it.getSubStates()) }
            .distinctUntilChanged()

        if (viewModel is BookSearchViewModel) {
            compositeDisposable.add(
                share.ofType(BookSearchState::class.java)
                    .subscribe {
                        val vm = viewModel as BookSearchViewModel
                        vm.render(it)
                    }
            )
        }

        binding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            false
        )
        binding.setVariable(BR.vm, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        compositeDisposable.dispose()
        if (::viewModel.isInitialized) {
            viewModel.dispose()
        }
        super.onDestroyView()
    }

}