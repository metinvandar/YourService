package com.metinvandar.yourservice.domain.mappers

import com.metinvandar.yourservice.data.model.Post
import com.metinvandar.yourservice.domain.models.PostItem

class PostItemMapper : ModelMapper<Post, PostItem> {

    override fun mapToUIModel(model: Post): PostItem {
        return PostItem(
            title =  model.title,
            category = model.category,
            imageUrl = model.imageUrl,
            link = model.link
        )
    }

    override fun mapToUIModelList(modelList: List<Post>): List<PostItem> {
        return modelList.map { mapToUIModel(it) }
    }
}