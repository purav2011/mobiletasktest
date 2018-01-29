package com.trademe.purav.trademecategories.data

import com.google.gson.annotations.SerializedName

/**
 * Created by purav on 30/01/2018.
 */
data class SearchResult(
        @SerializedName("TotalCount") val totalCount: Int,
        @SerializedName("List") val list: List<Listing>
)