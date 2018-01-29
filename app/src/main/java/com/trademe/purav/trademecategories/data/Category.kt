package com.trademe.purav.trademecategories.data

import com.google.gson.annotations.SerializedName

/**
 * Created by purav on 30/01/2018.
 */
data class Category(
        @SerializedName("Number") val number: String,
        @SerializedName("Name") val name: String,
        @SerializedName("Subcategories") val subcategories: List<Category>,
        @SerializedName("IsLeaf") val isLeaf: Boolean
)