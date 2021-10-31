package com.metinvandar.yourservice.data.repository

import com.metinvandar.yourservice.data.Resource
import com.metinvandar.yourservice.domain.models.HomeData

interface HomeRepository {

    suspend fun getHomeData(): Resource<HomeData>
}
