package com.dreamsoftware.fitflextv.ui.screens.dashboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.NavigationDrawerItemColors
import androidx.tv.material3.rememberDrawerState
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.navigation.Screen
import kotlinx.coroutines.delay

private val CLOSE_DRAWER_WIDTH = 80.dp
private val BACKGROUND_CONTENT_PADDING = 12.dp

data class NavigationDrawerItemModel(
    @StringRes val nameRes: Int? = null,
    val name: String? = null,
    @DrawableRes val imageRes: Int,
    val screen: Screen,
    val isIcon: Boolean = true
)

@Composable
fun DashboardNavigationDrawer(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    items: List<NavigationDrawerItemModel>,
    onItemClicked: (NavigationDrawerItemModel) -> Unit,
    content: @Composable () -> Unit,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var shouldShowDrawer by remember { mutableStateOf(true) }
    var isDrawerOpen by remember { mutableStateOf(false) }
    // Define the routes that should hide the drawer
    val hiddenDrawerRoutes = listOf(Screen.VideoPlayer.route, Screen.AudioPlayer.route)
    // Effect to manage drawer visibility based on currentDestination
    LaunchedEffect(currentDestination) {
        // Set flag to show drawer after a delay
        shouldShowDrawer = false
        delay(800L)
        shouldShowDrawer = true
    }

    // Monitor drawer state changes
    LaunchedEffect(drawerState.currentValue) {
        isDrawerOpen = drawerState.currentValue == DrawerValue.Open
    }

    with(MaterialTheme.colorScheme) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            scrimBrush = Brush.horizontalGradient(
                listOf(
                    background,
                    onPrimary.copy(alpha = 0.3f)
                )
            ),
            drawerContent = {
                if (shouldShowDrawer && currentDestination?.route !in hiddenDrawerRoutes) {
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 8.dp)
                            .background(background)
                            .selectableGroup(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (isDrawerOpen) {
                            Image(
                                painter = painterResource(id = R.drawable.main_logo_inverse),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(120.dp)
                                    .padding(horizontal = 20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        items.forEach { item ->
                            val selected: Boolean =
                                currentDestination?.hierarchy?.any { it.route == item.screen.route } ?: false
                            val interactionSource = remember { MutableInteractionSource() }
                            val isFocused by interactionSource.collectIsFocusedAsState()
                            val isPressed by interactionSource.collectIsPressedAsState()
                            NavigationDrawerItem(
                                colors = NavigationDrawerItemColors(
                                    containerColor = Color.Transparent,
                                    contentColor = onSurfaceVariant,
                                    inactiveContentColor = onSurfaceVariant,
                                    focusedContainerColor = onSurfaceVariant,
                                    focusedContentColor = inverseOnSurface,
                                    selectedContainerColor = primaryContainer,
                                    selectedContentColor = onSurfaceVariant,
                                    disabledContainerColor = onSurfaceVariant,
                                    disabledContentColor = onSurfaceVariant,
                                    disabledInactiveContentColor = onSurfaceVariant,
                                    focusedSelectedContainerColor = onSurfaceVariant,
                                    focusedSelectedContentColor = inverseOnSurface,
                                    pressedContentColor = onPrimary,
                                    pressedContainerColor = primary,
                                    pressedSelectedContainerColor = onSurfaceVariant,
                                    pressedSelectedContentColor = inverseOnSurface,
                                ),
                                interactionSource = interactionSource,
                                modifier = Modifier.padding(bottom = 16.dp, end = 8.dp),
                                selected = selected,
                                onClick = { onItemClicked(item) },
                                content = {
                                    if (isDrawerOpen) {
                                        CommonText(
                                            type = CommonTextTypeEnum.TITLE_MEDIUM,
                                            titleRes = item.nameRes,
                                            titleText = item.name,
                                            textColor = if (isPressed) {
                                                onPrimary
                                            } else if (isFocused) {
                                                inverseOnSurface
                                            } else {
                                                onPrimary
                                            }
                                        )
                                    }
                                },
                                leadingContent = {
                                    val painterResource = painterResource(id = item.imageRes)
                                    val contentDescription = item.nameRes?.let { stringResource(id = it) } ?: item.name ?: ""
                                    val imageSize = Modifier.size(24.dp)
                                    if (item.isIcon) {
                                        Icon(
                                            painter = painterResource,
                                            contentDescription = contentDescription,
                                            modifier = imageSize,
                                        )
                                    } else {
                                        Image(
                                            painter = painterResource,
                                            contentDescription = contentDescription,
                                            modifier = imageSize,
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        ) {
            if (currentDestination?.route !in hiddenDrawerRoutes) {
                Box(modifier = modifier
                    .background(background)
                    .padding(start = CLOSE_DRAWER_WIDTH + BACKGROUND_CONTENT_PADDING),
                    content = { content() }
                )
            } else {
                content()
            }
        }
    }
}
