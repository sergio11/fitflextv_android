package com.dreamsoftware.fitflextv.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.lightColorScheme
import com.dreamsoftware.fudge.theme.FudgeTvTheme

private val LightColorScheme = lightColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = primaryContainer,
    onPrimaryContainer = onPrimaryContainer,
    secondary = secondary,
    onSecondary = onSecondary,
    secondaryContainer = secondaryContainer,
    onSecondaryContainer = onSecondaryContainer,
    tertiary = tertiary,
    onTertiary = onTertiary,
    tertiaryContainer = tertiaryContainer,
    onTertiaryContainer = onTertiaryContainer,
    error = error,
    onError = onError,
    errorContainer = errorContainer,
    onErrorContainer = onErrorContainer,
    background = background,
    onBackground = onBackground,
    surface = surface,
    onSurface = onSurface,
    surfaceVariant = surfaceVariant,
    onSurfaceVariant = onSurfaceVariant,
    border = outline,
    inverseOnSurface = inverseOnSurface,
    inverseSurface = inverseSurface,
    inversePrimary = inversePrimary
)

val LocalNavigationProvider = staticCompositionLocalOf<NavHostController> {
    error("No navigation host controller provided.")
}

@Composable
fun FitFlexTVTheme(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
            LocalNavigationProvider provides rememberNavController(),
    ) {
        FudgeTvTheme(
            lightColorScheme = LightColorScheme,
            typography = FitFlexTypography,
            content = content
        )
    }
}