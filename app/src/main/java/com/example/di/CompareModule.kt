package com.example.di

import android.content.Context
import com.example.fitnessapp.feature_app.data.data_source.local.GalleryDataDao
import com.example.fitnessapp.feature_app.data.data_source.local.StatisticDataDao
import com.example.fitnessapp.feature_app.data.data_source.local.database.GalleryDaoDatabase
import com.example.fitnessapp.feature_app.data.data_source.local.database.StatisticDataDaoDatabase
import com.example.fitnessapp.feature_app.data.repository.CompareRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.CompareRepository
import com.example.fitnessapp.feature_app.domain.usecase.Compare.GetAllGalleryUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Compare.GetGalleryFromMonthToMonthUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Compare.UploadPhotoUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Compare.GetStatisticFromMonthToMonthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CompareModule{

    @Provides @Singleton
    fun getGalleryDao(@ApplicationContext context: Context) : GalleryDataDao{
        val database = GalleryDaoDatabase.createDatabase(context)
        return database.galleryDao
    }
    @Provides @Singleton
    fun getStatisticDao(@ApplicationContext context: Context) : StatisticDataDao{
        val database = StatisticDataDaoDatabase.createDatabase(context)
        return database.statisticDataDao
    }
    @Provides @Singleton
    fun getCompareRepository(galleryDataDao: GalleryDataDao, statisticDataDao: StatisticDataDao) : CompareRepository{
        return CompareRepositoryImpl(galleryDataDao, statisticDataDao)
    }

    //FACTORIES
    @Provides @Singleton
    fun getAllGalleryUseCase(compareRepository: CompareRepository) : GetAllGalleryUseCase{
        return GetAllGalleryUseCase(compareRepository)
    }
    @Provides @Singleton
    fun getGalleryFromMonthToMonthUseCase(compareRepository: CompareRepository) : GetGalleryFromMonthToMonthUseCase{
        return GetGalleryFromMonthToMonthUseCase(compareRepository)
    }
    @Provides @Singleton
    fun uploadPhotoUseCase(compareRepository: CompareRepository) : UploadPhotoUseCase{
        return UploadPhotoUseCase(compareRepository)
    }
    @Provides @Singleton
    fun getStatisticFromMonthToMonthUseCase(compareRepository: CompareRepository) : GetStatisticFromMonthToMonthUseCase {
        return GetStatisticFromMonthToMonthUseCase(compareRepository)
    }
}