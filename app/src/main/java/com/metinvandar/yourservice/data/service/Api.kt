package com.metinvandar.yourservice.data.service

import com.metinvandar.yourservice.data.model.HomeResponse
import retrofit2.http.GET

interface Api {

    @GET("home")
    suspend fun homeData(): HomeResponse
}
