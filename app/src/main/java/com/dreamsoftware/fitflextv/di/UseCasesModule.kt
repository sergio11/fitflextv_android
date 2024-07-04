package com.dreamsoftware.fitflextv.di

import com.dreamsoftware.fitflextv.domain.repository.IChallengesRepository
import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import com.dreamsoftware.fitflextv.domain.repository.IRoutineRepository
import com.dreamsoftware.fitflextv.domain.repository.ISeriesRepository
import com.dreamsoftware.fitflextv.domain.repository.ISessionRepository
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.repository.IWorkoutRepository
import com.dreamsoftware.fitflextv.domain.usecase.GetCategoriesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetChallengeByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetFavoritesWorkoutsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetInstructorsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetRoutineByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetSessionsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetSongByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingSeriesByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsRecommendedUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetUserProfilesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetWorkoutByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetWorkoutsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.SignInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetUserProfilesUseCase(
        userRepository: IUserRepository
    ): GetUserProfilesUseCase =
        GetUserProfilesUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetSessionsUseCase(
        sessionRepository: ISessionRepository
    ): GetSessionsUseCase =
        GetSessionsUseCase(
            sessionRepository = sessionRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetCategoriesUseCase(
        sessionRepository: ISessionRepository
    ): GetCategoriesUseCase =
        GetCategoriesUseCase(
            sessionRepository = sessionRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetTrainingsRecommendedUseCase(
        trainingRepository: ITrainingRepository
    ): GetTrainingsRecommendedUseCase =
        GetTrainingsRecommendedUseCase(
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetInstructorsUseCase(
        instructorRepository: IInstructorRepository
    ): GetInstructorsUseCase =
        GetInstructorsUseCase(
            instructorRepository = instructorRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetWorkoutsUseCaseUseCase(
        workoutRepository: IWorkoutRepository
    ): GetWorkoutsUseCase =
        GetWorkoutsUseCase(
            workoutRepository = workoutRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetChallengeByIdUseCase(
        challengesRepository: IChallengesRepository
    ): GetChallengeByIdUseCase =
        GetChallengeByIdUseCase(
            challengesRepository = challengesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetRoutineByIdUseCase(
        routineRepository: IRoutineRepository
    ): GetRoutineByIdUseCase =
        GetRoutineByIdUseCase(
            routineRepository = routineRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetWorkoutByIdUseCase(
        workoutRepository: IWorkoutRepository
    ): GetWorkoutByIdUseCase =
        GetWorkoutByIdUseCase(
            workoutRepository = workoutRepository
        )


    @Provides
    @ViewModelScoped
    fun provideGetTrainingSeriesByIdUseCase(
        seriesRepository: ISeriesRepository
    ): GetTrainingSeriesByIdUseCase =
        GetTrainingSeriesByIdUseCase(
            seriesRepository = seriesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetTrainingByIdUseCase(
        trainingRepository: ITrainingRepository
    ): GetTrainingByIdUseCase =
        GetTrainingByIdUseCase(
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetFavoritesWorkoutsUseCase(
        workoutRepository: IWorkoutRepository
    ): GetFavoritesWorkoutsUseCase =
        GetFavoritesWorkoutsUseCase(
            workoutRepository = workoutRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetSongByIdUseCase(
        seriesRepository: ISeriesRepository
    ): GetSongByIdUseCase =
        GetSongByIdUseCase(
            seriesRepository = seriesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideSignInUseCase(): SignInUseCase =
        SignInUseCase()
}