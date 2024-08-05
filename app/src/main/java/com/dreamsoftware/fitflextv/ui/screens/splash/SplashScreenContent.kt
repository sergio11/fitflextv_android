package com.dreamsoftware.fitflextv.ui.screens.splash

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum

@Composable
fun SplashScreenContent(
    modifier: Modifier,
    uiState: SplashUiState
) {
    with(MaterialTheme.colorScheme) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(surface),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SplashLogo()
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CommonText(
                    type = CommonTextTypeEnum.TITLE_LARGE,
                    titleRes = R.string.splash_main_title_text,
                    textColor = onSurface,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                CommonText(
                    type = CommonTextTypeEnum.TITLE_MEDIUM,
                    titleRes = R.string.splash_description_text,
                    textColor = onSurface,
                    textAlign = TextAlign.Center
                )
            }
            CircularProgressIndicator(
                modifier = Modifier
                    .size(60.dp)
                    .padding(8.dp),
                color = onPrimary
            )
        }
    }
}

@Composable
private fun SplashLogo() {
    val heightState by animateDpAsState(
        targetValue = 190.dp,
        animationSpec = tween(1500, easing = LinearOutSlowInEasing),
        label = "Splash Logo Anim"
    )
    Image(
        painter = painterResource(id = R.drawable.main_logo_inverse),
        contentDescription = null,
        modifier = Modifier
            .height(heightState)
            .animateContentSize()
    )
}