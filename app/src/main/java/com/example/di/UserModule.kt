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
    fun getUserDataDao(@ApplicationContext context: Context): UserDataDao {
        val database = UserDaoDatabase.createDatabase(context)
        return database.userDataDao
    }
    @Provides @Singleton
    fun getNotificationDataDao(@ApplicationContext context: Context): NotificationDataDao {
        val database = NotificationDaoDatabase.createDatabase(context)
        return database.notificationDataDao
    }
    @Provides @Singleton
    fun getLastActivityDao(@ApplicationContext context: Context): LastActivityDataDao {
        val database = LastActivityDaoDatabase.createDatabase(context)
        return database.lastActivityDataDao
    }
    @Provides @Singleton
    fun getHeartRateDao(@ApplicationContext context: Context): HeartRateDataDao {
        val database = HeartRateDaoDatabase.createDatabase(context)
        return database.heartRateDataDao
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