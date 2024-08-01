package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.ClassLanguageEnum
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.IntensityEnum
import com.dreamsoftware.fitflextv.domain.model.TrainingFilterDataBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.model.VideoLengthEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import com.dreamsoftware.fitflextv.domain.usecase.core.BaseUseCaseWithParams

class GetTrainingsByTypeUseCase(
    private val trainingRepository: ITrainingRepository
) : BaseUseCaseWithParams<GetTrainingsByTypeUseCase.Params, List<ITrainingProgramBO>>() {
    override suspend fun onExecuted(params: Params): List<ITrainingProgramBO> =
        trainingRepository.getTrainings(params.toTrainingFilterData()).toList()

    private fun Params.toTrainingFilterData() = TrainingFilterDataBO(
        type = type,
        classLanguage = classLanguage,
        workoutType = workoutType,
        intensity = intensity,
        videoLength = videoLength
    )

    data class Params(
        val type: TrainingTypeEnum,
        val classLanguage: ClassLanguageEnum,
        val workoutType: WorkoutTypeEnum,
        val intensity: IntensityEnum,
        val videoLength: VideoLengthEnum
    )
}