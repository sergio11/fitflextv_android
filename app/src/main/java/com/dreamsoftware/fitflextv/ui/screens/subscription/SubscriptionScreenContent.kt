package com.dreamsoftware.fitflextv.ui.screens.subscription

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.common.BrownieTvOutlineButton
import com.dreamsoftware.fitflextv.ui.core.components.CommonFilledButton
import com.dreamsoftware.fitflextv.ui.screens.subscription.components.SubscriptionHeadline
import com.dreamsoftware.fitflextv.ui.screens.subscription.components.SubscriptionOptions

@Composable
fun SubscriptionScreenContent(
    uiState: SubscriptionUiState,
    actionListener: ISubscriptionScreenActionListener
) {
    with(uiState) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .semantics { contentDescription = "Subscription Screen" },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier = Modifier
                    .size(width = 324.dp, height = 452.dp)
                    .border(
                        border = BorderStroke(1.dp, Color.White.copy(0.07f)),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clip(RoundedCornerShape(16.dp)),
                painter = painterResource(id = R.drawable.signup_background),
                contentDescription = "training photo",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(28.dp))
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                SubscriptionHeadline()
                SubscriptionOptions(
                    subscriptionOptions = subscriptionOptions,
                    selectedSubscription = selectedSubscription,
                    updateSelectedSubscriptionOption = actionListener::onSubscriptionOptionUpdated
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CommonFilledButton(
                        modifier = Modifier.width(184.dp),
                        buttonText = stringResource(R.string.subscribe_now),
                        onClick = actionListener::onSubscribe
                    )
                    BrownieTvOutlineButton(
                        modifier = Modifier.width(184.dp),
                        buttonText = stringResource(R.string.restore_purchases),
                        onClick = actionListener::onRestorePurchases
                    )
                }
            }
        }
    }
}