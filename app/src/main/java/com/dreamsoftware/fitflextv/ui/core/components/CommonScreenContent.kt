package com.dreamsoftware.fitflextv.ui.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import kotlinx.coroutines.launch

@Composable
fun CommonScreenContent(
    error: String? = null,
    onErrorAccepted: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    with(MaterialTheme.colorScheme) {
        val snackState = remember { SnackbarHostState() }
        val snackScope = rememberCoroutineScope()

        LaunchedEffect(key1 = error)
        {
            if (!error.isNullOrBlank()) {
                snackScope.launch {
                    snackState.showSnackbar(error)
                    onErrorAccepted()
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content()
            SnackbarHost(
                hostState = snackState,
                modifier = Modifier.align(Alignment.BottomCenter),
                snackbar = {
                    Snackbar(
                        shape = RoundedCornerShape(20.dp),
                        containerColor = errorContainer,
                        contentColor = onError,
                        snackbarData = it
                    )
                }
            )
        }
    }
}