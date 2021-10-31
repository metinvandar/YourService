package com.metinvandar.yourservice.data.repository

import com.metinvandar.yourservice.data.Resource
import com.metinvandar.yourservice.data.model.HomeResponse
import com.metinvandar.yourservice.data.model.Post
import com.metinvandar.yourservice.data.model.Service
import com.metinvandar.yourservice.data.service.Api
import com.metinvandar.yourservice.domain.mappers.ModelMapper
import com.metinvandar.yourservice.domain.models.HomeData
import com.metinvandar.yourservice.domain.models.PostItem
import com.metinvandar.yourservice.domain.models.ServiceItem
import retrofit2.HttpException

class HomeRepositoryImp(
    private val api: Api,
    private val serviceItemMapper: ModelMapper<Service, ServiceItem>,
    private val postItemMapper: ModelMapper<Post, PostItem>
) : HomeRepository {

    override suspend fun getHomeData(): Resource<HomeData>{
        return try {
            val apiResponse = api.homeData()
            Resource.Success(
                HomeData(
                    services = serviceItemMapper.mapToUIModelList(apiResponse.allServices),
                    posts =  postItemMapper.mapToUIModelList(apiResponse.posts),
                    popularServices = serviceItemMapper.mapToUIModelList(apiResponse.popular)
                )
            )
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