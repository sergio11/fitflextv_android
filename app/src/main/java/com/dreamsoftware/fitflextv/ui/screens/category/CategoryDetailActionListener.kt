package com.dreamsoftware.fitflextv.ui.screens.category

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fudge.core.IFudgeScreenActionListener

interface CategoryDetailActionListener: IFudgeScreenActionListener {

    fun onTrainingProgramOpened(trainingProgram: ITrainingProgramBO)
}