package com.dreamsoftware.fitflextv.di

import com.dreamsoftware.fitflextv.domain.repository.ICategoryRepository
import com.dreamsoftware.fitflextv.domain.repository.IChallengesRepository
import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.GetCategoriesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetCategoryByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetChallengeByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetChallengesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetFavoritesWorkoutsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetFeaturedTrainingsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetInstructorsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetSongByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsByCategoryUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsByTypeUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsRecommendedUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetUserProfilesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.SignInUseCase
import com.dreamsoftware.fitflextv.domain.usecase.SignUpUseCase
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
    fun provideGetCategoriesUseCase(
        categoryRepository: ICategoryRepository
    ): GetCategoriesUseCase =
        GetCategoriesUseCase(
            categoryRepository = categoryRepository
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
    fun provideGetTrainingByIdUseCase(
        trainingRepository: ITrainingRepository
    ): GetTrainingByIdUseCase =
        GetTrainingByIdUseCase(
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetTrainingsByTypeUseCase(
        trainingRepository: ITrainingRepository
    ): GetTrainingsByTypeUseCase =
        GetTrainingsByTypeUseCase(
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
    fun provideGetChallengeByIdUseCase(
        challengesRepository: IChallengesRepository
    ): GetChallengeByIdUseCase =
        GetChallengeByIdUseCase(
            challengesRepository = challengesRepository
        )


    @Provides
    @ViewModelScoped
    fun provideGetSongByIdUseCase(
        trainingRepository: ITrainingRepository
    ): GetSongByIdUseCase =
        GetSongByIdUseCase(
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideSignInUseCase(): SignInUseCase =
        SignInUseCase()

    @Provides
    @ViewModelScoped
    fun provideSignUpUseCase(): SignUpUseCase =
        SignUpUseCase()


    @Provides
    @ViewModelScoped
    fun provideGetChallengesUseCase(
        challengesRepository: IChallengesRepository
    ): GetChallengesUseCase =
        GetChallengesUseCase(
            challengesRepository = challengesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetFeaturedTrainingsUseCase(
        trainingRepository: ITrainingRepository
    ): GetFeaturedTrainingsUseCase =
        GetFeaturedTrainingsUseCase(
            trainingRepository = trainingRepository
        )


    @Provides
    @ViewModelScoped
    fun provideGetFavoritesWorkoutsUseCase(
        trainingRepository: ITrainingRepository
    ): GetFavoritesWorkoutsUseCase =
        GetFavoritesWorkoutsUseCase(
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetTrainingsByCategoryUseCase(
        trainingRepository: ITrainingRepository
    ): GetTrainingsByCategoryUseCase =
        GetTrainingsByCategoryUseCase(
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetCategoryByIdUseCase(
        categoryRepository: ICategoryRepository
    ): GetCategoryByIdUseCase =
        GetCategoryByIdUseCase(
            categoryRepository = categoryRepository
        )
}