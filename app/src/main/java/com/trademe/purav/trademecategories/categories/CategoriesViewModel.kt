package com.trademe.purav.trademecategories.categories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.trademe.purav.trademecategories.TradeMeService
import com.trademe.purav.trademecategories.data.Category
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by purav on 30/01/2018.
 */
class CategoriesViewModel(
        private val categoryObserver: Observer<Category>,
        categoryObservable: Observable<Category>,
        service: TradeMeService
) : ViewModel() {
    private val disposables = CompositeDisposable()

    val viewState = MutableLiveData<CategoriesViewState>()

    init {
        viewState.value = Loading
        disposables.add(service.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onCategorySelected(it)
                }, {
                    viewState.value = Failure(it)
                }));

        disposables.add(categoryObservable
                .filter({ !it.isLeaf })
                .subscribe({ viewState.value = Success(it) }))
    }

    fun onCategorySelected(category: Category) {
        categoryObserver.onNext(category)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    class Factory(
            private val categoryObserver: Observer<Category>,
            private val categoryObservable: Observable<Category>,
            private val service: TradeMeService
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
                CategoriesViewModel(categoryObserver, categoryObservable, service) as T
    }
}