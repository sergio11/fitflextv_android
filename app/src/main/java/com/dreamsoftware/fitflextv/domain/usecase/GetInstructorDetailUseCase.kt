package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.InstructorBO
import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetInstructorDetailUseCase(
    private val instructorRepository: IInstructorRepository
): BaseUseCaseWithParams<GetInstructorDetailUseCase.Params, InstructorBO>() {

    override suspend fun onExecuted(params: Params): InstructorBO =
        instructorRepository.getInstructorById(params.id)

    data class Params(
        val id: String
    )
}