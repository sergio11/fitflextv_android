package com.dreamsoftware.fitflextv.ui.screens.subscription.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.RadioButton
import androidx.tv.material3.RadioButtonDefaults
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.theme.FitFlexTVTheme
import com.dreamsoftware.fitflextv.ui.utils.EMPTY

@Composable
internal fun SubscriptionOption(
    modifier: Modifier = Modifier,
    title: String = String.EMPTY,
    description: String = String.EMPTY,
    price: String = String.EMPTY,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    with(MaterialTheme.colorScheme){
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
        ) {
            CommonText(
                type = CommonTextTypeEnum.TITLE_MEDIUM,
                titleText = title,
                textColor = onSurface
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CommonText(
                    titleText = description,
                    type = CommonTextTypeEnum.BODY_SMALL,
                    textColor = onSurface.copy(alpha = 0.8f)
                )
                CommonText(
                    titleText = price,
                    type = CommonTextTypeEnum.LABEL_LARGE,
                    textColor = onSurface
                )
                RadioButton(
                    modifier = Modifier.size(24.dp),
                    selected = isSelected,
                    onClick = { onClick() },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = secondary,
                        unselectedColor = border
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun SelectedSubscriptionOptionPreview() {
    FitFlexTVTheme {
        SubscriptionOption(
            title = "1 Month Subscription",
            description = "Start your 7-day free trial then \$7.99 / month.\nSubscription continues until cancelled",
            price = "$7.99",
            isSelected = true,
        )
    }
}

@Preview
@Composable
private fun UnSelectedSubscriptionOptionPreview() {
    FitFlexTVTheme {
        SubscriptionOption(
            title = "1 Month Subscription",
            description = "Start your 7-day free trial then \$7.99 / month.\nSubscription continues until cancelled",
            price = "$7.99",
            isSelected = false,
        )
    }
}