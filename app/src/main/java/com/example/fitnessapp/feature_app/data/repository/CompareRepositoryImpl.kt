package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.GalleryData
import com.example.fitnessapp.feature_app.domain.model.StatisticData
import com.example.fitnessapp.feature_app.domain.repository.CompareRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.datetime.Month
import java.time.LocalDate
import kotlin.time.Duration

class CompareRepositoryImpl : CompareRepository {

    override suspend fun getAllGallery(): List<GalleryData> {
        val userID = getUserID()

        return client.postgrest["Gallery"].select {
            filter { eq("userID", userID) }
        }.decodeList<GalleryData>()
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
        val date1 = kotlinx.datetime.LocalDate(currentTime.year, month2, month2.maxLength())

        return client.postgrest["Gallery"].select {
            filter {
                eq("userID", userID)
                and {
                    gte("date", date)
                    lte("date", date1)
                }
            }
        }.decodeList<GalleryData>()
    }

    override suspend fun getStatisticFromMonthToMonth(
        firstMonth: String,
        secondMonth: String
    ): List<StatisticData> {
        val userID = getUserID()
        val currentTime = LocalDate.now()

        val month1 = Month(firstMonth.toInt())

        val date1 = kotlinx.datetime.LocalDate(currentTime.year, month1, 1)
        val date2 = kotlinx.datetime.LocalDate(currentTime.year, month1, if (currentTime.year % 4 == 0) month1.maxLength() else month1.maxLength()-1)

        return client.postgrest["UserStatistic"].select {
            filter {
                eq("userID", userID)
                and {
                    gte("date", date1)
                    lte("date", date2)
                }
            }
        }.decodeList<StatisticData>()
    }

    override suspend fun uploadPhoto(photo: ByteArray, category: String) {

        val userID = getUserID()
        val bucket = client.storage.from("gallery")

        val signedUploadUrl = bucket.createSignedUploadUrl("$userID.png")
        client.storage.from("gallery").uploadToSignedUrl(
            path = "$userID.png", token = signedUploadUrl.token,
            data = photo
        )

        val url = bucket.createSignedUrl("$userID.png", Duration.INFINITE)
        client.postgrest["Gallery"].insert(
            mapOf(
                "userID" to userID,
                "photo" to url,
                "category" to category
            )
        )

    }

    private fun getUserID(): String {
        return client.auth.currentUserOrNull()?.id ?: ""
    }
}