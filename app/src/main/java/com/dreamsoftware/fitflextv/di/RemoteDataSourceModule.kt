package com.dreamsoftware.fitflextv.di

import com.dreamsoftware.fitflextv.data.remote.datasource.IAuthRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.ICategoryRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IChallengesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IFavoritesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IProfilesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.ISeriesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IUserRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IWorkoutRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.AuthRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.CategoryRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.ChallengesRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.FavoritesRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.ProfilesRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.RoutineRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.SeriesRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.UserRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.WorkoutRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.AddFavoriteTrainingDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.AuthUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ChallengeDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.FavoriteTrainingDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.WorkoutDTO
import com.dreamsoftware.fitflextv.data.remote.mapper.AddFavoriteTrainingRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.CategoryRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.ChallengeRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.CreateProfileRequestRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.CreateUserRequestRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.FavoriteTrainingRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.ProfileRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.RoutineRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.SeriesRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.UpdateProfileRequestRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.UpdatedUserRequestRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.UserAuthenticatedRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.UserRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.WorkoutRemoteMapper
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

// Dagger module for providing Firebase-related dependencies
@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    /**
     * Provides a singleton instance of UserAuthenticatedMapper.
     * @return a new instance of UserAuthenticatedMapper.
     */
    @Provides
    @Singleton
    fun provideUserAuthenticatedRemoteMapper(): IOneSideMapper<FirebaseUser, AuthUserDTO> = UserAuthenticatedRemoteMapper()

    /**
     * Provides a singleton instance of RoutineMapper.
     * @return a new instance of RoutineMapper.
     */
    @Provides
    @Singleton
    fun provideRoutineRemoteMapper(): IOneSideMapper<Map<String, Any?>, RoutineDTO> = RoutineRemoteMapper()

    /**
     * Provides a singleton instance of SeriesMapper.
     * @return a new instance of SeriesMapper.
     */
    @Provides
    @Singleton
    fun provideSeriesRemoteMapper(): IOneSideMapper<Map<String, Any?>, SeriesDTO> = SeriesRemoteMapper()


    /**
     * Provides a singleton instance of CategoryMapper.
     * @return a new instance of CategoryMapper.
     */
    @Provides
    @Singleton
    fun provideCategoryRemoteMapper(): IOneSideMapper<Map<String, Any?>, CategoryDTO> = CategoryRemoteMapper()

    /**
     * Provides a singleton instance of WorkoutRemoteMapper.
     * @return a new instance of WorkoutRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideWorkoutRemoteMapper(): IOneSideMapper<Map<String, Any?>, WorkoutDTO> = WorkoutRemoteMapper()

    /**
     * Provides a singleton instance of ChallengeRemoteMapper.
     * @return a new instance of ChallengeRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideChallengeRemoteMapper(): IOneSideMapper<Map<String, Any?>, ChallengeDTO> = ChallengeRemoteMapper()

    /**
     * Provides a singleton instance of CreateProfileRequestRemoteMapper.
     * @return a new instance of CreateProfileRequestRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideCreateProfileRequestRemoteMapper(): IOneSideMapper<CreateProfileRequestDTO, Map<String, Any?>> = CreateProfileRequestRemoteMapper()

    /**
     * Provides a singleton instance of UpdateProfileRequestRemoteMapper.
     * @return a new instance of UpdateProfileRequestRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideUpdateProfileRequestRemoteMapper(): IOneSideMapper<UpdatedProfileRequestDTO, Map<String, Any?>> = UpdateProfileRequestRemoteMapper()

    /**
     * Provides a singleton instance of UserRemoteMapper.
     * @return a new instance of UserRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideUsersRemoteMapper(): IOneSideMapper<Map<String, Any?>, UserResponseDTO> = UserRemoteMapper()

    /**
     * Provides a singleton instance of UpdatedUserRequestRemoteMapper.
     * @return a new instance of UpdatedUserRequestRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideUpdatedUserRequestRemoteMapper(): IOneSideMapper<UpdatedUserRequestDTO, Map<String, Any?>> = UpdatedUserRequestRemoteMapper()

    /**
     * Provides a singleton instance of CreateUserRequestRemoteMapper.
     * @return a new instance of CreateUserRequestRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideCreateUserRequestRemoteMapper(): IOneSideMapper<CreateUserDTO, Map<String, Any?>> = CreateUserRequestRemoteMapper()

    /**
     * Provides a singleton instance of ProfileRemoteMapper.
     * @return a new instance of ProfileRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideProfileRemoteMapper(): IOneSideMapper<Map<String, Any?>, ProfileDTO> = ProfileRemoteMapper()


    /**
     * Provides a singleton instance of AddFavoriteTrainingRemoteMapper.
     * @return a new instance of AddFavoriteTrainingRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideAddFavoriteTrainingRemoteMapper(): IOneSideMapper<AddFavoriteTrainingDTO, Map<String, Any?>> = AddFavoriteTrainingRemoteMapper()

    /**
     * Provides a singleton instance of FavoriteTrainingRemoteMapper.
     * @return a new instance of FavoriteTrainingRemoteMapper.
     */
    @Provides
    @Singleton
    fun provideFavoriteTrainingRemoteMapper(): IOneSideMapper<Map<String, Any?>, FavoriteTrainingDTO> = FavoriteTrainingRemoteMapper()


    /**
     * Provides a singleton instance of FirebaseAuth.
     * @return the default instance of FirebaseAuth.
     */
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()


    /**
     * Provide Firebase Store
     */
    @Provides
    @Singleton
    fun provideFirebaseStore() = Firebase.firestore

    /**
     * Provides a singleton instance of IAuthDataSource.
     * @param userAuthenticatedMapper the IOneSideMapper<FirebaseUser, AuthUserDTO> instance.
     * @param firebaseAuth the FirebaseAuth instance.
     * @return a new instance of AuthDataSourceImpl implementing IAuthDataSource.
     */
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(
        userAuthenticatedMapper: IOneSideMapper<FirebaseUser, AuthUserDTO>,
        firebaseAuth: FirebaseAuth
    ): IAuthRemoteDataSource = AuthRemoteDataSourceImpl(
        userAuthenticatedMapper,
        firebaseAuth
    )


    @Provides
    @Singleton
    fun provideRoutineRemoteDataSource(
        routineMapper: IOneSideMapper<Map<String, Any?>, RoutineDTO>,
        firestore: FirebaseFirestore,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IRoutineRemoteDataSource = RoutineRemoteDataSourceImpl(
        firestore,
        routineMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideSeriesRemoteDataSource(
        seriesMapper: IOneSideMapper<Map<String, Any?>, SeriesDTO>,
        firestore: FirebaseFirestore,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ISeriesRemoteDataSource = SeriesRemoteDataSourceImpl(
        firestore,
        seriesMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideCategoryRemoteDataSource(
        categoryMapper: IOneSideMapper<Map<String, Any?>, CategoryDTO>,
        firestore: FirebaseFirestore,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ICategoryRemoteDataSource = CategoryRemoteDataSourceImpl(
        firestore,
        categoryMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideWorkoutRemoteDataSource(
        workoutMapper: IOneSideMapper<Map<String, Any?>, WorkoutDTO>,
        firestore: FirebaseFirestore,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IWorkoutRemoteDataSource = WorkoutRemoteDataSourceImpl(
        firestore,
        workoutMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideChallengesRemoteDataSource(
        challengeMapper: IOneSideMapper<Map<String, Any?>, ChallengeDTO>,
        firestore: FirebaseFirestore,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IChallengesRemoteDataSource = ChallengesRemoteDataSourceImpl(
        firestore,
        challengeMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideProfilesRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        profilesMapper: IOneSideMapper<Map<String, Any?>, ProfileDTO>,
        createProfileRequestMapper: IOneSideMapper<CreateProfileRequestDTO, Map<String, Any?>>,
        updateProfileRequestMapper: IOneSideMapper<UpdatedProfileRequestDTO, Map<String, Any?>>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IProfilesRemoteDataSource = ProfilesRemoteDataSourceImpl(
        firebaseStore,
        profilesMapper,
        createProfileRequestMapper,
        updateProfileRequestMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        usersMapper: IOneSideMapper<Map<String, Any?>, UserResponseDTO>,
        updatedUserRequestMapper: IOneSideMapper<UpdatedUserRequestDTO, Map<String, Any?>>,
        createUserRequestMapper: IOneSideMapper<CreateUserDTO, Map<String, Any?>>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IUserRemoteDataSource = UserRemoteDataSourceImpl(
        firebaseStore,
        usersMapper,
        updatedUserRequestMapper,
        createUserRequestMapper,
        dispatcher
    )

    @Provides
    @Singleton
    fun provideFavoritesRemoteDataSource(
        firebaseStore: FirebaseFirestore,
        addFavoriteMapper: IOneSideMapper<AddFavoriteTrainingDTO, Map<String, Any?>>,
        favoriteMapper: IOneSideMapper<Map<String, Any?>, FavoriteTrainingDTO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IFavoritesRemoteDataSource = FavoritesRemoteDataSourceImpl(
        firebaseStore,
        addFavoriteMapper,
        favoriteMapper,
        dispatcher
    )
}