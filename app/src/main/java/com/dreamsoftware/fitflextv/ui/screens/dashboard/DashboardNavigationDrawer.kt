package com.dreamsoftware.fitflextv.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.NavigationDrawerItemColors
import androidx.tv.material3.Text
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.navigation.Screens


private val CLOSE_DRAWER_WIDTH = 80.dp
private val BACKGROUND_CONTENT_PADDING = 12.dp

@Composable
fun DashboardNavigationDrawer(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    screens: List<Screens>,
    onNavigateTo: (Screens) -> Unit,
    content: @Composable () -> Unit,
) {
    with(MaterialTheme.colorScheme) {
        ModalNavigationDrawer(
            scrimBrush = Brush.horizontalGradient(
                listOf(
                    MaterialTheme.colorScheme.background,
                    Color.Transparent
                )
            ),
            drawerContent = {
                if(currentDestination?.route != Screens.VideoPlayer.route) {
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .padding(BACKGROUND_CONTENT_PADDING)
                            .selectableGroup(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        screens.forEachIndexed { index, screen ->
                            val selected: Boolean =
                                currentDestination?.hierarchy?.any { it.route == screen.route } ?: false
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
                                modifier = Modifier.padding(bottom = BACKGROUND_CONTENT_PADDING),
                                selected = selected,
                                onClick = { onNavigateTo(screen) },
                                content = { Text(screens[index].name ) },
                                leadingContent = {
                                    Icon(
                                        painter = painterResource(
                                            id = screen.navigationDrawerIcon ?: R.drawable.home
                                        ),
                                        contentDescription = screen.name,
                                        modifier = Modifier.size(24.dp),
                                    )
                                }
                            )
                        }
                    }
                }
            },
            modifier = Modifier
        ) {
            if(currentDestination?.route != Screens.VideoPlayer.route) {
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
