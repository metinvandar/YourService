package com.metinvandar.yourservice.domain.mappers

import com.metinvandar.yourservice.data.model.DetailResponse
import com.metinvandar.yourservice.domain.models.ServiceDetailedItem

class DetailMapper : ModelMapper<DetailResponse, ServiceDetailedItem> {

    override fun mapToUIModel(model: DetailResponse): ServiceDetailedItem {
        return ServiceDetailedItem(
            id = model.serviceId,
            name = model.name,
            longName = model.longName,
            proCount = model.proCount,
            completedJobs = model.completedJob,
            averageRating = model.averageRating,
            imageUrl = model.imageUrl
        )
    }

    override fun mapToUIModelList(modelList: List<DetailResponse>): List<ServiceDetailedItem> {
        return modelList.map { mapToUIModel(it) }
    }
}
