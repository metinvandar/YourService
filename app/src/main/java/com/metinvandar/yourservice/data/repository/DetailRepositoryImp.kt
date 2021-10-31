package com.metinvandar.yourservice.data.repository

import com.metinvandar.yourservice.data.Resource
import com.metinvandar.yourservice.data.model.DetailResponse
import com.metinvandar.yourservice.data.service.Api
import com.metinvandar.yourservice.domain.mappers.ModelMapper
import com.metinvandar.yourservice.domain.models.ServiceDetailedItem
import retrofit2.HttpException

class DetailRepositoryImp(
    private val api: Api,
    private val mapper: ModelMapper<DetailResponse, ServiceDetailedItem>
) : DetailRepository {

    override suspend fun getServiceDetail(serviceId: Int): Resource<ServiceDetailedItem> {
        return try {
            val apiResponse = api.detail(serviceId)
            Resource.Success(mapper.mapToUIModel(apiResponse))
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    Resource.Error(false, throwable.code(), throwable.response()?.errorBody())
                }
                else -> {
                    Resource.Error(true, null, null)
                }
            }
        }
    }
}
