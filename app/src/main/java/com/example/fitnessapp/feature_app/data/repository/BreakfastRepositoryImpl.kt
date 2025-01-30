package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.CategoryData
import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.domain.repository.BreakfastRepository
import io.github.jan.supabase.postgrest.postgrest

class BreakfastRepositoryImpl : BreakfastRepository {

    override suspend fun getCategories(): List<CategoryData> {

        return client.postgrest["BreakfastCategories"].select().decodeList<CategoryData>()
    }

    override suspend fun getDietaryRecommendation(): List<DietaryRecommendation> {
        return client.postgrest["DietaryRecommendation"].select()
            .decodeList<DietaryRecommendation>()
    }
}