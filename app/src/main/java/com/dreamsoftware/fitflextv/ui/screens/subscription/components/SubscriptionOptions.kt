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
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.utils.conditional
import com.dreamsoftware.fitflextv.ui.utils.formatPeriodTime
import com.dreamsoftware.fitflextv.ui.utils.formatPeriodTimeAndPrice

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
        CommonText(
            modifier = Modifier.padding(bottom = 8.dp),
            titleRes = R.string.our_plans,
            type = CommonTextTypeEnum.TITLE_MEDIUM,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        CommonFocusRequester { focusRequester ->
            subscriptionOptions.forEachIndexed { index, subscriptionBO ->
                with(subscriptionBO) {
                    SubscriptionOption(
                        modifier = Modifier.conditional(index == 0, ifTrue = {
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