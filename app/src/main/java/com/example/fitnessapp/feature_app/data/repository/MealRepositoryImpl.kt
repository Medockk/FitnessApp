package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.model.CategoryDataImpl
import com.example.fitnessapp.feature_app.data.model.DietaryRecommendationImpl
import com.example.fitnessapp.feature_app.data.model.MealDetailsImpl
import com.example.fitnessapp.feature_app.data.model.UserMealScheduleImpl
import com.example.fitnessapp.feature_app.data.data_source.local.UserMealScheduleDao
import com.example.fitnessapp.feature_app.data.data_source.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.CategoryData
import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.domain.model.MealDetails
import com.example.fitnessapp.feature_app.domain.model.UserMealSchedule
import com.example.fitnessapp.feature_app.domain.repository.MealRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import java.time.LocalDate

/**
 * Класс для работы с блюдами и их категориями
 * @author Андреев Арсений, 18,02,2025; 12:07
 */
class MealRepositoryImpl(
    private val userMealScheduleDao: UserMealScheduleDao
) : MealRepository {

    override suspend fun getCategories(): List<CategoryData> {

        return client.postgrest["BreakfastCategories"].select().decodeList<CategoryDataImpl>()
    }

    override suspend fun getDietaryRecommendation(): List<DietaryRecommendation> {
        return client.postgrest["DietaryRecommendation"].select()
            .decodeList<DietaryRecommendationImpl>()
    }

    override suspend fun getMealDetails(id: Int): MealDetails {
        return client.postgrest["MealDetails"].select{
            filter { eq("id", id) }
        }.decodeSingle<MealDetailsImpl>()
    }

    override suspend fun getDietaryRecommendationByID(id: Int): DietaryRecommendation {
        return client.postgrest["DietaryRecommendation"].select {
            filter { eq("id", id) }
        }.decodeSingle<DietaryRecommendationImpl>()
    }

    override suspend fun getUserMealSchedule(): List<UserMealSchedule> {

        val userID = getUserID()
        val date = LocalDate.now().toString()

        val data = client.postgrest["UserMealSchedule"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", date)
                }
            }
        }.decodeList<UserMealScheduleImpl>()

        data.forEach {
            userMealScheduleDao.upsertUserMealSchedule(it)
        }
        return userMealScheduleDao.getUserMealSchedule(userID)
    }

    override suspend fun getUserMealScheduleByDate(year: Int, month: Int,day: Int): List<UserMealSchedule> {
        val userID = getUserID()

        val data = client.postgrest["UserMealSchedule"].select {
            filter {
                and {
                    eq("userID", userID)
                    eq("date", "${year}-${month}-$day")
                }
            }
        }.decodeList<UserMealScheduleImpl>()
        userMealScheduleDao.clearUserMealSchedule()

        data.forEach {
            userMealScheduleDao.upsertUserMealSchedule(it)
        }

        return userMealScheduleDao.getUserMealSchedule(userID)
    }

    override suspend fun addMealToUserMealSchedule(category: String, mealID: String) {

        val userID = getUserID()

        client.postgrest["UserMealSchedule"].insert(mapOf(
            "userID" to userID,
            "category" to category,
            "mealID" to mealID
        ))
    }

    private suspend fun getUserID() : String{
        client.auth.awaitInitialization()
        return client.auth.currentUserOrNull()?.id ?: ""
    }
}