package com.dreamsoftware.fitflextv.di

import com.dreamsoftware.fitflextv.data.remote.datasource.ICategoryRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.ISeriesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IWorkoutRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.CategoryDTO
import com.dreamsoftware.fitflextv.data.remote.dto.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.dto.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.dto.WorkoutDTO
import com.dreamsoftware.fitflextv.data.repository.impl.CategoryRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.ChallengesRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.InstructorRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.TrainingRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.impl.UserRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.mapper.CategoryMapper
import com.dreamsoftware.fitflextv.data.repository.mapper.RoutineMapper
import com.dreamsoftware.fitflextv.data.repository.mapper.SeriesMapper
import com.dreamsoftware.fitflextv.data.repository.mapper.WorkoutMapper
import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.model.RoutineBO
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.repository.ICategoryRepository
import com.dreamsoftware.fitflextv.domain.repository.IChallengesRepository
import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
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
    fun provideChallengeRepository(
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IChallengesRepository =
        ChallengesRepositoryImpl(dispatcher)

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
        routineMapper: IOneSideMapper<RoutineDTO, RoutineBO>,
        workoutMapper: IOneSideMapper<WorkoutDTO, WorkoutBO>,
        seriesMapper: IOneSideMapper<SeriesDTO, SeriesBO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ITrainingRepository =
        TrainingRepositoryImpl(
            routineRemoteDataSource,
            workoutRemoteDataSource,
            seriesRemoteDataSource,
            routineMapper,
            workoutMapper,
            seriesMapper,
            dispatcher
        )

    @Provides
    @Singleton
    fun provideUserRepository(
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IUserRepository =
        UserRepositoryImpl(dispatcher)

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
}