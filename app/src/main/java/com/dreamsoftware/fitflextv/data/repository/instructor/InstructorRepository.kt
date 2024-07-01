package com.dreamsoftware.fitflextv.data.repository.instructor

import com.dreamsoftware.fitflextv.data.entities.Subscription
import kotlinx.coroutines.flow.Flow

interface InstructorRepository {

    suspend fun getInstructors():List<String>

    suspend fun getInstructorImageById(instructorId: String): String

    fun getSubscriptionOptionsByInstructorId(instructorId: String): Flow<List<Subscription>>
}