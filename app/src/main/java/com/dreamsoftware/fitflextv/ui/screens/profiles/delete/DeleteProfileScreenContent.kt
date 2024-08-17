package com.dreamsoftware.fitflextv.ui.screens.profiles.delete

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.screens.profiles.components.CommonProfileScreenContent
import com.dreamsoftware.fitflextv.ui.theme.Dimens
import com.dreamsoftware.fitflextv.ui.utils.toDrawableResource
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvScalableAvatar
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum

@Composable
fun DeleteProfileScreenContent(
    uiState: DeleteProfileUiState,
    actionListener: DeleteProfileScreenActionListener
) {
    with(uiState) {
        CommonProfileScreenContent(
            isLoading = isLoading,
            error = errorMessage,
            mainTitleRes = R.string.delete_profile_main_title,
            secondaryTitleRes = R.string.delete_profile_main_description,
            primaryOptionTextRes = R.string.delete_profile_form_accept_button_text,
            secondaryOptionTextRes = R.string.delete_profile_form_cancel_button_text,
            onPrimaryOptionPressed = actionListener::onDeletePressed,
            onSecondaryOptionPressed = actionListener::onCancelPressed,
            onErrorAccepted = actionListener::onErrorMessageCleared
        ) {
            FudgeTvFocusRequester {
                FudgeTvScalableAvatar(
                    modifier = Modifier.focusRequester(it),
                    avatarRes = profile?.avatarType?.toDrawableResource(),
                    padding = Dimens.PROFILE_AVATAR_NO_PADDING
                )
            }
            FudgeTvText(
                titleText = profile?.alias.orEmpty(),
                type = FudgeTvTextTypeEnum.TITLE_LARGE,
                textBold = true
            )
            FudgeTvText(
                modifier = Modifier.padding(horizontal = 30.dp),
                titleRes = R.string.delete_profile_explanation_text,
                type = FudgeTvTextTypeEnum.BODY_LARGE,
                textAlign = TextAlign.Center
            )
        }
    }
}