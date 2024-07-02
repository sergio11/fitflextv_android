package com.dreamsoftware.fitflextv.data.repository.instructor

import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import kotlinx.coroutines.flow.Flow

interface InstructorRepository {

    suspend fun getInstructors():List<String>

    suspend fun getInstructorImageById(instructorId: String): String

    fun getSubscriptionOptionsByInstructorId(instructorId: String): Flow<List<SubscriptionBO>>
}