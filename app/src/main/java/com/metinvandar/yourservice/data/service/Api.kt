package com.metinvandar.yourservice.data.service

import com.metinvandar.yourservice.data.model.DetailResponse
import com.metinvandar.yourservice.data.model.HomeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("home")
    suspend fun homeData(): HomeResponse

    @GET("service/{id}")
    suspend fun detail(@Path("id") serviceId: Int): DetailResponse
}
