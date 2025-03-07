package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.dao.UserDao
import com.example.fitnessapp.feature_app.data.model.UserDataImpl
import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.HeartRate
import com.example.fitnessapp.feature_app.domain.model.LastActivityData
import com.example.fitnessapp.feature_app.domain.model.NotificationData
import com.example.fitnessapp.feature_app.domain.model.Purpose
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.model.UserStatistics
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration

/**
 * Класс для работы с данными пользователя
 * @author Андреев Арсений, 18.02.2025; 12:08
 */
class UserDataRepositoryImpl(
    private val userDao: UserDao
) : UserDataRepository {

    override suspend fun getUserData(): UserDataImpl {

        val userID = getUserID()

        val serverData = client.postgrest["Users"].select { filter { eq("userID", userID) } }
            .decodeSingle<UserDataImpl>()
        userDao.upsertUserData(serverData)
        return serverData
    }

    override suspend fun upsertUserData(userDataImpl: UserDataImpl) {
        val userID = getUserID()
        val data = userDataImpl.copy(userID = userID)

        userDao.upsertUserData(data)
    }

    override suspend fun getUserDataDao(userID: String): Flow<UserDataImpl> = flow {
        userDao.getUserData(userID)
    }

    override suspend fun updateUserData(userData: UserData) {

        val userID = getUserID()

        client.postgrest["Users"].update({
            set("fio", userData.fio)
            set("phone", userData.phone)
            set("gender", userData.gender)
            set("birthdayData", userData.birthdayData)
            set("weight", userData.weight)
            set("height", userData.height)
        }) {
            filter {
                eq("userID", userID)
            }
        }
    }

    override suspend fun getUserStatistics(): List<UserStatistics> {
        val userID = getUserID()

        return client.postgrest["UserStatistics"].select {
            filter { eq("userID", userID) }
        }.decodeList<UserStatistics>()
    }

    override suspend fun getNotifications(): List<NotificationData> {

        val userID = getUserID()

        return client.postgrest["Notification"].select {
            filter { eq("userID", userID) }
        }.decodeList<NotificationData>()
    }

    override suspend fun getPurpose(): Purpose {

        val userID = getUserID()

        return client.postgrest["Users"].select {
            filter { eq("userID", userID) }
        }.decodeSingle<Purpose>()
    }

    override suspend fun getLastActivity(): List<LastActivityData> {

        val userID = getUserID()

        return client.postgrest["LastActivity"].select {
            filter { eq("userID", userID) }
        }.decodeList<LastActivityData>()
    }

    override suspend fun getUserImage(): String {

        val userID = getUserID()
        val bucket = client.storage.from("avatars")
        val url = bucket.createSignedUrl("$userID.png", Duration.INFINITE)

        return url
    }

    override suspend fun setUserImage(byteArray: ByteArray) {

        val userID = getUserID()

        client.storage.from("avatars").upload(
            path = "$userID.png",
            data = byteArray
        ) {
            upsert = true
        }

        val url = client.storage.from("avatars").createSignedUrl("$userID.png", Duration.INFINITE)
        client.postgrest["Users"].update(mapOf("image" to url)) { filter { eq("userID", userID) } }
    }

    override suspend fun changeNotificationState(value: Boolean) {
        val userID = getUserID()

        client.postgrest["Users"].update(mapOf("notification" to value)) {
            filter { eq("userID", userID) }
        }
    }

    override suspend fun getHeartRate(): HeartRate {

        val userID = getUserID()

        return client.postgrest["HeartRate"].select {
            filter { eq("userID", userID) }
        }.decodeSingle<HeartRate>()
    }

    private suspend fun getUserID(): String {
        client.auth.awaitInitialization()
        return client.auth.currentUserOrNull()?.id ?: ""
    }
}