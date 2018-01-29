package com.trademe.purav.trademecategories.data

import com.google.gson.annotations.SerializedName

/**
 * Created by purav on 30/01/2018.
 */
data class Listing(
        @SerializedName("ListingId") val id: Int,
        @SerializedName("Title") val title: String,
        @SerializedName("PictureHref") val pictureHref: String
)