package com.tugceaksoy.vitrinapp.data.repository


import com.tugceaksoy.vitrinapp.data.model.Output
import com.tugceaksoy.vitrinapp.data.model.discover.DiscoverModel


interface VitrinRepository {
    suspend fun getDiscover(): Output<DiscoverModel>
}