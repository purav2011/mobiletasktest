package com.trademe.purav.trademecategories

import com.trademe.purav.trademecategories.data.Category
import com.trademe.purav.trademecategories.data.SearchResult
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by purav on 30/01/2018.
 */
interface TradeMeService {
    @GET("v1/Categories/0.json")
    fun getCategories(): Single<Category>

    @GET("v1/Search/General.json")
    fun search(
            @Query("category") category: String = "0",
            @Query("rows") rows: Int = 20
    ): Observable<SearchResult>
}