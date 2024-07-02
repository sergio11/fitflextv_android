package com.dreamsoftware.fitflextv.ui.screens.subscription.composable

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO

@Composable
internal fun SubscriptionOptions(
    modifier: Modifier = Modifier,
    subscriptionBOOptions: List<SubscriptionBO> = emptyList(),
    formatPeriodTime: (String, Context) -> String,
    formatPeriodTimeAndPrice: (String, String, Context) -> String,
    selectedSubscriptionBO: SubscriptionBO,
    updateSelectedSubscriptionOption: (SubscriptionBO) -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.width(412.dp),
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(R.string.our_plans),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        subscriptionBOOptions.forEach {
            SubscriptionOption(
                title = formatPeriodTime(it.periodTime, context),
                description = formatPeriodTimeAndPrice(it.periodTime, it.price, context),
                price = it.price,
                isSelected = selectedSubscriptionBO.price == it.price,
                onClick = { updateSelectedSubscriptionOption(it) }
            )
        }
    }
}