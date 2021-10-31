package com.metinvandar.yourservice.di

import com.metinvandar.yourservice.data.model.DetailResponse
import com.metinvandar.yourservice.data.model.Post
import com.metinvandar.yourservice.data.model.Service
import com.metinvandar.yourservice.data.repository.DetailRepository
import com.metinvandar.yourservice.data.repository.DetailRepositoryImp
import com.metinvandar.yourservice.data.repository.HomeRepository
import com.metinvandar.yourservice.data.repository.HomeRepositoryImp
import com.metinvandar.yourservice.data.service.Api
import com.metinvandar.yourservice.domain.mappers.*
import com.metinvandar.yourservice.domain.models.PostItem
import com.metinvandar.yourservice.domain.models.ServiceDetailedItem
import com.metinvandar.yourservice.domain.models.ServiceItem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideHomeRepository(
        api: Api,
        serviceItemMapper: ModelMapper<Service, ServiceItem>,
        postItemMapper: ModelMapper<Post, PostItem>
    ): HomeRepository {
        return HomeRepositoryImp(api, serviceItemMapper, postItemMapper)
    }

    @Provides
    @ViewModelScoped
    fun provideServiceMapper(): ModelMapper<Service, ServiceItem> {
        return ServiceItemMapper()
    }

    @Provides
    @ViewModelScoped
    fun providePostItemMapper(): ModelMapper<Post, PostItem> {
        return PostItemMapper()
    }

    @Provides
    @ViewModelScoped
    fun provideDetailMapper(): ModelMapper<DetailResponse, ServiceDetailedItem> {
        return DetailMapper()
    }

    @Provides
    @ViewModelScoped
    fun provideDetailRepository(
        api: Api,
        serviceItemMapper: ModelMapper<DetailResponse, ServiceDetailedItem>
    ): DetailRepository {
        return DetailRepositoryImp(api, serviceItemMapper)
    }

}
