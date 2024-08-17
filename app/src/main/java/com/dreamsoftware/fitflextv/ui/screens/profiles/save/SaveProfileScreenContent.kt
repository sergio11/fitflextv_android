package com.dreamsoftware.fitflextv.ui.screens.profiles.save

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.AvatarTypeEnum
import com.dreamsoftware.fitflextv.ui.screens.profiles.components.CommonProfileScreenContent
import com.dreamsoftware.fitflextv.ui.theme.Dimens
import com.dreamsoftware.fitflextv.ui.utils.toDrawableResource
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvScalableAvatar
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextField
import com.dreamsoftware.fudge.component.FudgeTvTextFieldTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum

@Composable
fun SaveProfileScreenContent(
    uiState: SaveProfileUiState,
    actionListener: SaveProfileScreenActionListener
) {
    with(uiState) {
        CommonProfileScreenContent(
            isLoading = isLoading,
            error = errorMessage,
            onErrorAccepted = actionListener::onErrorMessageCleared,
            mainTitleRes = if(isEditMode) {
                R.string.edit_profile_main_title
            } else {
                R.string.create_profile_main_title
            },
            secondaryTitleRes = if(isEditMode) {
                R.string.edit_profile_secondary_title
            } else {
                R.string.create_profile_secondary_title
            },
            primaryOptionTextRes = R.string.save_profile_confirm_button_text,
            secondaryOptionTextRes = R.string.save_profile_cancel_button_text,
            tertiaryOptionTextRes = if(isEditMode) {
                R.string.save_profile_change_secure_pin_button_text
            } else {
                null
            },
            onPrimaryOptionPressed = actionListener::onSaveProfilePressed,
            onSecondaryOptionPressed = actionListener::onCancelPressed,
            onTertiaryOptionPressed = actionListener::onAdvanceConfigurationPressed,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if(isEditMode) {
                    EditProfile(
                        uiState = uiState,
                        onAliasChanged = actionListener::onAliasChanged,
                        onPinChanged = actionListener::onPinChanged,
                        onAvatarTypeChanged = actionListener::onAvatarTypeChanged,
                    )
                } else {
                    CreateNewProfile(
                        uiState = uiState,
                        onAliasChanged = actionListener::onAliasChanged,
                        onPinChanged = actionListener::onPinChanged,
                        onAvatarTypeChanged = actionListener::onAvatarTypeChanged,
                    )
                }
            }
        }
    }
}

@Composable
private fun CreateNewProfile(
    uiState: SaveProfileUiState,
    onAliasChanged: (String) -> Unit,
    onPinChanged: (String) -> Unit,
    onAvatarTypeChanged: (AvatarTypeEnum) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ProfileAvatarSelected(
            profileType = uiState.avatarType
        )
        ProfileSelector(
            modifier = Modifier
                .weight(0.25f),
            onProfileTypeChanged = onAvatarTypeChanged
        )
    }
    Spacer(modifier = Modifier.width(30.dp))
    SaveProfileFormContent(
        uiState = uiState,
        onAliasChanged = onAliasChanged,
        onPinChanged = onPinChanged
    )
}

@Composable
private fun EditProfile(
    uiState: SaveProfileUiState,
    onAliasChanged: (String) -> Unit,
    onPinChanged: (String) -> Unit,
    onAvatarTypeChanged: (AvatarTypeEnum) -> Unit,
) {
    ProfileAvatarSelected(
        profileType = uiState.avatarType
    )
    Spacer(modifier = Modifier.width(30.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        SaveProfileFormContent(
            modifier = Modifier.weight(0.4f),
            uiState = uiState,
            onAliasChanged = onAliasChanged,
            onPinChanged = onPinChanged
        )
        ProfileSelector(
            modifier = Modifier
                .weight(0.3f),
            onProfileTypeChanged = onAvatarTypeChanged
        )
    }
}

@Composable
private fun ProfileAvatarSelected(
    profileType: AvatarTypeEnum?
) {
    Column(
        modifier = Modifier
            .width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FudgeTvScalableAvatar(
            avatarRes = profileType?.toDrawableResource(),
            padding = Dimens.PROFILE_AVATAR_NO_PADDING,
            focusedScale = Dimens.SAVE_PROFILE_AVATAR_SELECTED_FOCUSED_SCALE
        )
        Spacer(modifier = Modifier.height(10.dp))
        FudgeTvText(
            titleRes = R.string.save_profile_form_avatar_label_text,
            type = FudgeTvTextTypeEnum.BODY_MEDIUM,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SaveProfileFormContent(
    modifier: Modifier = Modifier,
    uiState: SaveProfileUiState,
    onAliasChanged: (String) -> Unit,
    onPinChanged: (String) -> Unit
) {
    with(uiState) {
        FudgeTvFocusRequester { requester ->
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                FudgeTvTextField(
                    modifier = Modifier.focusRequester(requester),
                    icon = Icons.Filled.Person,
                    type = FudgeTvTextFieldTypeEnum.TEXT,
                    value = alias,
                    errorMessage = aliasError,
                    labelRes = R.string.save_profile_form_alias_label_text,
                    onValueChange = onAliasChanged
                )
                if(!isEditMode) {
                    Spacer(modifier = Modifier.height(20.dp))
                    FudgeTvTextField(
                        icon = Icons.Filled.Key,
                        value = securePin,
                        errorMessage = securePinError,
                        type = FudgeTvTextFieldTypeEnum.NUMBER_SECRET,
                        labelRes = R.string.save_profile_form_pin_label_text,
                        onValueChange = onPinChanged
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileSelector(
    modifier: Modifier,
    onProfileTypeChanged: (AvatarTypeEnum) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        AvatarTypeEnum.entries.forEach {
            FudgeTvScalableAvatar(
                avatarRes = it.toDrawableResource(),
                focusedScale = Dimens.SAVE_PROFILE_AVATAR_FOCUSED_SCALE,
                size = Dimens.SAVE_PROFILE_AVATAR_SIZE,
                padding = Dimens.PROFILE_AVATAR_NO_PADDING,
                onPressed = {
                    onProfileTypeChanged(it)
                }
            )
            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}