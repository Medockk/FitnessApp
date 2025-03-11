package com.example.fitnessapp.feature_app.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessapp.feature_app.data.model.LastActivityDataImpl

@Dao
interface LastActivityDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertLastActivityData(lastActivityDataImpl: LastActivityDataImpl)

    @Query("SELECT * FROM LastActivityDataImpl WHERE userID =:userId")
    fun getLastActivityData(userId: String) : List<LastActivityDataImpl>
}