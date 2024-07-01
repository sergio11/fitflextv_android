package com.dreamsoftware.fitflextv.presentation.screens.home

import com.dreamsoftware.fitflextv.data.entities.Category
import com.dreamsoftware.fitflextv.data.entities.Session
import com.dreamsoftware.fitflextv.data.entities.Training

data class HomeUiState(
    val contentStatus: ContentStatus = ContentStatus.LOADING,
    val categories: List<Category> = listOf(),
    val sessions: List<Session> = emptyList(),
    val recommended: List<Training> = listOf()
) {
    enum class ContentStatus {
        LOADING,
        VISIBLE,
        ERROR
    }
}