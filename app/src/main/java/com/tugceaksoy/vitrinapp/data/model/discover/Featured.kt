package com.tugceaksoy.vitrinapp.data.model.discover

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Featured(
    val cover: Cover,
    val sub_title: String,
    val title: String,
): Parcelable