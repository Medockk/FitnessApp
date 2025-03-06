package com.example.di

import com.example.fitnessapp.feature_app.presentation.ActivityTracker.ActivityTrackerViewModel
import com.example.fitnessapp.feature_app.presentation.AddAlarm.AddAlarmViewModel
import com.example.fitnessapp.feature_app.presentation.AddWorkoutSchedule.AddWorkoutScheduleViewModel
import com.example.fitnessapp.feature_app.presentation.CategoryBreakfast.CategoryBreakfastViewModel
import com.example.fitnessapp.feature_app.presentation.CompareResult.CompareResultViewModel
import com.example.fitnessapp.feature_app.presentation.Comparison.ComparisonViewModel
import com.example.fitnessapp.feature_app.presentation.CreateProfile.CreateProfileViewModel
import com.example.fitnessapp.feature_app.presentation.Home.HomeViewModel
import com.example.fitnessapp.feature_app.presentation.MealDetail.MealDetailsViewModel
import com.example.fitnessapp.feature_app.presentation.MealSchedule.MealScheduleViewModel
import com.example.fitnessapp.feature_app.presentation.Notification.NotificationViewModel
import com.example.fitnessapp.feature_app.presentation.OnBoard.OnBoardViewModel
import com.example.fitnessapp.feature_app.presentation.Profile.ProfileViewModel
import com.example.fitnessapp.feature_app.presentation.ProgressPhoto.ProgressPhotoViewModel
import com.example.fitnessapp.feature_app.presentation.RegisterPage.RegisterPageViewModel
import com.example.fitnessapp.feature_app.presentation.SignIn.SignInViewModel
import com.example.fitnessapp.feature_app.presentation.SignUp.SignUpViewModel
import com.example.fitnessapp.feature_app.presentation.SleepSchedule.SleepScheduleViewModel
import com.example.fitnessapp.feature_app.presentation.SleepTracker.SleepTrackerViewModel
import com.example.fitnessapp.feature_app.presentation.StartWorkout.StartWorkoutViewModel
import com.example.fitnessapp.feature_app.presentation.SuccessRegistration.SuccessRegistrationViewModel
import com.example.fitnessapp.feature_app.presentation.TakePhoto.TakePhotoViewModel
import com.example.fitnessapp.feature_app.presentation.Welcome.WelcomeViewModel
import com.example.fitnessapp.feature_app.presentation.WorkoutDetail.WorkoutDetailViewModel
import com.example.fitnessapp.feature_app.presentation.WorkoutSchedule.WorkoutScheduleViewModel
import com.example.fitnessapp.feature_app.presentation.WorkoutTracker.WorkoutViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleViewModel = module {

    viewModel<WelcomeViewModel> {
        WelcomeViewModel(get())
    }
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
            get(), get(),
            get(), get(), get()
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

    viewModel<WorkoutDetailViewModel>{
        WorkoutDetailViewModel(get(), get())
    }

    viewModel<CategoryBreakfastViewModel>{
        CategoryBreakfastViewModel(
            get(), get()
        )
    }

    viewModel<MealDetailsViewModel>{
        MealDetailsViewModel(get(), get())
    }

    viewModel<MealScheduleViewModel>{
        MealScheduleViewModel(
            get(), get(), get()
        )
    }

    viewModel<WorkoutScheduleViewModel>{
        WorkoutScheduleViewModel(get(), get())
    }

    viewModel<AddWorkoutScheduleViewModel>{
        AddWorkoutScheduleViewModel(get())
    }

    viewModel<StartWorkoutViewModel>{
        StartWorkoutViewModel(get())
    }

    viewModel<SleepTrackerViewModel>{
        SleepTrackerViewModel(
            get(), get(), get(), get(),
            get()
        )
    }

    viewModel<SleepScheduleViewModel>{
        SleepScheduleViewModel(get(), get(), get(),get(),
            get(), get())
    }

    viewModel<AddAlarmViewModel>{
        AddAlarmViewModel(get())
    }

    viewModel<ProgressPhotoViewModel>{
        ProgressPhotoViewModel(get())
    }

    viewModel<ComparisonViewModel>{
        ComparisonViewModel()
    }

    viewModel<CompareResultViewModel>{
        CompareResultViewModel(get(), get())
    }

    viewModel<TakePhotoViewModel> {
        TakePhotoViewModel(get())
    }
}