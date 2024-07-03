package com.dreamsoftware.fitflextv.ui.screens.training.trainingentities

sealed class TrainingEntityPages {
    data object EntityDetails : TrainingEntityPages()
    data object EntityTabs : TrainingEntityPages()
}