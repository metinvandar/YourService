package com.metinvandar.yourservice.domain.mappers

interface ModelMapper<T,U> {

    fun mapToUIModel(model: T): U

    fun mapToUIModelList(modelList: List<T>): List<U>

}
