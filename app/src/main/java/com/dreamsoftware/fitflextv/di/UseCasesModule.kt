package com.dreamsoftware.fitflextv.di

import com.dreamsoftware.fitflextv.domain.repository.ISessionRepository
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.GetCategoriesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetSessionsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsRecommendedUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetUserProfilesUseCase
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
}