package com.dreamsoftware.fitflextv.ui.screens.favorites

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fudge.core.IFudgeScreenActionListener

interface FavoritesScreenActionListener: IFudgeScreenActionListener {

    fun onTrainingProgramSelected(trainingProgram: ITrainingProgramBO)
    fun onTrainingProgramStarted(id: String)
    fun onTrainingProgramRemovedFromFavorites(id: String)
    fun onDismissRequest()
}