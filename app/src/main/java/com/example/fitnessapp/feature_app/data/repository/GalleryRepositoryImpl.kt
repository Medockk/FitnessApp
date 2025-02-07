package com.example.fitnessapp.feature_app.data.repository

import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.GalleryData
import com.example.fitnessapp.feature_app.domain.repository.GalleryRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlin.time.Duration

class GalleryRepositoryImpl : GalleryRepository {

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

        return client.postgrest["Gallery"].select {
            filter {
                eq("userID", userID)
                eq("date", "2025-$firstMonth-07")
            }
        }.decodeList<GalleryData>()
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