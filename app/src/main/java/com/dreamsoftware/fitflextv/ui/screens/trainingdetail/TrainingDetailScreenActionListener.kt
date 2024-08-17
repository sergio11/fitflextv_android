package com.dreamsoftware.fitflextv.ui.screens.trainingdetail

import com.dreamsoftware.fudge.core.IFudgeScreenActionListener

interface TrainingDetailScreenActionListener: IFudgeScreenActionListener {

    fun onTrainingProgramStarted()
    fun onTrainingProgramMoreInfoRequested()
    fun onTrainingFavoriteClicked()
}