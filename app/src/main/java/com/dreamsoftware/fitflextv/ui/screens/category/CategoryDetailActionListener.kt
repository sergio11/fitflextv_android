package com.dreamsoftware.fitflextv.ui.screens.category

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface CategoryDetailActionListener: IFudgeTvScreenActionListener {

    fun onTrainingProgramOpened(trainingProgram: ITrainingProgramBO)
}