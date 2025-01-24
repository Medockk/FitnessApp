package com.example.di

import com.example.fitnessapp.feature_app.presentation.OnBoard.OnBoardViewModel
import com.example.fitnessapp.feature_app.presentation.SignIn.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleViewModel = module {

    viewModel<OnBoardViewModel>{
        OnBoardViewModel(get())
    }

    viewModel<SignInViewModel>{
        SignInViewModel(get())
    }
}