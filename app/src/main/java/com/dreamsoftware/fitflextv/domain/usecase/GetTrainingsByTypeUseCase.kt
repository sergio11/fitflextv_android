package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ClassLanguageEnum
import com.dreamsoftware.fitflextv.domain.model.ClassTypeEnum
import com.dreamsoftware.fitflextv.domain.model.DifficultyEnum
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.model.VideoLengthEnum
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetTrainingsByTypeUseCase(
    private val trainingRepository: ITrainingRepository
) : BaseUseCaseWithParams<GetTrainingsByTypeUseCase.Params, List<ITrainingProgramBO>>() {
    override suspend fun onExecuted(params: Params): List<ITrainingProgramBO> =
        trainingRepository.getTrainings(params.type).toList()

    data class Params(
        val type: TrainingTypeEnum,
        val classLanguage: ClassLanguageEnum,
        val classType: ClassTypeEnum,
        val difficulty: DifficultyEnum,
        val videoLength: VideoLengthEnum
    )
}