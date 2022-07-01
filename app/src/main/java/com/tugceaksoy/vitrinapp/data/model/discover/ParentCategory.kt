package com.tugceaksoy.vitrinapp.data.model.discover

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParentCategory(
    val id: Int,
    val name: String,
    val order: Int,
    val parent_category: ParentCategory?,
    val parent_id: Int?
): Parcelable