package com.trademe.purav.trademecategories.categories

import com.trademe.purav.trademecategories.data.Category

/**
 * Created by purav on 30/01/2018.
 */
sealed class CategoriesViewState

object Loading : CategoriesViewState()
data class Failure(val error: Throwable) : CategoriesViewState()
data class Success(val category: Category) : CategoriesViewState()
