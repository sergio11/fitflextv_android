package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.ISeriesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IWorkoutRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.dto.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.dto.WorkoutDTO
import com.dreamsoftware.fitflextv.data.remote.exception.DataSourceException
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingByCategoryException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingByIdException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingsException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingsRecommendedException
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.RoutineBO
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async

internal class TrainingRepositoryImpl(
    private val routineRemoteDataSource: IRoutineRemoteDataSource,
    private val workoutRemoteDataSource: IWorkoutRemoteDataSource,
    private val seriesRemoteDataSource: ISeriesRemoteDataSource,
    private val routineMapper: IOneSideMapper<RoutineDTO, RoutineBO>,
    private val workoutMapper: IOneSideMapper<WorkoutDTO, WorkoutBO>,
    private val seriesMapper: IOneSideMapper<SeriesDTO, SeriesBO>,
    private val dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), ITrainingRepository {

    @Throws(FetchTrainingsException::class)
    override suspend fun getTrainings(type: TrainingTypeEnum): Iterable<ITrainingProgramBO> =
        safeExecute {
            try {
                when (type) {
                    TrainingTypeEnum.WORK_OUT -> workoutRemoteDataSource.getWorkouts()
                        .let(workoutMapper::mapInListToOutList)

                    TrainingTypeEnum.SERIES -> seriesRemoteDataSource.getSeries()
                        .let(seriesMapper::mapInListToOutList)

                    TrainingTypeEnum.CHALLENGES -> seriesRemoteDataSource.getSeries()
                        .let(seriesMapper::mapInListToOutList)

                    TrainingTypeEnum.ROUTINE -> routineRemoteDataSource.getRoutines()
                        .let(routineMapper::mapInListToOutList)
                }
            } catch (ex: DataSourceException) {
                throw FetchTrainingsException("An error occurred when fetching trainings", ex)
            }
        }

    @Throws(FetchTrainingByIdException::class)
    override suspend fun getTrainingById(id: String, type: TrainingTypeEnum): ITrainingProgramBO =
        safeExecute {
            try {
                when (type) {
                    TrainingTypeEnum.WORK_OUT -> workoutRemoteDataSource.getWorkoutById(id)
                        .let(workoutMapper::mapInToOut)

                    TrainingTypeEnum.SERIES -> seriesRemoteDataSource.getSeriesById(id)
                        .let(seriesMapper::mapInToOut)

                    TrainingTypeEnum.CHALLENGES -> seriesRemoteDataSource.getSeriesById(id)
                        .let(seriesMapper::mapInToOut)

                    TrainingTypeEnum.ROUTINE -> routineRemoteDataSource.getRoutineById(id)
                        .let(routineMapper::mapInToOut)
                }
            } catch (ex: DataSourceException) {
                throw FetchTrainingsException("An error occurred when fetching the training", ex)
            }
        }

    @Throws(FetchTrainingsRecommendedException::class)
    override suspend fun getTrainingsRecommended(): Iterable<ITrainingProgramBO> = safeExecute {
        emptyList()
    }

    @Throws(FetchTrainingByCategoryException::class)
    override suspend fun getTrainingsByCategory(id: String): Iterable<ITrainingProgramBO> = safeExecute {
        try {
            val workoutDeferred = async(dispatcher) { workoutRemoteDataSource.getWorkoutByCategory(id).let(workoutMapper::mapInListToOutList) }
            val seriesDeferred = async(dispatcher) { seriesRemoteDataSource.getSeriesByCategory(id).let(seriesMapper::mapInListToOutList) }
            val routinesDeferred = async(dispatcher) { routineRemoteDataSource.getRoutineByCategory(id).let(routineMapper::mapInListToOutList) }
            workoutDeferred.await() + seriesDeferred.await() + routinesDeferred.await()
        } catch (ex: DataSourceException) {
            throw FetchTrainingByCategoryException("An error occurred when fetching the training", ex)
        }
    }
}