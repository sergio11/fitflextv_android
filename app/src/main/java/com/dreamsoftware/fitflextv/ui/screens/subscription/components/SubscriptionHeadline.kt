package com.dreamsoftware.fitflextv.ui.screens.subscription.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.theme.FitFlexTVTheme
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum

@Composable
internal fun SubscriptionHeadline() {
    with(MaterialTheme.colorScheme) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FudgeTvText(
                type = FudgeTvTextTypeEnum.TITLE_LARGE,
                textColor = onSurface,
                titleRes = R.string.subscribe_to_premium
            )
            FudgeTvText(
                titleRes = R.string.subscribe_brief,
                type = FudgeTvTextTypeEnum.BODY_SMALL,
                textColor = onSurface
            )
        }
    }
}

@Preview
@Composable
private fun SubscriptionHeadlinePreview() {
    FitFlexTVTheme {
        SubscriptionHeadline()
    }
}