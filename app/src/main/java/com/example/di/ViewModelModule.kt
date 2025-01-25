package com.example.di

import com.example.fitnessapp.feature_app.presentation.OnBoard.OnBoardViewModel
import com.example.fitnessapp.feature_app.presentation.RegisterPage.RegisterPageViewModel
import com.example.fitnessapp.feature_app.presentation.SignIn.SignInViewModel
import com.example.fitnessapp.feature_app.presentation.SignUp.SignUpViewModel
import com.example.fitnessapp.feature_app.presentation.SuccessRegistration.SuccessRegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleViewModel = module {

    viewModel<OnBoardViewModel>{
        OnBoardViewModel(get())
    }

    viewModel<SignInViewModel>{
        SignInViewModel(get())
    }

    viewModel<SignUpViewModel>{
        SignUpViewModel(get())
    }

    viewModel<RegisterPageViewModel>{
        RegisterPageViewModel(get())
    }

    viewModel<SuccessRegistrationViewModel>{
        SuccessRegistrationViewModel(get())
    }
}