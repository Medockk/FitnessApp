package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.data_source.local.HeartRateDataDao
import com.example.fitnessapp.feature_app.data.model.HeartRateImpl
import com.example.fitnessapp.feature_app.data.model.LastActivityDataImpl
import com.example.fitnessapp.feature_app.data.model.NotificationDataImpl
import com.example.fitnessapp.feature_app.data.model.PurposeImpl
import com.example.fitnessapp.feature_app.data.model.UserDataImpl
import com.example.fitnessapp.feature_app.data.model.UserStatisticsImpl
import com.example.fitnessapp.feature_app.data.data_source.local.LastActivityDataDao
import com.example.fitnessapp.feature_app.data.data_source.local.NotificationDataDao
import com.example.fitnessapp.feature_app.data.data_source.local.UserDataDao
import com.example.fitnessapp.feature_app.data.data_source.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.NetworkResult
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration

/**
 * Класс для работы с данными пользователя
 * @author Андреев Арсений, 18.02.2025; 12:08
 */
class UserDataRepositoryImpl(
    private val userDataDao: UserDataDao,
    private val notificationDataDao: NotificationDataDao,
    private val lastActivityDataDao: LastActivityDataDao,
    private val heartRateDataDao: HeartRateDataDao
) : UserDataRepository {

    override suspend fun getUserData(): Flow<NetworkResult<UserData>> {
        val userID = getUserID()

        return flow<NetworkResult<UserData>> {

            emit(NetworkResult.Loading())
            emit(NetworkResult.Success(userDataDao.getUserById(userID)))

            val serverData = client.postgrest["Users"].select { filter { eq("userID", userID) } }
                .decodeSingle<UserDataImpl>()
            emit(NetworkResult.Success(serverData))

            userDataDao.upsertUserData(serverData)
        }.catch {
            emit(NetworkResult.Error(it.localizedMessage))
        }
    }

    override suspend fun getUserStatistics(): List<UserStatistics> {
        val userID = getUserID()

        return client.postgrest["UserStatistics"].select {
            filter { eq("userID", userID) }
        }.decodeList<UserStatisticsImpl>()
    }

    override suspend fun getNotifications() = flow<NetworkResult<List<NotificationData>>> {

        emit(NetworkResult.Loading())
        val userID = getUserID()
        emit(NetworkResult.Success(notificationDataDao.getNotifications(userID)))

        val data = client.postgrest["Notification"].select {
            filter { eq("userID", userID) }
        }.decodeList<NotificationDataImpl>()
        emit(NetworkResult.Success(data))


        data.forEach {
            notificationDataDao.upsertNotificationData(it)
        }

    }

    override suspend fun getPurpose(): Purpose {

        val userID = getUserID()

        return client.postgrest["Users"].select {
            filter { eq("userID", userID) }
        }.decodeSingle<PurposeImpl>()
    }

    override suspend fun getLastActivity() = flow<NetworkResult<List<LastActivityData>>> {

        emit(NetworkResult.Loading())
        val userID = getUserID()
        emit(NetworkResult.Success(lastActivityDataDao.getLastActivityData(userID)))

        val data = client.postgrest["LastActivity"].select {
            filter { eq("userID", userID) }
        }.decodeList<LastActivityDataImpl>()

        emit(NetworkResult.Success(data))

        data.forEach {
            lastActivityDataDao.upsertLastActivityData(it)
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
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

    override suspend fun getHeartRate() = flow<NetworkResult<HeartRate>> {

        emit(NetworkResult.Loading())
        val userID = getUserID()
        emit(NetworkResult.Success(heartRateDataDao.getHeartRateData(userID)))

        val data = client.postgrest["HeartRate"].select {
            filter { eq("userID", userID) }
        }.decodeSingle<HeartRateImpl>()
        emit(NetworkResult.Success(data))

    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }

    private suspend fun getUserID(): String {
        client.auth.awaitInitialization()
        return client.auth.currentUserOrNull()?.id ?: ""
    }
}