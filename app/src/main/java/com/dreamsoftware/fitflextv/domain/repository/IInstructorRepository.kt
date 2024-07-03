package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import kotlinx.coroutines.flow.Flow

interface IInstructorRepository {

    suspend fun getInstructors():List<String>

    suspend fun getInstructorImageById(instructorId: String): String

    fun getSubscriptionOptionsByInstructorId(instructorId: String): Flow<List<SubscriptionBO>>
}