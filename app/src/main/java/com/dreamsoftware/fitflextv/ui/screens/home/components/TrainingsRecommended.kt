package com.dreamsoftware.fitflextv.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fudge.component.FudgeTvCard
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum

@Composable
internal fun TrainingsRecommended(
    modifier: Modifier = Modifier,
    state: List<ITrainingProgramBO>,
    onClick: (ITrainingProgramBO) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        FudgeTvText(
            modifier = Modifier.padding(start = 32.dp),
            type = FudgeTvTextTypeEnum.HEADLINE_MEDIUM,
            titleRes = R.string.recommended_for_you,
            textColor = MaterialTheme.colorScheme.onSurface,
            textBold = true
        )
        LazyHorizontalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .height(370.dp),
            rows = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(horizontal = 32.dp)
        ) {
            items(state) { training ->
                FudgeTvCard(
                    modifier = modifier.width(196.dp),
                    imageUrl = training.imageUrl,
                    title = training.name,
                    subtitle = "${training.duration} | ${training.instructorName}",
                    onClick = { onClick(training) }
                )
            }
        }
    }
}