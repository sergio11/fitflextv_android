package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.response.InstructorDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchInstructorByIdRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchInstructorsRemoteException

interface IInstructorsRemoteDataSource {

    @Throws(FetchInstructorsRemoteException::class)
    suspend fun getInstructors(): List<InstructorDTO>

    @Throws(FetchInstructorByIdRemoteException::class)
    suspend fun getInstructorById(id: String): InstructorDTO
}