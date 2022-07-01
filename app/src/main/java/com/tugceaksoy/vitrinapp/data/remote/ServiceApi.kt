package com.tugceaksoy.vitrinapp.data.remote

import com.tugceaksoy.vitrinapp.data.model.discover.DiscoverModel
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {

    //Get All Discover Results
    @GET("discover")
    suspend fun getDiscover(): Response<DiscoverModel>


}