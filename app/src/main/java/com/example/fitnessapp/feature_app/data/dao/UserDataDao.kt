package com.example.fitnessapp.feature_app.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnessapp.feature_app.domain.dao.UserDao
import com.example.fitnessapp.feature_app.domain.model.UserDataEntity
import kotlinx.coroutines.flow.Flow

@Database([UserDataEntity::class], version = 1)
abstract class UserDataDao : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        fun createDataBase(context: Context): UserDataDao {
            return Room.databaseBuilder(context, UserDataDao::class.java, "userData.db").build()
        }
    }
}

class UserDataDaoImpl(
    private val userDao: UserDao
) {
    suspend fun upsertUserData(userDataEntity: UserDataEntity) {
        userDao.upsertUserData(userDataEntity)
    }

    fun getUserData(email: String): Flow<List<UserDataEntity>> {
        return userDao.getUserData(email)
    }

    fun deleteUserData(userDataEntity: UserDataEntity) {
        userDao.deleteUserData(userDataEntity)
    }


}