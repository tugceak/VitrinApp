package com.tugceaksoy.vitrinapp.data.model.discover

import android.os.Parcelable

import kotlinx.parcelize.Parcelize

@Parcelize
data class ShopList(
    val shop:List<Shop>
):Parcelable
