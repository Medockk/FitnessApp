package com.example.fitnessapp.feature_app.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessapp.feature_app.data.model.NotificationDataImpl

@Dao
interface NotificationDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertNotificationData(notificationDataImpl: NotificationDataImpl)

    @Query("SELECT * FROM NotificationDataImpl WHERE userID =:userId")
    fun getNotifications(userId: String) : List<NotificationDataImpl>
}