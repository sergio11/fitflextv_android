package com.dreamsoftware.fitflextv.ui.screens.subscription

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.screens.subscription.components.SubscriptionOptions
import com.dreamsoftware.fudge.component.FudgeTvButton
import com.dreamsoftware.fudge.component.FudgeTvButtonStyleTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvButtonTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvDialog
import com.dreamsoftware.fudge.component.FudgeTvPreviewContent
import com.dreamsoftware.fudge.component.FudgeTvScreenContent

@Composable
fun SubscriptionScreenContent(
    uiState: SubscriptionUiState,
    actionListener: ISubscriptionScreenActionListener
) {
    with(uiState) {
        FudgeTvScreenContent(onErrorAccepted = actionListener::onErrorMessageCleared) {
            FudgeTvDialog(
                isVisible = showSubscriptionAddedDialog,
                mainLogoRes = R.drawable.main_logo,
                titleRes = R.string.add_subscription_completed_dialog_title,
                descriptionRes = R.string.add_subscription_completed_dialog_description,
                onAcceptClicked = actionListener::onCompleted
            )
            FudgeTvPreviewContent(
                imageRes = R.drawable.signup_background,
                defaultImagePlaceholderRes = R.drawable.main_logo,
                mainTitleRes = R.string.subscribe_to_premium,
                secondaryTitleRes = R.string.subscribe_brief,
                onBuildContent = {
                    SubscriptionOptions(
                        subscriptionOptions = subscriptionList,
                        selectedSubscription = selectedSubscription,
                        updateSelectedSubscriptionOption = actionListener::onSubscriptionOptionUpdated
                    )
                },
                onBuildActions = {
                    FudgeTvButton(
                        type = FudgeTvButtonTypeEnum.LARGE,
                        style = FudgeTvButtonStyleTypeEnum.NORMAL,
                        textRes = R.string.subscribe_now,
                        onClick = actionListener::onSubscribe
                    )
                    if(hasActiveSubscription) {
                        Spacer(modifier = Modifier.width(10.dp))
                        FudgeTvButton(
                            type = FudgeTvButtonTypeEnum.LARGE,
                            style = FudgeTvButtonStyleTypeEnum.INVERSE,
                            textRes = R.string.restore_purchases,
                            onClick = actionListener::onRestorePurchases
                        )
                    }
                }
            )
        }
    }
}