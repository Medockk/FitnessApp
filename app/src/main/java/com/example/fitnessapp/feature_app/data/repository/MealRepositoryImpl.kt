package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.CategoryData
import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.domain.model.MealDetails
import com.example.fitnessapp.feature_app.domain.model.UserMealSchedule
import com.example.fitnessapp.feature_app.domain.repository.MealRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlin.random.Random

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

        val userID = client.auth.currentUserOrNull()?.id?:""

        return client.postgrest["UserMealSchedule"].select {
            filter { eq("userID", userID) }
        }.decodeList<UserMealSchedule>()
    }

    override suspend fun addMealToUserMealSchedule(meal: DietaryRecommendation) {

        val userID = client.auth.currentUserOrNull()?.id?:""
        val hour = Random.nextInt(0,24).toString()
        val minute = Random.nextInt(0, 60).toString()

        val userMealSchedule =
            UserMealSchedule(0, userID, UserMealSchedule.categoryBreakfast,
                meal.id,
                time = if (hour.length == 1 && minute.length == 1){
                    "0$hour:0$minute"
                }else if (hour.length == 1){
                    "0$hour:$minute"
                }else{
                    "$hour:$minute"
                }
            )
        client.postgrest["UserMealSchedule"].insert(userMealSchedule)
    }
}