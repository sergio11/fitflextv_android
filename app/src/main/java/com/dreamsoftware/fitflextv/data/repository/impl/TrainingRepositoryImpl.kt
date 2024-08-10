package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IChallengesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IFavoritesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IInstructorsRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.ISeriesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IWorkoutRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.request.AddFavoriteTrainingDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ChallengeDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.InstructorDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.SeriesDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.WorkoutDTO
import com.dreamsoftware.fitflextv.data.remote.exception.AddToFavoritesRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.GetFavoritesByUserRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.HasTrainingInFavoritesRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.RemoteDataSourceException
import com.dreamsoftware.fitflextv.data.remote.exception.RemoveFromFavoritesRemoteException
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
import com.dreamsoftware.fitflextv.domain.model.TrainingFilterDataBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.dreamsoftware.fitflextv.ui.utils.parallelMap
import com.dreamsoftware.fitflextv.utils.enumNameOfOrDefault
import com.dreamsoftware.fitflextv.utils.executeAsync
import kotlinx.coroutines.CoroutineDispatcher

internal class TrainingRepositoryImpl(
    private val routineRemoteDataSource: IRoutineRemoteDataSource,
    private val workoutRemoteDataSource: IWorkoutRemoteDataSource,
    private val seriesRemoteDataSource: ISeriesRemoteDataSource,
    private val challengeRemoteDataSource: IChallengesRemoteDataSource,
    private val favoritesRemoteDataSource: IFavoritesRemoteDataSource,
    private val instructorRemoteDataSource: IInstructorsRemoteDataSource,
    private val routineMapper: IOneSideMapper<Pair<RoutineDTO, InstructorDTO>, RoutineBO>,
    private val workoutMapper: IOneSideMapper<Pair<WorkoutDTO, InstructorDTO>, WorkoutBO>,
    private val seriesMapper: IOneSideMapper<Pair<SeriesDTO, InstructorDTO>, SeriesBO>,
    private val addFavoriteMapper: IOneSideMapper<AddFavoriteTrainingBO, AddFavoriteTrainingDTO>,
    private val trainingFilterDataMapper: IOneSideMapper<TrainingFilterDataBO, TrainingFilterDTO>,
    private val challengesMapper: IOneSideMapper<Triple<ChallengeDTO, List<WorkoutDTO>, InstructorDTO>, ChallengeBO>,
    private val dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), ITrainingRepository {

    @Throws(FetchTrainingsException::class)
    override suspend fun getTrainings(data: TrainingFilterDataBO): Iterable<ITrainingProgramBO> = with(data) {
        safeExecute {
            val filterDTO = trainingFilterDataMapper.mapInToOut(data)
            try {
                when (type) {
                    TrainingTypeEnum.WORK_OUT -> workoutRemoteDataSource.getTrainings(filterDTO).workoutsToTrainingPrograms()
                    TrainingTypeEnum.SERIES -> seriesRemoteDataSource.getTrainings(filterDTO).seriesToTrainingPrograms()
                    TrainingTypeEnum.CHALLENGES -> challengeRemoteDataSource.getTrainings(filterDTO).challengesToTrainingPrograms()
                    TrainingTypeEnum.ROUTINE -> routineRemoteDataSource.getTrainings(filterDTO).routinesToTrainingPrograms()
                }
            } catch (ex: RemoteDataSourceException) {
                throw FetchTrainingsException("An error occurred when fetching trainings", ex)
            }
        }
    }

    @Throws(FetchTrainingByIdException::class)
    override suspend fun getTrainingById(id: String, type: TrainingTypeEnum): ITrainingProgramBO =
        safeExecute {
            try {
                when (type) {
                    TrainingTypeEnum.WORK_OUT -> workoutRemoteDataSource.getTrainingById(id).toTrainingProgram()
                    TrainingTypeEnum.SERIES -> seriesRemoteDataSource.getTrainingById(id).toTrainingProgram()
                    TrainingTypeEnum.CHALLENGES -> challengeRemoteDataSource.getTrainingById(id).toTrainingProgram()
                    TrainingTypeEnum.ROUTINE -> routineRemoteDataSource.getTrainingById(id).toTrainingProgram()
                }
            } catch (ex: RemoteDataSourceException) {
                throw FetchTrainingsException("An error occurred when fetching the training", ex)
            }
        }

    @Throws(FetchTrainingsRecommendedException::class)
    override suspend fun getTrainingsRecommended(): Iterable<ITrainingProgramBO> = safeExecute {
        try {
            val workoutDeferred = executeAsync(dispatcher) { workoutRemoteDataSource.getRecommendedTrainings().workoutsToTrainingPrograms() }
            val seriesDeferred = executeAsync(dispatcher) { seriesRemoteDataSource.getRecommendedTrainings().seriesToTrainingPrograms() }
            val challengesDeferred = executeAsync(dispatcher) { challengeRemoteDataSource.getRecommendedTrainings().challengesToTrainingPrograms() }
            val routinesDeferred = executeAsync(dispatcher) { routineRemoteDataSource.getRecommendedTrainings().routinesToTrainingPrograms() }
            workoutDeferred.await() + seriesDeferred.await() + challengesDeferred.await() + routinesDeferred.await()
        } catch (ex: RemoteDataSourceException) {
            throw FetchTrainingsRecommendedException(
                "An error occurred when fetching the recommended trainings",
                ex
            )
        }
    }

    @Throws(FetchFeaturedTrainingsException::class)
    override suspend fun getFeaturedTrainings(): Iterable<ITrainingProgramBO> = safeExecute {
        try {
            val workoutDeferred = executeAsync(dispatcher) { workoutRemoteDataSource.getFeaturedTrainings().workoutsToTrainingPrograms() }
            val seriesDeferred = executeAsync(dispatcher) { seriesRemoteDataSource.getFeaturedTrainings().seriesToTrainingPrograms() }
            val routinesDeferred = executeAsync(dispatcher) { routineRemoteDataSource.getFeaturedTrainings().routinesToTrainingPrograms() }
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
                val workoutDeferred = executeAsync(dispatcher) { workoutRemoteDataSource.getTrainingByCategory(id).workoutsToTrainingPrograms() }
                val seriesDeferred = executeAsync(dispatcher) { seriesRemoteDataSource.getTrainingByCategory(id).seriesToTrainingPrograms() }
                val challengesDeferred = executeAsync(dispatcher) { challengeRemoteDataSource.getTrainingByCategory(id).challengesToTrainingPrograms() }
                val routinesDeferred = executeAsync(dispatcher) { routineRemoteDataSource.getTrainingByCategory(id).routinesToTrainingPrograms() }
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
            } catch (ex: AddToFavoritesRemoteException) {
                throw AddFavoriteTrainingException(
                    "An error occurred when adding training to favorites",
                    ex
                )
            }
        }

    @Throws(FetchFavoritesTrainingsByUserException::class)
    override suspend fun getFavoritesTrainingsByProfile(profileId: String): List<ITrainingProgramBO> = safeExecute {
        try {
            favoritesRemoteDataSource.getFavoritesByUser(profileId).parallelMap {
                getTrainingById(
                    id = it.trainingId,
                    type = enumNameOfOrDefault(it.trainingType, TrainingTypeEnum.WORK_OUT)
                )
            }
        } catch (ex: GetFavoritesByUserRemoteException) {
            throw FetchFavoritesTrainingsByUserException(
                "An error occurred when fetching favorites",
                ex
            )
        }
    }

    @Throws(VerifyFavoriteTrainingException::class)
    override suspend fun hasTrainingInFavorites(profileId: String, trainingId: String): Boolean = safeExecute {
        try {
            favoritesRemoteDataSource.hasTrainingInFavorites(
                profileId = profileId,
                trainingId = trainingId
            )
        } catch (ex: HasTrainingInFavoritesRemoteException) {
            throw VerifyFavoriteTrainingException(
                "An error occurred when checking favorites",
                ex
            )
        }
    }

    @Throws(RemoveFavoriteTrainingException::class)
    override suspend fun removeFavoriteTraining(profileId: String, trainingId: String): Boolean = safeExecute {
        try {
            favoritesRemoteDataSource.removeFavorite(
                profileId = profileId,
                trainingId = trainingId
            )
        } catch (ex: RemoveFromFavoritesRemoteException) {
            throw RemoveFavoriteTrainingException(
                "An error occurred when removing training from favorites",
                ex
            )
        }
    }

    private suspend fun WorkoutDTO.toTrainingProgram(): ITrainingProgramBO =
        let { workout -> workout to instructorRemoteDataSource.getInstructorById(workout.instructor) }
            .let(workoutMapper::mapInToOut)

    private suspend fun SeriesDTO.toTrainingProgram(): ITrainingProgramBO =
        let { series -> series to instructorRemoteDataSource.getInstructorById(series.instructor) }
            .let(seriesMapper::mapInToOut)

    private suspend fun ChallengeDTO.toTrainingProgram(): ITrainingProgramBO =
        let { challenge ->
            val instructor = instructorRemoteDataSource.getInstructorById(challenge.instructor)
            val weaklyPlans = challenge.weaklyPlans.parallelMap { weaklyPlan ->
                workoutRemoteDataSource.getTrainingByIdList(weaklyPlan.workouts)
            }.flatten()
            challengesMapper.mapInToOut(Triple(challenge, weaklyPlans, instructor))
        }

    private suspend fun RoutineDTO.toTrainingProgram(): ITrainingProgramBO =
        let { routine -> routine to instructorRemoteDataSource.getInstructorById(routine.instructor) }
            .let(routineMapper::mapInToOut)

    private suspend fun List<WorkoutDTO>.workoutsToTrainingPrograms(): Iterable<ITrainingProgramBO> =
        parallelMap { workout -> workout to instructorRemoteDataSource.getInstructorById(workout.instructor) }
            .let(workoutMapper::mapInListToOutList)

    private suspend fun List<SeriesDTO>.seriesToTrainingPrograms(): Iterable<ITrainingProgramBO> =
        parallelMap { series -> series to instructorRemoteDataSource.getInstructorById(series.instructor) }
            .let(seriesMapper::mapInListToOutList)

    private suspend fun List<ChallengeDTO>.challengesToTrainingPrograms(): Iterable<ITrainingProgramBO> =
        parallelMap { challenge ->
            val instructor = instructorRemoteDataSource.getInstructorById(challenge.instructor)
            val weaklyPlans = challenge.weaklyPlans.parallelMap { weaklyPlan ->
                workoutRemoteDataSource.getTrainingByIdList(weaklyPlan.workouts)
            }.flatten()
            challengesMapper.mapInToOut(Triple(challenge, weaklyPlans, instructor))
        }

    private suspend fun List<RoutineDTO>.routinesToTrainingPrograms(): Iterable<ITrainingProgramBO> =
        parallelMap { series -> series to instructorRemoteDataSource.getInstructorById(series.instructor) }
            .let(routineMapper::mapInListToOutList)
}