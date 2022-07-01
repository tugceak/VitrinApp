package com.tugceaksoy.vitrinapp.data.model.discover

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CollectionList(
    val collection: List<Collections>
):Parcelable
