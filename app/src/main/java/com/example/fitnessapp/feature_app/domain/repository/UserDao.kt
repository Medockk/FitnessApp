package com.example.fitnessapp.feature_app.domain.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessapp.feature_app.domain.model.UserDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertUserData(userDataEntity: UserDataEntity)

    @Query("SELECT * FROM UserDataEntity WHERE id=:id")
    fun getUserData(id: Int) : Flow<UserDataEntity>

    @Delete
    fun deleteUserData(userDataEntity: UserDataEntity)
}