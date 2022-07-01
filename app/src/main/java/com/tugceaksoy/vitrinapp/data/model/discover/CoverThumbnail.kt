package com.tugceaksoy.vitrinapp.data.model.discover

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoverThumbnail(
    val height: Int,
    val url: String,
    val width: Int
): Parcelable