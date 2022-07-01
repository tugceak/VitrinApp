package com.tugceaksoy.vitrinapp.data.model.discover

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryChildren(
    val children: List<CategoryChildren>,
    val cover: Cover,
    val id: Int,
    val logo: Cover,
    val name: String,
    val order: Int,
    val parent_category: ParentCategory,
    val parent_id: Int
): Parcelable