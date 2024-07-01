package com.dreamsoftware.fitflextv.di


import com.dreamsoftware.fitflextv.data.repository.challenges.ChallengesRepository
import com.dreamsoftware.fitflextv.data.repository.challenges.ChallengesRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.instructor.InstructorRepository
import com.dreamsoftware.fitflextv.data.repository.instructor.InstructorRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.routine.RoutineRepository
import com.dreamsoftware.fitflextv.data.repository.routine.RoutineRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.series.SeriesRepository
import com.dreamsoftware.fitflextv.data.repository.series.SeriesRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.sessions.SessionRepository
import com.dreamsoftware.fitflextv.data.repository.sessions.SessionRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.training.TrainingRepository
import com.dreamsoftware.fitflextv.data.repository.training.TrainingRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.user.UserRepository
import com.dreamsoftware.fitflextv.data.repository.user.UserRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.workout.WorkoutRepository
import com.dreamsoftware.fitflextv.data.repository.workout.WorkoutRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Singleton
    @Binds
    abstract fun bindSessionRepository(
        sessionRepository: SessionRepositoryImpl
    ): SessionRepository

    @Singleton
    @Binds
    abstract fun bindSeriesRepository(
        seriesRepositoryImpl: SeriesRepositoryImpl
    ): SeriesRepository

    @Singleton
    @Binds
    abstract fun bindWorkoutRepository(
        workoutRepositoryImpl: WorkoutRepositoryImpl
    ): WorkoutRepository

    @Singleton
    @Binds
    abstract fun bindInstructorRepository(
        instructorRepositoryImpl: InstructorRepositoryImpl
    ): InstructorRepository


    @Binds
    @Singleton
    abstract fun bindChallengeRepository(
       challengesRepositoryImpl: ChallengesRepositoryImpl
    ): ChallengesRepository

    @Binds
    @Singleton
    abstract fun bindRoutineRepository(
        routineRepositoryImpl: RoutineRepositoryImpl
    ): RoutineRepository

    @Binds
    @Singleton
    abstract fun bindTrainingRepository(
        trainingRepositoryImpl: TrainingRepositoryImpl
    ): TrainingRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}