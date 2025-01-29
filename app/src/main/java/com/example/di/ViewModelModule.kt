package com.example.di

import com.example.fitnessapp.feature_app.presentation.ActivityTracker.ActivityTrackerViewModel
import com.example.fitnessapp.feature_app.presentation.CreateProfile.CreateProfileViewModel
import com.example.fitnessapp.feature_app.presentation.Home.HomeViewModel
import com.example.fitnessapp.feature_app.presentation.Notification.NotificationViewModel
import com.example.fitnessapp.feature_app.presentation.OnBoard.OnBoardViewModel
import com.example.fitnessapp.feature_app.presentation.Profile.ProfileViewModel
import com.example.fitnessapp.feature_app.presentation.RegisterPage.RegisterPageViewModel
import com.example.fitnessapp.feature_app.presentation.SignIn.SignInViewModel
import com.example.fitnessapp.feature_app.presentation.SignUp.SignUpViewModel
import com.example.fitnessapp.feature_app.presentation.SuccessRegistration.SuccessRegistrationViewModel
import com.example.fitnessapp.feature_app.presentation.WorkoutTracker.WorkoutViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleViewModel = module {

    viewModel<OnBoardViewModel>{
        OnBoardViewModel(get())
    }

    viewModel<SignInViewModel>{
        SignInViewModel(get(), get())
    }

    viewModel<SignUpViewModel>{
        SignUpViewModel(get(), get())
    }

    viewModel<RegisterPageViewModel>{
        RegisterPageViewModel(get())
    }

    viewModel<SuccessRegistrationViewModel>{
        SuccessRegistrationViewModel(get())
    }

    viewModel<CreateProfileViewModel>{
        CreateProfileViewModel(get())
    }

    viewModel<HomeViewModel>{
        HomeViewModel(
            get(),
            get(),
            get()
        )
    }

    viewModel<NotificationViewModel>{
        NotificationViewModel(get())
    }

    viewModel<ProfileViewModel>{
        ProfileViewModel(
            get(), get()
        )
    }

    viewModel<ActivityTrackerViewModel>{
        ActivityTrackerViewModel(
            get()
        )
    }

    viewModel<WorkoutViewModel>{
        WorkoutViewModel(
            get(), get(), get(),
            get()
        )
    }
}