package com.example.fitnessapp.feature_app.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessapp.feature_app.data.model.UserDataImpl

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun upsertUserData(userDataImpl: UserDataImpl)

    @Query("SELECT * FROM UserDataImpl WHERE id=:id")
    fun getUserData(id: String): UserDataImpl?

    @Delete
    fun deleteUserData(userDataImpl: UserDataImpl)
}