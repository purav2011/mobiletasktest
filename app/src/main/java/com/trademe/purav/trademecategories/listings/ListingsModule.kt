package com.trademe.purav.trademecategories.listings

import com.trademe.purav.trademecategories.TradeMeService
import dagger.Module
import dagger.Provides
import io.reactivex.Observable

/**
 * Created by purav on 30/01/2018.
 */
@Module
object ListingsModule {
    @JvmStatic
    @Provides
    fun viewModelFactory(
            categoryNumberObservable: Observable<String>,
            service: TradeMeService
    ) = ListingsViewModel.Factory(categoryNumberObservable, service)
}