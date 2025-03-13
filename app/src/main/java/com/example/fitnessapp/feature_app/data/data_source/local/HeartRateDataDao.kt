package com.example.fitnessapp.feature_app.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessapp.feature_app.data.model.HeartRateImpl

@Dao
interface HeartRateDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertHeartRateData(heartRateImpl: HeartRateImpl)

    @Query("SELECT * FROM HeartRateImpl WHERE userID =:userId")
    fun getHeartRateData(userId: String) : HeartRateImpl

    @Query("DELETE FROM HeartRateImpl")
    fun clearUserData()
}