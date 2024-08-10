package com.dreamsoftware.fitflextv.ui.core.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import kotlinx.coroutines.delay

private const val DEFAULT_REQUEST_FOCUS_AT_IN_MILLIS = 1000L

@Composable
fun CommonFocusRequester(
    key: Any = Unit,
    requestFocusAtInMillis: Long = DEFAULT_REQUEST_FOCUS_AT_IN_MILLIS,
    shouldRequestFocus: () -> Boolean = { true },
    content: @Composable (FocusRequester) -> Unit
) {
    val requester = remember { FocusRequester() }
    if(shouldRequestFocus()) {
        LaunchedEffect(key) {
            delay(requestFocusAtInMillis)
            runCatching { requester.requestFocus() }
        }
    }
    content(requester)
}