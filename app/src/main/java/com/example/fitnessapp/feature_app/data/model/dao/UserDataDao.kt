package com.example.fitnessapp.feature_app.data.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessapp.feature_app.data.model.UserDataRepoImpl

@Dao
interface UserDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertUserData(userDataRepoImpl: UserDataRepoImpl)

    @Query("SELECT * FROM UserDataRepoImpl WHERE userID=:userID")
    fun getUserById(userID: String) : UserDataRepoImpl

    @Delete
    fun deleteUser(userDataRepoImpl: UserDataRepoImpl)
}