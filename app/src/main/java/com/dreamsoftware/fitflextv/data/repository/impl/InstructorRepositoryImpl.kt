package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IInstructorsRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.response.InstructorDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchInstructorByIdRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchInstructorsRemoteException
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.exception.FetchInstructorByIdException
import com.dreamsoftware.fitflextv.domain.exception.FetchInstructorsException
import com.dreamsoftware.fitflextv.domain.model.InstructorBO
import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class InstructorRepositoryImpl(
    private val instructorsRemoteDataSource: IInstructorsRemoteDataSource,
    private val instructorMapper: IOneSideMapper<InstructorDTO, InstructorBO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), IInstructorRepository {

    @Throws(FetchInstructorsException::class)
    override suspend fun getInstructors(): List<InstructorBO> = safeExecute {
        try {
            instructorsRemoteDataSource
                .getInstructors()
                .let(instructorMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchInstructorsRemoteException) {
            throw FetchInstructorsException("An error occurred when fetching instructors", ex)
        }
    }

    @Throws(FetchInstructorByIdException::class)
    override suspend fun getInstructorById(id: String): InstructorBO = safeExecute {
        try {
            instructorsRemoteDataSource
                .getInstructorById(id)
                .let(instructorMapper::mapInToOut)
        } catch (ex: FetchInstructorByIdRemoteException) {
            throw FetchInstructorByIdException("An error occurred when fetching the instructor by id: $id", ex)
        }
    }
}