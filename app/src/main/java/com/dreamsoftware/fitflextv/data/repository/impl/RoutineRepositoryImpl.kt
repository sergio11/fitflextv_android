package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutinesException
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.exception.FetchRoutineByIdException
import com.dreamsoftware.fitflextv.domain.exception.FetchRoutinesException
import com.dreamsoftware.fitflextv.domain.model.RoutineBO
import com.dreamsoftware.fitflextv.domain.repository.IRoutineRepository
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class RoutineRepositoryImpl (
    private val routineRemoteDataSource: IRoutineRemoteDataSource,
    private val routineMapper: IOneSideMapper<RoutineDTO, RoutineBO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), IRoutineRepository {

    @Throws(FetchRoutinesException::class)
    override suspend fun getRoutines(): List<RoutineBO> = safeExecute {
        try {
            routineRemoteDataSource
                .getRoutines()
                .let(routineMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchRemoteRoutinesException) {
            throw FetchRoutinesException("An error occurred when fetching routines", ex)
        }
    }

    @Throws(FetchRoutineByIdException::class)
    override suspend fun getRoutineById(id: String): RoutineBO = safeExecute {
        try {
            routineRemoteDataSource
                .getRoutineById(id)
                .let(routineMapper::mapInToOut)
        } catch (ex: FetchRemoteRoutinesException) {
            throw FetchRoutineByIdException("An error occurred when fetching the routine $id", ex)
        }
    }
}