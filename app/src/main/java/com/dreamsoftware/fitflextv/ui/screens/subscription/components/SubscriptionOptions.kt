package com.dreamsoftware.fitflextv.ui.screens.subscription.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
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
    ) {
        CommonText(
            modifier = Modifier.padding(bottom = 8.dp),
            titleRes = R.string.our_plans,
            type = CommonTextTypeEnum.LABEL_LARGE,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        subscriptionOptions.forEach {
            SubscriptionOption(
                title = it.formatPeriodTime(it.periodTime, context),
                description = it.formatPeriodTimeAndPrice(it.periodTime, it.price, context),
                price = it.price,
                isSelected = selectedSubscription?.price == it.price,
                onClick = { updateSelectedSubscriptionOption(it) }
            )
        }
    }
}