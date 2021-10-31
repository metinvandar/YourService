package com.metinvandar.yourservice.data.repository

import com.metinvandar.yourservice.data.Resource
import com.metinvandar.yourservice.domain.models.ServiceDetailedItem

interface DetailRepository {

    suspend fun getServiceDetail(serviceId: Int): Resource<ServiceDetailedItem>
}
