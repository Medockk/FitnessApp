package com.example.di

import com.example.fitnessapp.feature_app.data.repository.GalleryRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.GalleryRepository
import com.example.fitnessapp.feature_app.domain.usecase.Gallery.GetAllGalleryUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Gallery.GetGalleryFromMonthToMonthUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Gallery.UploadPhotoUseCase
import org.koin.dsl.module

val moduleGallery = module {

    single<GalleryRepository> {
        GalleryRepositoryImpl()
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
}