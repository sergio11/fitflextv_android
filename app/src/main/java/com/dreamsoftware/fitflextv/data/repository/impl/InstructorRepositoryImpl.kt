package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import kotlinx.coroutines.CoroutineDispatcher

internal class InstructorRepositoryImpl(
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), IInstructorRepository {
    override suspend fun getInstructors(): List<String> {
        return emptyList()
    }

    override suspend fun getInstructorImageById(instructorId: String): String {
        return ""
    }
}