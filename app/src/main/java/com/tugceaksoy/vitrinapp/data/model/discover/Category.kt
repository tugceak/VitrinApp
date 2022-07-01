package com.tugceaksoy.vitrinapp.data.model.discover

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    val cover: Cover?,
    val name: String?,
): Parcelable