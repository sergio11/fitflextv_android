package com.dreamsoftware.fitflextv.ui.screens.category

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO

interface CategoryDetailActionListener {

    fun onTrainingProgramOpened(trainingProgram: ITrainingProgramBO)
    fun onErrorAccepted()
}