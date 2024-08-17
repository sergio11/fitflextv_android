package com.dreamsoftware.fitflextv.ui.screens.favorites

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface FavoritesScreenActionListener: IFudgeTvScreenActionListener {

    fun onTrainingProgramSelected(trainingProgram: ITrainingProgramBO)
    fun onTrainingProgramStarted(id: String)
    fun onTrainingProgramRemovedFromFavorites(id: String)
    fun onDismissRequest()
}