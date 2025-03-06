package com.example.fitnessapp.feature_app.data.repository

import android.util.Log
import com.example.fitnessapp.feature_app.data.network.SupabaseClient.client
import com.example.fitnessapp.feature_app.domain.model.StatisticData
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.repository.AuthRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import java.time.LocalDate
import kotlin.random.Random

/**
 * Class for authentication, log in, and log in with google
 * @author Andreev Arsenij
 * 18.02.2025; 12:01
 */
class AuthRepositoryImpl : AuthRepository {

    override suspend fun signIn(mail: String, pass: String) {
        client.auth.signInWith(Email) {
            email = mail
            password = pass
        }
    }

    override suspend fun signInWithGoogle(): Boolean {

        Log.e("sign in google", "use case")
        val userID = getUserID()
        try {
            client.postgrest["Users"].select {
                filter { eq("userID", userID) }
            }.decodeSingle<UserData>()
            return true
        } catch (_: Exception) {
            Log.i("catch", userID)
            return false
        }

    }

    override suspend fun signUp(mail: String, pass: String, userData: UserData) {
        client.auth.signUpWith(Email) {
            email = mail
            password = pass
        }

        val userID = getUserID()
        client.postgrest["Users"].insert(
            mapOf(
                "userID" to userID,
                "fio" to userData.fio,
                "phone" to userData.phone
            )
        )

        setSomeUserData()
    }

    override suspend fun signUpWithGoogle(): Boolean {

        try {
            val userID = getUserID()
            client.postgrest["Users"].select {
                filter { eq("userID", userID) }
            }
            return true
        } catch (_: Exception) {
            return false
        }
    }

    override suspend fun createProfile(
        gender: String,
        birthdayData: String,
        weight: String,
        height: String
    ) {
        val userID = getUserID()
        client.postgrest["Users"].update(
            mapOf(
                "gender" to gender,
                "birthdayData" to birthdayData,
                "weight" to weight,
                "height" to height
            )
        ) {
            filter {
                eq("userID", userID)
            }
        }
    }

    override suspend fun selectPurpose(purpose: String) {

        val userID = getUserID()
        client.postgrest["Purpose"].insert(
            mapOf(
                "userID" to userID,
                "purpose" to purpose
            )
        )
    }

    private fun getRandomHeartRate() : String{

        var heartRate = ""

        for (i in 0..11){
            heartRate += Random.nextInt(1, 6).toString() + " "
        }

        return heartRate
    }

    private suspend fun setSomeUserData() {

        val userID = getUserID()
        val heartRate = getRandomHeartRate()

        client.postgrest["HeartRate"].insert(
            mapOf(
                "userID" to userID,
                "heartRateList" to heartRate
            )
        )

        client.postgrest["Notification"].insert(mapOf(
            "userID" to userID,
            "title" to "TITLE 1",
            "image" to "https://static.mk.ru/upload/entities/2021/08/09/14/articles/facebookPicture/82/7f/7d/7b/8aeb5a415ffef24a5e5ad91cbdcc780f.jpg"
        ))
        client.postgrest["Notification"].insert(mapOf(
            "userID" to userID,
            "title" to "TITLE 2",
            "image" to "https://otvet.imgsmail.ru/download/192595754_616730d75f25be81a06363d434675a14.jpg"
        ))
        client.postgrest["Notification"].insert(mapOf(
            "userID" to userID,
            "title" to "TITLE 3",
            "image" to "https://avatars.mds.yandex.net/i?id=235372336de4205955ef50002f605c58eff716ab-10848343-images-thumbs&n=13"
        ))

        client.postgrest["UserWorkoutData"].insert(mapOf(
            "userID" to userID,
            "title" to "TITLE 1",
            "image" to "https://avatars.mds.yandex.net/i?id=b423ba05961f480723da2984814eafa3a5f7b098-10895071-images-thumbs&n=13"
        ))
        client.postgrest["UserWorkoutData"].insert(mapOf(
            "userID" to userID,
            "title" to "TITLE 2",
            "image" to "https://avatars.mds.yandex.net/i?id=f5dcdb8d72912dcd17fd2928355c34f36ab5468b-5987336-images-thumbs&n=13",
        ))
        client.postgrest["UserWorkoutData"].insert(mapOf(
            "userID" to userID,
            "title" to "TITLE 3",
            "image" to "https://avatars.mds.yandex.net/i?id=afa88c37e00bdefb2e1cc8358642ae7ea6b43207-5551844-images-thumbs&n=13",
        ))

        client.postgrest["UserStatistics"].insert(mapOf(
            "userID" to userID,
            "title" to "TITLE 1",
            "description" to "8 ч. 20 мин."
        ))
        client.postgrest["UserStatistics"].insert(mapOf(
            "userID" to userID,
            "title" to "TITLE 2",
            "description" to "4 л."
        ))

        val weight = StatisticData(LocalDate.now().toString(), userID, StatisticData.loseWeight, getRandomStatistic())
        val height = StatisticData(LocalDate.now().toString(), userID, StatisticData.increasedGrowth, getRandomStatistic())
        val muscleMass = StatisticData(LocalDate.now().toString(), userID, StatisticData.increaseInMuscleMass, getRandomStatistic())
        val press = StatisticData(LocalDate.now().toString(), userID, StatisticData.press, getRandomStatistic())
        client.postgrest["UserStatistic"].insert(listOf(weight, height, muscleMass, press))
    }

    private suspend fun getUserID(): String {
        client.auth.awaitInitialization()
        return client.auth.currentUserOrNull()?.id ?: ""
    }

    private fun getRandomStatistic() : String{
        return Random.nextInt(10, 70).toString()
    }
}