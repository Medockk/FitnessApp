package com.example.di

import com.example.fitnessapp.feature_app.data.data_source.local.database.HeartRateDaoDatabase
import com.example.fitnessapp.feature_app.data.data_source.local.database.LastActivityDaoDatabase
import com.example.fitnessapp.feature_app.data.data_source.local.database.NotificationDaoDatabase
import com.example.fitnessapp.feature_app.data.data_source.local.database.UserDaoDatabase
import com.example.fitnessapp.feature_app.data.repository.UserDataRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository
import com.example.fitnessapp.feature_app.domain.usecase.Statistic.GetUserStatisticsUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.ChangeNotificationStateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetHeartRateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetLastActivityUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetNotificationsUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetPurposeUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetUserDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.SetUserImageUseCase
import org.koin.dsl.module

val moduleUser = module {

    single { UserDaoDatabase.createDatabase(get()) }
    single { get<UserDaoDatabase>().userDataDao }
    single { NotificationDaoDatabase.createDatabase(get()) }
    single { get<NotificationDaoDatabase>().notificationDataDao }
    single { LastActivityDaoDatabase.createDatabase(get()) }
    single { get<LastActivityDaoDatabase>().lastActivityDataDao }
    single { HeartRateDaoDatabase.createDatabase(get()) }
    single { get<HeartRateDaoDatabase>().heartRateDataDao }
    single<UserDataRepository> {
        UserDataRepositoryImpl(get(), get(), get(), get())
    }

    factory<GetUserDataUseCase> {
        GetUserDataUseCase(get())
    }

    factory<GetUserStatisticsUseCase> {
        GetUserStatisticsUseCase(get())
    }

    factory<GetNotificationsUseCase> {
        GetNotificationsUseCase(get())
    }

    factory<GetPurposeUseCase> {
        GetPurposeUseCase(get())
    }

    factory<GetLastActivityUseCase> {
        GetLastActivityUseCase(get())
    }

    factory<GetHeartRateUseCase> {
        GetHeartRateUseCase(get())
    }

    factory<SetUserImageUseCase> {
        SetUserImageUseCase(get())
    }
    factory<ChangeNotificationStateUseCase> {
        ChangeNotificationStateUseCase(get())
    }
}