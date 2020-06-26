package com.swkang.booksearch.base.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

/**
 * @author kangsungwoo
 * @since 6/25/2020
 */
class ViewModelFactory @Inject constructor(
    private val vms: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return vms[modelClass]?.get() as T
    }
}

@Module
abstract class ViewModelBuilder {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

/**
 * ViewModel 을 Mutable map 에 저장하기 위한 Key의 어노테이션 클래스.
 * Dagger 의 `@MapKey` 어노테이션을 이용 하여 `value` 패러미터를
 * Map 의 Key 로 사용함을 컴파일러에 알린다.
 */
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)