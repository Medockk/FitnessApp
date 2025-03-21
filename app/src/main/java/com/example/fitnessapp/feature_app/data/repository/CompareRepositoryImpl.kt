package com.example.fitnessapp.feature_app.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.fitnessapp.feature_app.data.data_source.local.GalleryDataDao
import com.example.fitnessapp.feature_app.data.data_source.local.StatisticDataDao
import com.example.fitnessapp.feature_app.data.data_source.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.data.model.GalleryDataImpl
import com.example.fitnessapp.feature_app.data.model.StatisticDataImpl
import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.GalleryData
import com.example.fitnessapp.feature_app.domain.model.StatisticData
import com.example.fitnessapp.feature_app.domain.repository.CompareRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Month
import java.time.LocalDate
import kotlin.time.Duration

/**
 * Класс для получения галлереи и статистики с сервера, и отправки фотографий на сервер
 * @author Андреев Арсений, 18.02.2025; 12:06
 */
class CompareRepositoryImpl(
    private val galleryDataDao: GalleryDataDao,
    private val statisticDataDao: StatisticDataDao
) : CompareRepository {

    override suspend fun getAllGallery() = flow<NetworkResult<List<GalleryData>>> {

        emit(NetworkResult.Loading())
        val userID = getUserID()
        emit(NetworkResult.Success(galleryDataDao.getGalleryByID(userID)))

        val data = client.postgrest["Gallery"].select {
            filter { eq("userID", userID) }
        }.decodeList<GalleryDataImpl>()
        emit(NetworkResult.Success(data))

        data.forEach {
            galleryDataDao.upsertGalleryData(it)
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }

    override suspend fun getGalleryFromMonthToMonth(
        firstMonth: String,
        secondMonth: String
    ): List<GalleryData> {
        val userID = getUserID()
        val currentTime = LocalDate.now()
        val month1 = Month(firstMonth.toInt())
        val month2 = Month(secondMonth.toInt())
        val date = kotlinx.datetime.LocalDate(currentTime.year, month1, 1)
        val date1 = kotlinx.datetime.LocalDate(
            currentTime.year, month2, if (secondMonth.toInt() == 2) {
                month2.maxLength() - 1
            } else {
                month2.maxLength()
            }
        )

        val data = client.postgrest["Gallery"].select {
            filter {
                eq("userID", userID)
                and {
                    gte("date", date)
                    lte("date", date1)
                }
            }
        }.decodeList<GalleryDataImpl>()

        val dataList = ArrayList<GalleryDataImpl>()
        data.forEach {
            galleryDataDao.upsertGalleryData(it)
            dataList.add(it)
        }
        return dataList
    }

    override suspend fun getStatisticFromMonthToMonth(
        firstMonth: String,
        secondMonth: String
    ) = flow<NetworkResult<List<StatisticData>>> {

        emit(NetworkResult.Loading())
        val userID = getUserID()
        emit(NetworkResult.Success(statisticDataDao.getUserStatisticData(userID)))


        val currentTime = LocalDate.now()
        val month1 = Month(firstMonth.toInt())
        val date1 = kotlinx.datetime.LocalDate(currentTime.year, month1, 1)
        val date2 = kotlinx.datetime.LocalDate(
            currentTime.year,
            month1,
            if (currentTime.year % 4 == 0) month1.maxLength() else month1.maxLength() - 1
        )

        val data = client.postgrest["UserStatistic"].select {
            filter {
                eq("userID", userID)
                and {
                    gte("date", date1)
                    lte("date", date2)
                }
            }
        }.decodeList<StatisticDataImpl>()
        emit(NetworkResult.Success(data))

        statisticDataDao.clearUserStatisticData()
        data.forEach {
            statisticDataDao.upsertStatisticData(it)
        }
    }

    override suspend fun uploadPhoto(photo: ByteArray, category: String) {

        val userID = getUserID()
        val bucket = client.storage.from("gallery/$userID")
        val bitmap = photo.toBitmap()

        bucket.upload(
            "$bitmap.png",
            data = photo
        ) {
            this.upsert = true
        }

        val url = bucket.createSignedUrl("$bitmap.png", Duration.INFINITE)
        client.postgrest["Gallery"].insert(
            mapOf(
                "userID" to userID,
                "photo" to url,
                "category" to category
            )
        )
    }

    private suspend fun getUserID(): String {
        client.auth.awaitInitialization()
        return client.auth.currentUserOrNull()?.id ?: ""
    }

    private fun ByteArray.toBitmap(): Bitmap {
        return BitmapFactory.decodeByteArray(this, 0, this.size)
    }
}