package com.dreamsoftware.fitflextv.di

import com.dreamsoftware.fitflextv.data.preferences.datasource.IProfileSessionDataSource
import com.dreamsoftware.fitflextv.data.preferences.dto.ProfileSelectedPreferenceDTO
import com.dreamsoftware.fitflextv.data.remote.datasource.IAuthRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.ICategoryRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IChallengesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IProfilesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.ISeriesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IUserRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IWorkoutRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ChallengeDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.WorkoutDTO
import com.dreamsoftware.fitflextv.data.remote.mapper.ProfileSessionMapper
import com.dreamsoftware.fitflextv.data.repository.impl.CategoryRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.InstructorRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.ProfilesRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.TrainingRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.UserRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.mapper.CategoryMapper
import com.dreamsoftware.fitflextv.data.repository.mapper.ChallengeMapper
import com.dreamsoftware.fitflextv.data.repository.mapper.CreateProfileRequestMapper
import com.dreamsoftware.fitflextv.data.repository.mapper.CreateUserMapper
import com.dreamsoftware.fitflextv.data.repository.mapper.ProfileMapper
import com.dreamsoftware.fitflextv.data.repository.mapper.RoutineMapper
import com.dreamsoftware.fitflextv.data.repository.mapper.SeriesMapper
import com.dreamsoftware.fitflextv.data.repository.mapper.UpdateProfileRequestMapper
import com.dreamsoftware.fitflextv.data.repository.mapper.UpdatedUserRequestMapper
import com.dreamsoftware.fitflextv.data.repository.mapper.UserDetailMapper
import com.dreamsoftware.fitflextv.data.repository.mapper.WorkoutMapper
import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.model.ChallengeBO
import com.dreamsoftware.fitflextv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.fitflextv.domain.model.SignUpBO
import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.model.RoutineBO
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.fitflextv.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.repository.ICategoryRepository
import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.ui.utils.IMapper
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRoutineMapper(): IOneSideMapper<RoutineDTO, RoutineBO> = RoutineMapper()

    @Provides
    @Singleton
    fun provideCategoryMapper(): IOneSideMapper<CategoryDTO, CategoryBO> = CategoryMapper()

    @Provides
    @Singleton
    fun provideSeriesMapper(): IOneSideMapper<SeriesDTO, SeriesBO> = SeriesMapper()

    @Provides
    @Singleton
    fun provideWorkoutMapper(): IOneSideMapper<WorkoutDTO, WorkoutBO> = WorkoutMapper()

    @Provides
    @Singleton
    fun provideChallengeMapper(
        workoutMapper: IOneSideMapper<WorkoutDTO, WorkoutBO>
    ): IOneSideMapper<Pair<ChallengeDTO, List<WorkoutDTO>>, ChallengeBO> = ChallengeMapper(workoutMapper)

    @Provides
    @Singleton
    fun provideProfileMapper(): IOneSideMapper<ProfileDTO, ProfileBO> = ProfileMapper()

    @Provides
    @Singleton
    fun provideCreateProfileRequestMapper(): IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO> = CreateProfileRequestMapper()

    @Provides
    @Singleton
    fun provideUpdateProfileRequestMapper(): IOneSideMapper<UpdatedProfileRequestBO, UpdatedProfileRequestDTO> = UpdateProfileRequestMapper()

    @Provides
    @Singleton
    fun provideProfileSessionMapper(): IMapper<ProfileBO, ProfileSelectedPreferenceDTO> = ProfileSessionMapper()

    @Provides
    @Singleton
    fun provideUserDetailMapper(): IOneSideMapper<UserResponseDTO, UserDetailBO> = UserDetailMapper()

    @Provides
    @Singleton
    fun provideUpdatedUserRequestMapper(): IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO> = UpdatedUserRequestMapper()

    @Provides
    @Singleton
    fun provideUpdatedCreateUserMapper(): IOneSideMapper<SignUpBO, CreateUserDTO> = CreateUserMapper()

    @Provides
    @Singleton
    fun provideInstructorRepository(
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IInstructorRepository =
        InstructorRepositoryImpl(dispatcher)


    @Provides
    @Singleton
    fun provideTrainingRepository(
        routineRemoteDataSource: IRoutineRemoteDataSource,
        workoutRemoteDataSource: IWorkoutRemoteDataSource,
        seriesRemoteDataSource: ISeriesRemoteDataSource,
        challengesRemoteDataSource: IChallengesRemoteDataSource,
        routineMapper: IOneSideMapper<RoutineDTO, RoutineBO>,
        workoutMapper: IOneSideMapper<WorkoutDTO, WorkoutBO>,
        seriesMapper: IOneSideMapper<SeriesDTO, SeriesBO>,
        challengesMapper: IOneSideMapper<Pair<ChallengeDTO, List<WorkoutDTO>>, ChallengeBO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ITrainingRepository =
        TrainingRepositoryImpl(
            routineRemoteDataSource,
            workoutRemoteDataSource,
            seriesRemoteDataSource,
            challengesRemoteDataSource,
            routineMapper,
            workoutMapper,
            seriesMapper,
            challengesMapper,
            dispatcher
        )

    @Provides
    @Singleton
    fun provideUserRepository(
        userRemoteDataSource: IUserRemoteDataSource,
        authRemoteDataSource: IAuthRemoteDataSource,
        userDetailMapper: IOneSideMapper<UserResponseDTO, UserDetailBO>,
        updatedUserRequestMapper: IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO>,
        createUserMapper: IOneSideMapper<SignUpBO, CreateUserDTO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IUserRepository =
        UserRepositoryImpl(
            userRemoteDataSource,
            authRemoteDataSource,
            userDetailMapper,
            updatedUserRequestMapper,
            createUserMapper,
            dispatcher
        )

    @Provides
    @Singleton
    fun provideCategoryRepository(
        categoryRemoteDataSource: ICategoryRemoteDataSource,
        categoryMapper: IOneSideMapper<CategoryDTO, CategoryBO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ICategoryRepository =
        CategoryRepositoryImpl(
            categoryRemoteDataSource,
            categoryMapper,
            dispatcher
        )


    @Provides
    @Singleton
    fun provideProfilesRepository(
        profilesRemoteDataSource: IProfilesRemoteDataSource,
        profilesMapper: IOneSideMapper<ProfileDTO, ProfileBO>,
        createProfileMapper: IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO>,
        updateProfileMapper: IOneSideMapper<UpdatedProfileRequestBO, UpdatedProfileRequestDTO>,
        profileSessionMapper: IMapper<ProfileBO, ProfileSelectedPreferenceDTO>,
        profileSessionDataSource: IProfileSessionDataSource,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IProfilesRepository =
        ProfilesRepositoryImpl(
            profilesRemoteDataSource,
            profilesMapper,
            createProfileMapper,
            updateProfileMapper,
            profileSessionMapper,
            profileSessionDataSource,
            dispatcher
        )
}