package com.dreamsoftware.fitflextv.ui.screens.subscription.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.ui.utils.formatPeriodTime
import com.dreamsoftware.fitflextv.ui.utils.formatPeriodTimeAndPrice
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvItemOption
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum
import com.dreamsoftware.fudge.utils.conditional

@Composable
internal fun SubscriptionOptions(
    modifier: Modifier = Modifier,
    subscriptionOptions: List<SubscriptionBO> = emptyList(),
    selectedSubscription: SubscriptionBO?,
    updateSelectedSubscriptionOption: (SubscriptionBO) -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.width(412.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        FudgeTvText(
            modifier = Modifier.padding(bottom = 8.dp),
            titleRes = R.string.our_plans,
            type = FudgeTvTextTypeEnum.TITLE_MEDIUM,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        val idxSelected = selectedSubscription?.let { subscriptionOptions.indexOf(it) } ?: 0
        FudgeTvFocusRequester { focusRequester ->
            subscriptionOptions.forEachIndexed { index, subscriptionBO ->
                with(subscriptionBO) {
                    FudgeTvItemOption(
                        modifier = Modifier.conditional(index == idxSelected, ifTrue = {
                            focusRequester(focusRequester)
                        }),
                        title = formatPeriodTime(periodTime, context),
                        description = formatPeriodTimeAndPrice(periodTime, price, context),
                        price = price,
                        isSelected = selectedSubscription?.id == id,
                        onClick = { updateSelectedSubscriptionOption(subscriptionBO) }
                    )
                }
            }
        }
    }
}