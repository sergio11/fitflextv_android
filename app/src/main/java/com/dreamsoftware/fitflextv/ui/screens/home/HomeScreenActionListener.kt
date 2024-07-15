package com.dreamsoftware.fitflextv.ui.screens.home

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO

interface HomeScreenActionListener {

    fun onOpenTrainingProgram(trainingProgram: ITrainingProgramBO)
    fun onCategorySelected(categoryId: String): Unit
}