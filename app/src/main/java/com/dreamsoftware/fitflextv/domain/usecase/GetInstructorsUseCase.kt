package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.InstructorBO
import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import com.dreamsoftware.fudge.core.FudgeUseCase

class GetInstructorsUseCase(
    private val instructorRepository: IInstructorRepository
) : FudgeUseCase<List<InstructorBO>>() {
    override suspend fun onExecuted():  List<InstructorBO> =
        instructorRepository.getInstructors()
}