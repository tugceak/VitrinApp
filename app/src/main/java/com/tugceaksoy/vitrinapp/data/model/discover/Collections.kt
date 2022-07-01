package com.tugceaksoy.vitrinapp.data.model.discover

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Collections(
    val cover: Cover?,
    val definition: String?,
    val id: Int?,
    val logo: Cover?,
    val share_url: String?,
    val start: String?,
    val title: String?
): Parcelable