package com.example.fitnessapp.feature_app.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessapp.feature_app.data.model.NotificationDataImpl
import com.example.fitnessapp.feature_app.domain.model.NotificationData

@Dao
interface NotificationDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertNotificationData(notificationDataImpl: NotificationDataImpl)

    @Query("SELECT * FROM NotificationDataImpl WHERE userID =:userId")
    fun getNotifications(userId: String) : List<NotificationDataImpl>
}