package com.dreamsoftware.fitflextv.ui.screens.home

import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.model.SessionBO
import com.dreamsoftware.fitflextv.domain.model.TrainingBO

data class HomeUiState(
    val contentStatus: ContentStatus = ContentStatus.LOADING,
    val categories: List<CategoryBO> = listOf(),
    val sessionBOS: List<SessionBO> = emptyList(),
    val recommended: List<TrainingBO> = listOf()
) {
    enum class ContentStatus {
        LOADING,
        VISIBLE,
        ERROR
    }
}