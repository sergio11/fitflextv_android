package com.dreamsoftware.fitflextv.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyHorizontalGrid
import androidx.tv.foundation.lazy.grid.items
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.TrainingBO
import com.dreamsoftware.fitflextv.ui.core.components.CommonCard
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum

@Composable
internal fun TrainingsRecommended(
    modifier: Modifier = Modifier,
    state: List<TrainingBO>,
    onClick: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        CommonText(
            modifier = Modifier.padding(start = 32.dp),
            type = CommonTextTypeEnum.HEADLINE_MEDIUM,
            titleRes = R.string.recommended_for_you,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        TvLazyHorizontalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .height(370.dp),
            rows = TvGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(horizontal = 32.dp)
        ) {
            items(state) { training ->
                CommonCard(
                    modifier = modifier.width(196.dp),
                    imageUrl = training.imageUrl,
                    title = training.title,
                    timeText = training.time,
                    typeText = training.instructor,
                    onClick = { onClick(training.id) }
                )
            }
        }
    }
}