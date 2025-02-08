package com.example.di

import com.example.fitnessapp.feature_app.data.repository.CompareRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.CompareRepository
import com.example.fitnessapp.feature_app.domain.usecase.Compare.GetAllGalleryUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Compare.GetGalleryFromMonthToMonthUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Compare.UploadPhotoUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Statistic.GetStatisticFromMonthToMonthUseCase
import org.koin.dsl.module

val moduleCompare = module {

    single<CompareRepository> {
        CompareRepositoryImpl()
    }

    factory<GetAllGalleryUseCase> {
        GetAllGalleryUseCase(get())
    }

    factory<GetGalleryFromMonthToMonthUseCase> {
        GetGalleryFromMonthToMonthUseCase(get())
    }

    factory<UploadPhotoUseCase> {
        UploadPhotoUseCase(get())
    }

    factory<GetStatisticFromMonthToMonthUseCase> {
        GetStatisticFromMonthToMonthUseCase(get())
    }
}