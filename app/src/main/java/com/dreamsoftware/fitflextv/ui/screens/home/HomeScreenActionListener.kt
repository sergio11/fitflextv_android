package com.dreamsoftware.fitflextv.ui.screens.home

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface HomeScreenActionListener: IFudgeTvScreenActionListener {

    fun onOpenTrainingProgram(trainingProgram: ITrainingProgramBO)
    fun onCategorySelected(categoryId: String)
}