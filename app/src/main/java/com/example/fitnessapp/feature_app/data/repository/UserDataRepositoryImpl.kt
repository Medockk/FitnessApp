package com.example.fitnessapp.feature_app.data.repository

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
import kotlin.time.Duration

class UserDataRepositoryImpl : UserDataRepository {

    override suspend fun getUserData(): UserData {

        val userID = client.auth.currentUserOrNull()?.id?:""
        return client.postgrest["Users"].select {
            filter {
                eq("userID", userID)
            }
        }.decodeSingle<UserData>()
    }

    override suspend fun updateUserData(userData: UserData) {

        val userID = client.auth.currentUserOrNull()?.id?:""

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
        val userID = client.auth.currentUserOrNull()?.id?:""
        return client.postgrest["UserStatistics"].select {
            filter { eq("userID", userID) }
        }.decodeList<UserStatistics>()
    }

    override suspend fun getNotifications(): List<NotificationData> {

        val userID = client.auth.currentUserOrNull()?.id?:""

        return client.postgrest["Notification"].select {
            filter { eq("userID", userID) }
        }.decodeList<NotificationData>()
    }

    override suspend fun getPurpose(): Purpose {

        val userID = client.auth.currentUserOrNull()?.id?:""

        return client.postgrest["Purpose"].select {
            filter { eq("userID", userID) }
        }.decodeSingle<Purpose>()
    }

    override suspend fun getLastActivity(): List<LastActivityData> {

        val userID = client.auth.currentUserOrNull()?.id?:""

        return client.postgrest["LastActivity"].select {
            filter { eq("userID", userID) }
        }.decodeList<LastActivityData>()
    }

    override suspend fun getUserImage(): String {

        val userID = client.auth.currentUserOrNull()?.id?:""
        val bucket = client.storage.from("avatars")
        val url = bucket.createSignedUrl("$userID.png", Duration.INFINITE)

        return url
    }

    override suspend fun setUserImage(byteArray: ByteArray) {

        val userID = client.auth.currentUserOrNull()?.id?:""

        val signedUploadUrl = client.storage.from("avatars").createSignedUploadUrl("$userID.png", upsert = true)

        client.storage.from("avatars").uploadToSignedUrl(
            path = "$userID.png",
            token = signedUploadUrl.token,
            data = byteArray
        ){
            upsert = true
        }
    }

    override suspend fun getHeartRate(): HeartRate {

        val userID = client.auth.currentUserOrNull()?.id?:""

        return client.postgrest["HeartRate"].select {
            filter { eq("userID", userID) }
        }.decodeSingle<HeartRate>()
    }
}