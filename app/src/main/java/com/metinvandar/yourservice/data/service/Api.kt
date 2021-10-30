package com.metinvandar.yourservice.data.service

import retrofit2.http.GET

interface Api {

    @GET("home")
    suspend fun homeData()
}
