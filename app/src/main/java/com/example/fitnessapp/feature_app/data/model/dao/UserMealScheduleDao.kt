package com.example.fitnessapp.feature_app.data.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessapp.feature_app.data.model.UserMealScheduleImpl

@Dao
interface UserMealScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertUserMealSchedule(userMealScheduleImpl: UserMealScheduleImpl)

    @Query("SELECT * FROM UserMealScheduleImpl WHERE userID =:userId")
    fun getUserMealSchedule(userId: String) : List<UserMealScheduleImpl>

    @Query("DELETE FROM UserMealScheduleImpl")
    fun clearUserMealSchedule()
}