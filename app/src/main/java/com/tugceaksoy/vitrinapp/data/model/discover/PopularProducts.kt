package com.tugceaksoy.vitrinapp.data.model.discover

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularProducts(
    val images: List<PopularImages>?
): Parcelable
