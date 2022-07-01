package com.tugceaksoy.vitrinapp.data.model.discover

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shop(
    val cover: Cover?,
    val definition: String?,
    val logo: Cover?,
    val name: String?,
    val product_count: Int?,
    val popular_products: List<PopularProducts>?,

): Parcelable