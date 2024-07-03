package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCase

class GetInstructorsUseCase(
    private val instructorRepository: IInstructorRepository
) : BaseUseCase<List<String>>() {
    override suspend fun onExecuted():  List<String> =
        instructorRepository.getInstructors()
}