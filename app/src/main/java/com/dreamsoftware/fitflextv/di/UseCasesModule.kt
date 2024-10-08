package com.dreamsoftware.fitflextv.di

import com.dreamsoftware.fitflextv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.fitflextv.domain.model.SignInBO
import com.dreamsoftware.fitflextv.domain.model.SignUpBO
import com.dreamsoftware.fitflextv.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.fitflextv.domain.repository.ICategoryRepository
import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fitflextv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.repository.ITrainingSongsRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.usecase.AddFavoriteTrainingUseCase
import com.dreamsoftware.fitflextv.domain.usecase.AddUserSubscriptionUseCase
import com.dreamsoftware.fitflextv.domain.usecase.ChangeSecurePinUseCase
import com.dreamsoftware.fitflextv.domain.usecase.CreateProfileUseCase
import com.dreamsoftware.fitflextv.domain.usecase.DeleteProfileUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetCategoriesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetCategoryByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetFavoritesTrainingsByUserUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetFeaturedTrainingsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetInstructorDetailUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetInstructorsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetProfileByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetProfileSelectedUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetProfilesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetSongByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetSubscriptionsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsByCategoryUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsByTypeUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsRecommendedUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetUserDetailUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetUserPreferencesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetUserProfilesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetUserSubscriptionUseCase
import com.dreamsoftware.fitflextv.domain.usecase.HasActiveSubscriptionUseCase
import com.dreamsoftware.fitflextv.domain.usecase.HasMultiplesProfilesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.RemoveFavoriteTrainingUseCase
import com.dreamsoftware.fitflextv.domain.usecase.RemoveUserSubscriptionUseCase
import com.dreamsoftware.fitflextv.domain.usecase.SaveUserPreferencesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.SelectProfileUseCase
import com.dreamsoftware.fitflextv.domain.usecase.SignInUseCase
import com.dreamsoftware.fitflextv.domain.usecase.SignOffUseCase
import com.dreamsoftware.fitflextv.domain.usecase.SignUpUseCase
import com.dreamsoftware.fitflextv.domain.usecase.UpdateProfileUseCase
import com.dreamsoftware.fitflextv.domain.usecase.VerifyPinUseCase
import com.dreamsoftware.fitflextv.domain.usecase.VerifyTrainingInFavoritesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.VerifyUserSessionUseCase
import com.dreamsoftware.fitflextv.domain.validation.IBusinessEntityValidator
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
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository
    ): GetUserProfilesUseCase =
        GetUserProfilesUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository
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
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository,
        trainingRepository: ITrainingRepository
    ): GetTrainingsRecommendedUseCase =
        GetTrainingsRecommendedUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository,
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
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository,
        trainingRepository: ITrainingRepository
    ): GetTrainingsByTypeUseCase =
        GetTrainingsByTypeUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository,
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
    fun provideGetSongByIdUseCase(
        trainingSongRepository: ITrainingSongsRepository
    ): GetSongByIdUseCase =
        GetSongByIdUseCase(
            trainingSongRepository = trainingSongRepository
        )

    @Provides
    @ViewModelScoped
    fun provideSignInUseCase(
        userRepository: IUserRepository,
        validator: IBusinessEntityValidator<SignInBO>
    ): SignInUseCase =
        SignInUseCase(
            userRepository = userRepository,
            validator = validator
        )

    @Provides
    @ViewModelScoped
    fun provideSignUpUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository,
        validator: IBusinessEntityValidator<SignUpBO>
    ): SignUpUseCase =
        SignUpUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository,
            validator = validator
        )


    @Provides
    @ViewModelScoped
    fun provideGetFeaturedTrainingsUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository,
        trainingRepository: ITrainingRepository
    ): GetFeaturedTrainingsUseCase =
        GetFeaturedTrainingsUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository,
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetTrainingsByCategoryUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository,
        trainingRepository: ITrainingRepository
    ): GetTrainingsByCategoryUseCase =
        GetTrainingsByCategoryUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository,
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

    @Provides
    @ViewModelScoped
    fun provideGetUserDetailUseCase(
        userRepository: IUserRepository
    ): GetUserDetailUseCase =
        GetUserDetailUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetProfilesUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository
    ): GetProfilesUseCase =
        GetProfilesUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository
        )


    @Provides
    @ViewModelScoped
    fun provideSelectProfileUseCase(
        profilesRepository: IProfilesRepository
    ): SelectProfileUseCase =
        SelectProfileUseCase(
            profilesRepository = profilesRepository
        )


    @Provides
    @ViewModelScoped
    fun provideGetProfileSelectedUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository
    ): GetProfileSelectedUseCase =
        GetProfileSelectedUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetProfileByIdUseCase(
        profilesRepository: IProfilesRepository
    ): GetProfileByIdUseCase =
        GetProfileByIdUseCase(
            profilesRepository = profilesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideSignOffUseCase(
        userRepository: IUserRepository
    ): SignOffUseCase =
        SignOffUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideVerifyPinUseCase(
        profilesRepository: IProfilesRepository
    ): VerifyPinUseCase =
        VerifyPinUseCase(
            profilesRepository = profilesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideVerifyUserSessionUseCase(
        userRepository: IUserRepository
    ): VerifyUserSessionUseCase =
        VerifyUserSessionUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideCreateProfileUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository,
        validator: IBusinessEntityValidator<CreateProfileRequestBO>
    ): CreateProfileUseCase =
        CreateProfileUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository,
            validator = validator
        )


    @Provides
    @ViewModelScoped
    fun provideUpdateProfileUseCase(
        profilesRepository: IProfilesRepository,
        validator:  IBusinessEntityValidator<UpdatedProfileRequestBO>
    ): UpdateProfileUseCase =
        UpdateProfileUseCase(
            profilesRepository = profilesRepository,
            validator = validator
        )

    @Provides
    @ViewModelScoped
    fun provideAddFavoriteTrainingUseCase(
        userRepository: IUserRepository,
        profileRepository: IProfilesRepository,
        trainingRepository: ITrainingRepository
    ): AddFavoriteTrainingUseCase =
        AddFavoriteTrainingUseCase(
            userRepository = userRepository,
            profileRepository = profileRepository,
            trainingRepository = trainingRepository
        )


    @Provides
    @ViewModelScoped
    fun provideGetFavoritesTrainingsByUserUseCase(
        userRepository: IUserRepository,
        profileRepository: IProfilesRepository,
        trainingRepository: ITrainingRepository
    ): GetFavoritesTrainingsByUserUseCase =
        GetFavoritesTrainingsByUserUseCase(
            userRepository = userRepository,
            profileRepository = profileRepository,
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideRemoveFavoriteTrainingUseCase(
        userRepository: IUserRepository,
        trainingRepository: ITrainingRepository
    ): RemoveFavoriteTrainingUseCase =
        RemoveFavoriteTrainingUseCase(
            userRepository = userRepository,
            trainingRepository = trainingRepository
        )


    @Provides
    @ViewModelScoped
    fun provideVerifyTrainingInFavoritesUseCase(
        userRepository: IUserRepository,
        profileRepository: IProfilesRepository,
        trainingRepository: ITrainingRepository
    ): VerifyTrainingInFavoritesUseCase =
        VerifyTrainingInFavoritesUseCase(
            userRepository = userRepository,
            profileRepository = profileRepository,
            trainingRepository = trainingRepository
        )

    @Provides
    @ViewModelScoped
    fun provideDeleteProfileUseCase(
        profileRepository: IProfilesRepository
    ): DeleteProfileUseCase =
        DeleteProfileUseCase(profileRepository = profileRepository)

    @Provides
    @ViewModelScoped
    fun provideHasMultiplesProfilesUseCase(
        userRepository: IUserRepository,
        profilesRepository: IProfilesRepository
    ): HasMultiplesProfilesUseCase =
        HasMultiplesProfilesUseCase(
            userRepository = userRepository,
            profilesRepository = profilesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetSubscriptionsUseCase(
        subscriptionsRepository: ISubscriptionsRepository
    ): GetSubscriptionsUseCase =
        GetSubscriptionsUseCase(
            subscriptionsRepository = subscriptionsRepository
        )

    @Provides
    @ViewModelScoped
    fun provideHasActiveSubscriptionUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository
    ): HasActiveSubscriptionUseCase =
        HasActiveSubscriptionUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository
        )

    @Provides
    @ViewModelScoped
    fun provideRemoveUserSubscriptionUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository
    ): RemoveUserSubscriptionUseCase =
        RemoveUserSubscriptionUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository
        )


    @Provides
    @ViewModelScoped
    fun provideAddUserSubscriptionUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository
    ): AddUserSubscriptionUseCase =
        AddUserSubscriptionUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository
        )


    @Provides
    @ViewModelScoped
    fun provideGetUserSubscriptionUseCase(
        userRepository: IUserRepository,
        subscriptionsRepository: ISubscriptionsRepository
    ): GetUserSubscriptionUseCase =
        GetUserSubscriptionUseCase(
            userRepository = userRepository,
            subscriptionsRepository = subscriptionsRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetUserPreferencesUseCase(
        userRepository: IUserRepository
    ): GetUserPreferencesUseCase =
        GetUserPreferencesUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideSaveUserPreferencesUseCase(
        userRepository: IUserRepository
    ): SaveUserPreferencesUseCase =
        SaveUserPreferencesUseCase(
            userRepository = userRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetInstructorDetailUseCase(
        instructorRepository: IInstructorRepository
    ): GetInstructorDetailUseCase =
        GetInstructorDetailUseCase(
            instructorRepository = instructorRepository
        )

    @Provides
    @ViewModelScoped
    fun provideChangeSecurePinUseCase(
        profilesRepository: IProfilesRepository,
        validator:  IBusinessEntityValidator<UpdatedProfileRequestBO>
    ): ChangeSecurePinUseCase =
        ChangeSecurePinUseCase(
            profilesRepository = profilesRepository,
            validator = validator
        )
}