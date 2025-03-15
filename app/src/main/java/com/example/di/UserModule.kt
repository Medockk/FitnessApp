package com.example.di

import android.content.Context
import com.example.fitnessapp.feature_app.data.data_source.local.HeartRateDataDao
import com.example.fitnessapp.feature_app.data.data_source.local.LastActivityDataDao
import com.example.fitnessapp.feature_app.data.data_source.local.NotificationDataDao
import com.example.fitnessapp.feature_app.data.data_source.local.UserDataDao
import com.example.fitnessapp.feature_app.data.data_source.local.database.HeartRateDaoDatabase
import com.example.fitnessapp.feature_app.data.data_source.local.database.LastActivityDaoDatabase
import com.example.fitnessapp.feature_app.data.data_source.local.database.NotificationDaoDatabase
import com.example.fitnessapp.feature_app.data.data_source.local.database.UserDaoDatabase
import com.example.fitnessapp.feature_app.data.repository.UserDataRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository
import com.example.fitnessapp.feature_app.domain.usecase.User.ChangeNotificationStateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetHeartRateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetLastActivityUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetNotificationsUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetPurposeUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetUserDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetUserStatisticsUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.SetUserImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides @Singleton
    fun createUserDatabase(@ApplicationContext context: Context): UserDaoDatabase {
        return UserDaoDatabase.createDatabase(context)
    }
    @Provides @Singleton
    fun createUserDao(userDaoDatabase: UserDaoDatabase): UserDataDao {
        return userDaoDatabase.userDataDao
    }
    @Provides @Singleton
    fun createNotificationDatabase(@ApplicationContext context: Context): NotificationDaoDatabase {
        return NotificationDaoDatabase.createDatabase(context)
    }
    @Provides @Singleton
    fun createNotificationDao(notificationDaoDatabase: NotificationDaoDatabase): NotificationDataDao {
        return notificationDaoDatabase.notificationDataDao
    }
    @Provides @Singleton
    fun createLastActivityDatabase(@ApplicationContext context: Context): LastActivityDaoDatabase {
        return LastActivityDaoDatabase.createDatabase(context)
    }
    @Provides @Singleton
    fun createLastActivityDao(lastActivityDaoDatabase: LastActivityDaoDatabase): LastActivityDataDao {
        return lastActivityDaoDatabase.lastActivityDataDao
    }
    @Provides @Singleton
    fun createHeartRateDatabase(@ApplicationContext context: Context): HeartRateDaoDatabase {
        return HeartRateDaoDatabase.createDatabase(context)
    }
    @Provides @Singleton
    fun createHeartRateDao(heartRateDaoDatabase: HeartRateDaoDatabase): HeartRateDataDao {
        return heartRateDaoDatabase.heartRateDataDao
    }
    @Provides @Singleton
    fun getUserDataRepository(
        userDataDao: UserDataDao,
        notificationDataDao: NotificationDataDao,
        lastActivityDataDao: LastActivityDataDao,
        heartRateDataDao: HeartRateDataDao
    ): UserDataRepository {
        return UserDataRepositoryImpl(
            userDataDao,
            notificationDataDao,
            lastActivityDataDao,
            heartRateDataDao
        )
    }

    //factories
    @Provides @Singleton
    fun changeNotificationStateUseCase(userDataRepository: UserDataRepository) : ChangeNotificationStateUseCase{
        return ChangeNotificationStateUseCase(userDataRepository)
    }
    @Provides @Singleton
    fun getHeartRateUseCase(userDataRepository: UserDataRepository) : GetHeartRateUseCase{
        return GetHeartRateUseCase(userDataRepository)
    }
    @Provides @Singleton
    fun getLastActivityUseCase(userDataRepository: UserDataRepository) : GetLastActivityUseCase{
        return GetLastActivityUseCase(userDataRepository)
    }
    @Provides @Singleton
    fun getNotificationsUseCase(userDataRepository: UserDataRepository) : GetNotificationsUseCase{
        return GetNotificationsUseCase(userDataRepository)
    }
    @Provides @Singleton
    fun getPurposeUseCase(userDataRepository: UserDataRepository) : GetPurposeUseCase{
        return GetPurposeUseCase(userDataRepository)
    }
    @Provides @Singleton
    fun getUserDataUseCase(userDataRepository: UserDataRepository) : GetUserDataUseCase{
        return GetUserDataUseCase(userDataRepository)
    }
    @Provides @Singleton
    fun setUserImageUseCase(userDataRepository: UserDataRepository) : SetUserImageUseCase{
        return SetUserImageUseCase(userDataRepository)
    }
    @Provides @Singleton
    fun getUserStatisticsUseCase(userDataRepository: UserDataRepository) : GetUserStatisticsUseCase{
        return GetUserStatisticsUseCase(userDataRepository)
    }
}