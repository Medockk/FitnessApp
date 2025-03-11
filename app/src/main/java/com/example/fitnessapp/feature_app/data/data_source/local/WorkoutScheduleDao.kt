package com.example.fitnessapp.feature_app.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessapp.feature_app.data.model.WorkoutScheduleImpl

@Dao
interface WorkoutScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertWorkoutSchedule(workoutScheduleImpl: WorkoutScheduleImpl)

    @Query("SELECT * FROM WorkoutScheduleImpl WHERE userID =:userId")
    fun getWorkoutSchedule(userId: String) : List<WorkoutScheduleImpl>

    @Query("DELETE FROM WorkoutScheduleImpl")
    fun clearWorkoutSchedule()
}