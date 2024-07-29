package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IChallengesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IFavoritesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.ISeriesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IWorkoutRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.request.AddFavoriteTrainingDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ChallengeDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.WorkoutDTO
import com.dreamsoftware.fitflextv.data.remote.exception.AddToFavoritesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.GetFavoritesByUserExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.HasTrainingInFavoritesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.RemoteDataSourceException
import com.dreamsoftware.fitflextv.data.remote.exception.RemoveFromFavoritesExceptionRemote
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.exception.AddFavoriteTrainingException
import com.dreamsoftware.fitflextv.domain.exception.FetchFavoritesTrainingsByUserException
import com.dreamsoftware.fitflextv.domain.exception.FetchFeaturedTrainingsException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingByCategoryException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingByIdException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingsException
import com.dreamsoftware.fitflextv.domain.exception.FetchTrainingsRecommendedException
import com.dreamsoftware.fitflextv.domain.exception.RemoveFavoriteTrainingException
import com.dreamsoftware.fitflextv.domain.exception.VerifyFavoriteTrainingException
import com.dreamsoftware.fitflextv.domain.model.AddFavoriteTrainingBO
import com.dreamsoftware.fitflextv.domain.model.ChallengeBO
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.RoutineBO
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.dreamsoftware.fitflextv.ui.utils.parallelMap
import com.dreamsoftware.fitflextv.utils.enumValueOfOrDefault
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async

internal class TrainingRepositoryImpl(
    private val routineRemoteDataSource: IRoutineRemoteDataSource,
    private val workoutRemoteDataSource: IWorkoutRemoteDataSource,
    private val seriesRemoteDataSource: ISeriesRemoteDataSource,
    private val challengeRemoteDataSource: IChallengesRemoteDataSource,
    private val favoritesRemoteDataSource: IFavoritesRemoteDataSource,
    private val routineMapper: IOneSideMapper<RoutineDTO, RoutineBO>,
    private val workoutMapper: IOneSideMapper<WorkoutDTO, WorkoutBO>,
    private val seriesMapper: IOneSideMapper<SeriesDTO, SeriesBO>,
    private val addFavoriteMapper: IOneSideMapper<AddFavoriteTrainingBO, AddFavoriteTrainingDTO>,
    private val challengeMapper: IOneSideMapper<Pair<ChallengeDTO, List<WorkoutDTO>>, ChallengeBO>,
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

                    TrainingTypeEnum.CHALLENGES -> challengeRemoteDataSource.getChallenges()
                        .map {
                            challengeMapper.mapInToOut(it to it.weaklyPlans.parallelMap { weaklyPlan ->
                                workoutRemoteDataSource.getWorkoutByIdList(weaklyPlan.workouts)
                            }.flatten())
                        }

                    TrainingTypeEnum.ROUTINE -> routineRemoteDataSource.getRoutines()
                        .let(routineMapper::mapInListToOutList)
                }
            } catch (ex: RemoteDataSourceException) {
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

                    TrainingTypeEnum.CHALLENGES -> challengeRemoteDataSource.getChallengeById(id)
                        .let {
                            challengeMapper.mapInToOut(it to it.weaklyPlans.parallelMap { weaklyPlan ->
                                workoutRemoteDataSource.getWorkoutByIdList(weaklyPlan.workouts)
                            }.flatten())
                        }

                    TrainingTypeEnum.ROUTINE -> routineRemoteDataSource.getRoutineById(id)
                        .let(routineMapper::mapInToOut)
                }
            } catch (ex: RemoteDataSourceException) {
                throw FetchTrainingsException("An error occurred when fetching the training", ex)
            }
        }

    @Throws(FetchTrainingsRecommendedException::class)
    override suspend fun getTrainingsRecommended(): Iterable<ITrainingProgramBO> = safeExecute {
        emptyList()
    }

    @Throws(FetchFeaturedTrainingsException::class)
    override suspend fun getFeaturedTrainings(): Iterable<ITrainingProgramBO> = safeExecute {
        try {
            val workoutDeferred = async(dispatcher) {
                runCatching {
                    workoutRemoteDataSource.getFeaturedWorkouts()
                        .let(workoutMapper::mapInListToOutList)
                }
                    .getOrElse { emptyList() }
            }
            val seriesDeferred = async(dispatcher) {
                runCatching {
                    seriesRemoteDataSource.getFeaturedSeries()
                        .let(seriesMapper::mapInListToOutList)
                }
                    .getOrElse { emptyList() }
            }
            val routinesDeferred = async(dispatcher) {
                runCatching {
                    routineRemoteDataSource.getFeaturedRoutines()
                        .let(routineMapper::mapInListToOutList)
                }
                    .getOrElse { emptyList() }
            }
            workoutDeferred.await() + seriesDeferred.await() + routinesDeferred.await()
        } catch (ex: RemoteDataSourceException) {
            throw FetchFeaturedTrainingsException(
                "An error occurred when fetching the featured training",
                ex
            )
        }
    }

    @Throws(FetchTrainingByCategoryException::class)
    override suspend fun getTrainingsByCategory(id: String): Iterable<ITrainingProgramBO> =
        safeExecute {
            try {
                val workoutDeferred = async(dispatcher) {
                    runCatching {
                        workoutRemoteDataSource.getWorkoutByCategory(id)
                            .let(workoutMapper::mapInListToOutList)
                    }
                        .getOrElse { emptyList() }
                }
                val seriesDeferred = async(dispatcher) {
                    runCatching {
                        seriesRemoteDataSource.getSeriesByCategory(id)
                            .let(seriesMapper::mapInListToOutList)
                    }
                        .getOrElse { emptyList() }
                }
                val challengesDeferred = async(dispatcher) {
                    runCatching {
                        challengeRemoteDataSource.getChallengesByCategory(id)
                            .map {
                                challengeMapper.mapInToOut(it to it.weaklyPlans.parallelMap { weaklyPlan ->
                                    workoutRemoteDataSource.getWorkoutByIdList(weaklyPlan.workouts)
                                }.flatten())
                            }
                    }
                        .getOrElse { emptyList() }
                }
                val routinesDeferred = async(dispatcher) {
                    runCatching {
                        routineRemoteDataSource.getRoutineByCategory(id)
                            .let(routineMapper::mapInListToOutList)
                    }
                        .getOrElse { emptyList() }
                }
                workoutDeferred.await() + seriesDeferred.await() + routinesDeferred.await() + challengesDeferred.await()
            } catch (ex: RemoteDataSourceException) {
                throw FetchTrainingByCategoryException(
                    "An error occurred when fetching the training",
                    ex
                )
            }
        }

    @Throws(AddFavoriteTrainingException::class)
    override suspend fun addFavoriteTraining(data: AddFavoriteTrainingBO): Boolean =
        safeExecute {
            try {
                favoritesRemoteDataSource.addFavorite(addFavoriteMapper.mapInToOut(data))
            } catch (ex: AddToFavoritesExceptionRemote) {
                throw AddFavoriteTrainingException(
                    "An error occurred when adding training to favorites",
                    ex
                )
            }
        }

    @Throws(FetchFavoritesTrainingsByUserException::class)
    override suspend fun getFavoritesTrainingsByUser(userId: String): List<ITrainingProgramBO> = safeExecute {
        try {
            favoritesRemoteDataSource.getFavoritesByUser(userId).parallelMap {
                getTrainingById(
                    id = it.trainingId,
                    type = enumValueOfOrDefault(it.trainingType, TrainingTypeEnum.WORK_OUT)
                )
            }
        } catch (ex: GetFavoritesByUserExceptionRemote) {
            throw FetchFavoritesTrainingsByUserException(
                "An error occurred when fetching favorites",
                ex
            )
        }
    }

    @Throws(VerifyFavoriteTrainingException::class)
    override suspend fun hasTrainingInFavorites(userId: String, trainingId: String): Boolean = safeExecute {
        try {
            favoritesRemoteDataSource.hasTrainingInFavorites(
                userId = userId,
                trainingId = trainingId
            )
        } catch (ex: HasTrainingInFavoritesExceptionRemote) {
            throw VerifyFavoriteTrainingException(
                "An error occurred when checking favorites",
                ex
            )
        }
    }

    @Throws(RemoveFavoriteTrainingException::class)
    override suspend fun removeFavoriteTraining(userId: String, trainingId: String): Boolean = safeExecute {
        try {
            favoritesRemoteDataSource.removeFavorite(
                userId = userId,
                trainingId = trainingId
            )
        } catch (ex: RemoveFromFavoritesExceptionRemote) {
            throw RemoveFavoriteTrainingException(
                "An error occurred when removing training from favorites",
                ex
            )
        }
    }
}