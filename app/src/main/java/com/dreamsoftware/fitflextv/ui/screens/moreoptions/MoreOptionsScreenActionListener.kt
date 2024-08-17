package com.dreamsoftware.fitflextv.ui.screens.moreoptions

import com.dreamsoftware.fudge.core.IFudgeScreenActionListener

interface MoreOptionsScreenActionListener: IFudgeScreenActionListener {

    fun onBackPressed()
    fun onTrainingProgramOpened()
    fun onFavouriteClicked()
    fun onOpenInstructorDetail()
    fun onPlayTrainingSong()
}