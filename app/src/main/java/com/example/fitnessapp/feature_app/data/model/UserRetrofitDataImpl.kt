package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.Category
import com.example.fitnessapp.feature_app.domain.model.Tags
import com.example.fitnessapp.feature_app.domain.model.UserRetrofitData
import kotlinx.serialization.Serializable

@Serializable
data class UserRetrofitDataImpl(
    override val id: Int,
    override val name: String? = "",
    override val status: String? = "",
    override val photoUrls: List<String> = emptyList(),
    override val tags: List<TagsImpl> = emptyList(),
    override val category: CategoryImpl? = null
) : UserRetrofitData

@Serializable
data class TagsImpl(override val id: Int, override val name: String) : Tags

@Serializable
data class CategoryImpl(override val id: Int, override val name: String) : Category
