package com.dreamsoftware.fitflextv.data.repository.instructor

import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.data.repository.sessions.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InstructorRepositoryImpl @Inject constructor(
    private val sessionRepository: SessionRepository
):InstructorRepository{
    override suspend fun getInstructors(): List<String> {
        return sessionRepository.getSessions().map { it.instructor }
    }

    override suspend fun getInstructorImageById(instructorId: String): String {
        return sessionRepository.getSessions().find { it.id == instructorId }?.imageUrl ?: ""
    }

    override fun getSubscriptionOptionsByInstructorId(instructorId: String): Flow<List<SubscriptionBO>> {
        return flow {
            emit(
                listOf(
                    SubscriptionBO(
                        periodTime = "1",
                        price = "$7.99",
                    ),
                    SubscriptionBO(
                        periodTime = "3",
                        price = "$19.99",
                    ),
                    SubscriptionBO(
                        periodTime = "12",
                        price = "$79.99",
                    ),
                )
            )
        }
    }
}