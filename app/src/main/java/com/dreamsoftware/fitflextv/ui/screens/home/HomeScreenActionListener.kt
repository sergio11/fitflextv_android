package com.dreamsoftware.fitflextv.ui.screens.home

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fudge.core.IFudgeScreenActionListener

interface HomeScreenActionListener: IFudgeScreenActionListener {

    fun onOpenTrainingProgram(trainingProgram: ITrainingProgramBO)
    fun onCategorySelected(categoryId: String)
}