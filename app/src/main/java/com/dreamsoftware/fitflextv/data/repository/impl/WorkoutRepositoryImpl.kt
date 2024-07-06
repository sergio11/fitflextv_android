package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IWorkoutRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.WorkoutDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteWorkoutsException
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.workout.DummyWorkoutData
import com.dreamsoftware.fitflextv.domain.exception.FetchWorkoutByIdException
import com.dreamsoftware.fitflextv.domain.exception.FetchWorkoutsException
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.repository.IWorkoutRepository
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class WorkoutRepositoryImpl(
    private val workoutRemoteDataSource: IWorkoutRemoteDataSource,
    private val workoutsMapper: IOneSideMapper<WorkoutDTO, WorkoutBO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), IWorkoutRepository {

    private val dummyWorkoutData: DummyWorkoutData = DummyWorkoutData()

    @Throws(FetchWorkoutsException::class)
    override suspend fun getWorkouts(): List<WorkoutBO> = safeExecute {
        try {
            workoutRemoteDataSource
                .getWorkouts()
                .let(workoutsMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchRemoteWorkoutsException) {
            throw FetchWorkoutsException("An error occurred when fetching workouts", ex)
        }
    }

    @Throws(FetchWorkoutByIdException::class)
    override suspend fun getWorkoutById(id: String): WorkoutBO = safeExecute {
        try {
            workoutRemoteDataSource
                .getWorkoutById(id)
                .let(workoutsMapper::mapInToOut)
        } catch (ex: FetchRemoteWorkoutsException) {
            throw FetchWorkoutsException("An error occurred when fetching the workout $id", ex)
        }
    }

    override suspend fun getFavoritesWorkouts() = dummyWorkoutData.list


}