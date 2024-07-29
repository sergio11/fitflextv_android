package com.dreamsoftware.fitflextv.ui.screens.favorites

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO

interface FavoritesScreenActionListener {

    fun onTrainingProgramSelected(trainingProgram: ITrainingProgramBO)
    fun onTrainingProgramStarted(id: String)
    fun onTrainingProgramRemoved(id: String)
    fun onDismissRequest()
}