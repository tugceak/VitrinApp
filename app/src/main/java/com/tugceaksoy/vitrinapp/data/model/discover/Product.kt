package com.tugceaksoy.vitrinapp.data.model.discover

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val category: Category?,
    val definition: String?,
    val images: List<Cover>,
    val price: Int?,
    val shop: Shop?,
    val title: String?
): Parcelable
