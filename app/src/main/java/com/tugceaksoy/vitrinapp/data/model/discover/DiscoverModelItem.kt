package com.tugceaksoy.vitrinapp.data.model.discover

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DiscoverModelItem(
    val categories: List<Category>?,
    val collections: List<Collections>?,
    val featured: List<Featured>?,
    val products: List<Product>?,
    val shops: List<Shop>?,
    val title: String?,
    val type: String?
): Parcelable
