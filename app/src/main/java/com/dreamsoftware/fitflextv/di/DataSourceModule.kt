package com.dreamsoftware.fitflextv.di

import com.dreamsoftware.fitflextv.data.remote.datasource.IAuthRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.ICategoryRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IChallengesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.ISeriesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IWorkoutRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.AuthRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.CategoryRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.ChallengesRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.RoutineRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.SeriesRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.WorkoutRemoteDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.AuthUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.CategoryDTO
import com.dreamsoftware.fitflextv.data.remote.dto.ChallengeDTO
import com.dreamsoftware.fitflextv.data.remote.dto.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.dto.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.dto.WorkoutDTO
import com.dreamsoftware.fitflextv.data.remote.mapper.CategoryRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.ChallengeRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.RoutineRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.SeriesRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.UserAuthenticatedRemoteMapper
import com.dreamsoftware.fitflextv.data.remote.mapper.WorkoutRemoteMapper
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
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
class DataSourceModule {

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
}