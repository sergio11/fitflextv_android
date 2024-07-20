package com.dreamsoftware.fitflextv.ui.screens.dashboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.NavigationDrawerItemColors
import androidx.tv.material3.Text
import com.dreamsoftware.fitflextv.ui.navigation.Screen


private val CLOSE_DRAWER_WIDTH = 80.dp
private val BACKGROUND_CONTENT_PADDING = 12.dp

data class NavigationDrawerItemModel(
    @StringRes val nameRes: Int,
    @DrawableRes val iconRes: Int,
    val screen: Screen
)

@Composable
fun DashboardNavigationDrawer(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    items: List<NavigationDrawerItemModel>,
    onItemClicked: (NavigationDrawerItemModel) -> Unit,
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
                if(currentDestination?.route != Screen.VideoPlayer.route) {
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .padding(BACKGROUND_CONTENT_PADDING)
                            .selectableGroup(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        items.forEach { item ->
                            val selected: Boolean =
                                currentDestination?.hierarchy?.any { it.route == item.screen.route } ?: false
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
                                onClick = { onItemClicked(item) },
                                content = { Text(stringResource(id = item.nameRes) ) },
                                leadingContent = {
                                    Icon(
                                        painter = painterResource(
                                            id = item.iconRes
                                        ),
                                        contentDescription = stringResource(id = item.nameRes),
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
            if(currentDestination?.route != Screen.VideoPlayer.route) {
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
