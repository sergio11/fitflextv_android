package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import com.dreamsoftware.fitflextv.domain.repository.ISessionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class InstructorRepositoryImpl(
    private val sessionRepository: ISessionRepository,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), IInstructorRepository {
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