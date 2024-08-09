package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.exception.FetchInstructorByIdException
import com.dreamsoftware.fitflextv.domain.exception.FetchInstructorsException
import com.dreamsoftware.fitflextv.domain.model.InstructorBO

interface IInstructorRepository {

    @Throws(FetchInstructorsException::class)
    suspend fun getInstructors(): List<InstructorBO>

    @Throws(FetchInstructorByIdException::class)
    suspend fun getInstructorById(id: String): InstructorBO
}