package com.metinvandar.yourservice.domain.mappers

import com.metinvandar.yourservice.data.model.Service
import com.metinvandar.yourservice.domain.models.ServiceItem
import com.metinvandar.yourservice.domain.models.ServiceItemType

class ServiceItemMapper : ModelMapper<Service, ServiceItem> {

    override fun mapToUIModel(model: Service): ServiceItem {
        return ServiceItem(
            id = model.serviceId,
            name = model.name,
            type = ServiceItemType.fromValue(model.type),
            imageUrl = model.imageUrl
        )
    }

    override fun mapToUIModelList(modelList: List<Service>): List<ServiceItem> {
        return modelList.map {
            mapToUIModel(it)
        }
    }
}