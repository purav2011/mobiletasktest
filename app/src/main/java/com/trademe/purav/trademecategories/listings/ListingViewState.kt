package com.trademe.purav.trademecategories.listings

import com.trademe.purav.trademecategories.data.SearchResult

/**
 * Created by purav on 30/01/2018.
 */
sealed class ListingViewState

object Loading : ListingViewState()
data class Failure(val error: Throwable) : ListingViewState()
data class Success(val searchResult: SearchResult) : ListingViewState()
