package com.dreamsoftware.fitflextv.ui.screens.profiles.delete

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.ScalableAvatar
import com.dreamsoftware.fitflextv.ui.screens.profiles.components.CommonProfileScreenContent
import com.dreamsoftware.fitflextv.ui.theme.Dimens
import com.dreamsoftware.fitflextv.ui.utils.toDrawableResource

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
            onErrorAccepted = actionListener::onErrorAccepted
        ) {
            CommonFocusRequester {
                ScalableAvatar(
                    modifier = Modifier.focusRequester(it),
                    avatarRes = profile?.avatarType?.toDrawableResource(),
                    padding = Dimens.PROFILE_AVATAR_NO_PADDING
                )
            }
            CommonText(
                titleText = profile?.alias.orEmpty(),
                type = CommonTextTypeEnum.TITLE_LARGE,
                textBold = true
            )
            CommonText(
                modifier = Modifier.padding(horizontal = 30.dp),
                titleRes = R.string.delete_profile_explanation_text,
                type = CommonTextTypeEnum.BODY_LARGE,
                textAlign = TextAlign.Center
            )
        }
    }
}