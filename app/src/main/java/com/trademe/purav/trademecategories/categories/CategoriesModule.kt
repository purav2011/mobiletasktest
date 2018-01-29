package com.trademe.purav.trademecategories.categories

import com.trademe.purav.trademecategories.TradeMeService
import com.trademe.purav.trademecategories.data.Category
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * Created by purav on 30/01/2018.
 */
@Module
object CategoriesModule {

    @JvmStatic
    @Provides
    fun viewModelFactory(
            categoryObserver: Observer<Category>,
            categoryObservable: Observable<Category>,
            service: TradeMeService
    ): CategoriesViewModel.Factory = CategoriesViewModel
            .Factory(categoryObserver, categoryObservable, service)
}