package com.dreamsoftware.fitflextv.ui.screens.profileselector

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Card
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonOutlineButton
import com.dreamsoftware.fitflextv.ui.utils.conditional
import com.dreamsoftware.fitflextv.ui.utils.shadowBox

@Composable
fun ProfileSelectorContent(
    state: ProfileSelectorUiState,
    profileFocusRequester: FocusRequester,
    onSignInClick: () -> Unit,
    onProfileSelectedClick: (idProfile: String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = "Profile Selector" }
    ) {
        Text(
            stringResource(id = R.string.whos_working_out_today),
            style = MaterialTheme.typography.titleLarge,
        )

        TvLazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 44.dp, bottom = 24.dp)
                .height(204.dp)
                .focusRequester(profileFocusRequester),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items(state.profiles) { profile ->
                ItemProfile(
                    state = profile,
                    onClick = onProfileSelectedClick,
                )
            }
        }

        CommonOutlineButton(
            text = stringResource(id = R.string.sign_in_with_a_different_user),
            textStyle = MaterialTheme.typography.labelLarge,
            onClick = onSignInClick
        )
    }
}

@Composable
private fun ItemProfile(
    modifier: Modifier = Modifier,
    state: ProfileVO,
    onClick: (String) -> Unit,
) {
    var isItemProfileFocused by remember { mutableStateOf(false) }
    key(state.id) {
        val scaleAvatar = if (isItemProfileFocused) 160.dp else 120.dp
        val nameStyle =
            if (isItemProfileFocused) MaterialTheme.typography.labelLarge else MaterialTheme.typography.labelMedium

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .conditional(
                        isItemProfileFocused,
                        ifTrue = {
                            shadowBox(
                                color = Color(0x26000000),
                                blurRadius = 10.dp,
                                spreadRadius = 9.dp,
                                offset = DpOffset(0.dp, 6.dp),
                                shape = CircleShape,
                            ).shadowBox(
                                color = Color(0x4D000000),
                                blurRadius = 3.dp,
                                offset = DpOffset(0.dp, 2.dp),
                                shape = CircleShape,
                            )
                        }
                    )
                    .padding(1.dp)
                    .border(
                        width = 3.dp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = if (isItemProfileFocused) 1f else 0f),
                        shape = CircleShape,
                    )
                    .size(scaleAvatar)
                    .clip(CircleShape)
                    .onFocusChanged {
                        isItemProfileFocused = it.hasFocus
                    },
                onClick = {
                    onClick(state.id)
                },
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = state.avatar,
                    contentDescription = stringResource(id = R.string.image, state.name),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = state.name, style = nameStyle
            )
        }
    }
}