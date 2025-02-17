package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.CategoryData
import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.domain.model.MealDetails
import com.example.fitnessapp.feature_app.domain.model.UserMealSchedule
import com.example.fitnessapp.feature_app.domain.repository.MealRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest

class MealRepositoryImpl : MealRepository {

    override suspend fun getCategories(): List<CategoryData> {

        return client.postgrest["BreakfastCategories"].select().decodeList<CategoryData>()
    }

    override suspend fun getDietaryRecommendation(): List<DietaryRecommendation> {
        return client.postgrest["DietaryRecommendation"].select()
            .decodeList<DietaryRecommendation>()
    }

    override suspend fun getMealDetails(id: Int): MealDetails {
        return client.postgrest["MealDetails"].select{
            filter { eq("id", id) }
        }.decodeSingle<MealDetails>()
    }

    override suspend fun getDietaryRecommendationByID(id: Int): DietaryRecommendation {
        return client.postgrest["DietaryRecommendation"].select {
            filter { eq("id", id) }
        }.decodeSingle<DietaryRecommendation>()
    }

    override suspend fun getUserMealSchedule(): List<UserMealSchedule> {

        val userID = getUserID()

        return client.postgrest["UserMealSchedule"].select {
            filter { eq("userID", userID) }
        }.decodeList<UserMealSchedule>()
    }

    override suspend fun addMealToUserMealSchedule(meal: DietaryRecommendation) {

        val userID = getUserID()

        client.postgrest["UserMealSchedule"].insert(mapOf(
            "userID" to userID,
            "category" to meal.category,
            "mealID" to meal.id.toString()
        ))
    }

    private suspend fun getUserID() : String{
        client.auth.awaitInitialization()
        return client.auth.currentUserOrNull()?.id ?: ""
    }
}