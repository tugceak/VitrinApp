package com.tugceaksoy.vitrinapp.data.repository

import com.tugceaksoy.vitrinapp.data.model.Output
import com.tugceaksoy.vitrinapp.data.model.discover.DiscoverModel
import com.tugceaksoy.vitrinapp.data.remote.ServiceApi
import com.tugceaksoy.vitrinapp.utils.RetrofitUtils
import retrofit2.Retrofit
import javax.inject.Inject

class VitrinRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit,
    private val serviceApi: ServiceApi
) : VitrinRepository {
    override suspend fun getDiscover(): Output<DiscoverModel> {
        val response = serviceApi.getDiscover()
        return RetrofitUtils.getResponse(retrofit, { response }, "Error")
    }
}