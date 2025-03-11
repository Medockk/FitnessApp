package com.example.fitnessapp.feature_app.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessapp.feature_app.data.model.UserDataImpl

@Dao
interface UserDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertUserData(userDataRepoImpl: UserDataImpl)

    @Query("SELECT * FROM UserDataImpl WHERE userID=:userID")
    fun getUserById(userID: String) : UserDataImpl

    @Delete
    fun deleteUser(userDataRepoImpl: UserDataImpl)
}