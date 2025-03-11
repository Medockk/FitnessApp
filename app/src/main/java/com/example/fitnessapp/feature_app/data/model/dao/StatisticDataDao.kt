package com.example.fitnessapp.feature_app.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessapp.feature_app.data.model.StatisticDataImpl

@Dao
interface StatisticDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertStatisticData(statisticDataImpl: StatisticDataImpl)

    @Query("SELECT * FROM StatisticDataImpl WHERE userID =:userId")
    fun getUserStatisticData(userId: String) : List<StatisticDataImpl>

    @Query("DELETE FROM StatisticDataImpl")
    fun clearUserStatisticData()
}