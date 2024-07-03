package com.dreamsoftware.fitflextv.di

import com.dreamsoftware.fitflextv.data.repository.impl.ChallengesRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.InstructorRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.RoutineRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.SeriesRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.SessionRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.TrainingRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.UserRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.WorkoutRepositoryImpl
import com.dreamsoftware.fitflextv.domain.repository.IChallengesRepository
import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import com.dreamsoftware.fitflextv.domain.repository.IRoutineRepository
import com.dreamsoftware.fitflextv.domain.repository.ISeriesRepository
import com.dreamsoftware.fitflextv.domain.repository.ISessionRepository
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.domain.repository.IWorkoutRepository
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
    fun provideChallengeRepository(
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IChallengesRepository =
        ChallengesRepositoryImpl(dispatcher)

    @Provides
    @Singleton
    fun provideInstructorRepository(
        sessionRepository: ISessionRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IInstructorRepository =
        InstructorRepositoryImpl(sessionRepository, dispatcher)

    @Provides
    @Singleton
    fun provideRoutineRepository(
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IRoutineRepository =
        RoutineRepositoryImpl(dispatcher)

    @Provides
    @Singleton
    fun provideSeriesRepository(
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ISeriesRepository =
        SeriesRepositoryImpl(dispatcher)

    @Provides
    @Singleton
    fun provideSessionRepository(
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ISessionRepository =
        SessionRepositoryImpl(dispatcher)

    @Provides
    @Singleton
    fun provideTrainingRepository(
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ITrainingRepository =
        TrainingRepositoryImpl(dispatcher)

    @Provides
    @Singleton
    fun provideUserRepository(
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IUserRepository =
        UserRepositoryImpl(dispatcher)

    @Provides
    @Singleton
    fun provideWorkoutRepository(
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IWorkoutRepository =
        WorkoutRepositoryImpl(dispatcher)
}