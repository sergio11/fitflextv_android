package com.dreamsoftware.fitflextv.ui.screens.profiles.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.ScalableAvatar
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import com.dreamsoftware.fitflextv.ui.utils.toDrawableResource

@Composable
fun CommonProfileSelector(
    profiles: List<ProfileBO>,
    editMode: Boolean = false,
    onProfileSelected: (ProfileBO) -> Unit
) {
    var selectedAvatar by remember { mutableStateOf("") }
    CommonFocusRequester { requester ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(profiles.size) {
                    val profile = profiles[it]
                    ScalableAvatar(
                        avatarRes = profile.avatarType.toDrawableResource(),
                        editMode = editMode,
                        modifier = Modifier
                            .then(if (it == 0) Modifier.focusRequester(requester) else Modifier)
                            .onFocusChanged {
                                selectedAvatar = profile.alias
                            },
                        onPressed = {
                            onProfileSelected(profile)
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.size(30.dp))
            if (selectedAvatar.isNotEmpty()) {
                ProfileAvatarName(name = selectedAvatar)
            }
        }
    }
}

@Composable
private fun ProfileAvatarName(name: String) {
    AnimatedContent(
        targetState = name,
        transitionSpec = {
            (slideInVertically { height -> height } + fadeIn() togetherWith
                    slideOutVertically { height -> -height } + fadeOut())
                .using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false),
                )
        },
        label = String.EMPTY,
    ) { text ->
        CommonText(
            titleText = text,
            type = CommonTextTypeEnum.HEADLINE_LARGE,
            textBold = true,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
        )
    }
}