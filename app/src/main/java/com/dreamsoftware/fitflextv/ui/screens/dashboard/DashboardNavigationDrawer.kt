package com.dreamsoftware.fitflextv.ui.screens.dashboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.dreamsoftware.fitflextv.ui.utils.EMPTY

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
    with(MaterialTheme.colorScheme) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            scrimBrush = Brush.horizontalGradient(
                listOf(
                    background,
                    Color.Transparent
                )
            ),
            drawerContent = {
                if(currentDestination?.route != Screen.VideoPlayer.route) {
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .padding(BACKGROUND_CONTENT_PADDING)
                            .background(background)
                            .selectableGroup(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if(drawerState.currentValue == DrawerValue.Open) {
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
                                modifier = Modifier.padding(bottom = BACKGROUND_CONTENT_PADDING, end = 8.dp),
                                selected = selected,
                                onClick = { onItemClicked(item) },
                                content = {
                                    CommonText(
                                        type = CommonTextTypeEnum.TITLE_MEDIUM,
                                        titleRes = item.nameRes,
                                        titleText = item.name,
                                        textColor = if(isFocused) {
                                            inverseOnSurface
                                        } else {
                                            onPrimary
                                        }
                                    )
                                },
                                leadingContent = {
                                    val painterResource = painterResource(id = item.imageRes)
                                    val contentDescription = item.nameRes?.let { stringResource(id = it) } ?: item.name ?: String.EMPTY
                                    val imageSize = Modifier.size(24.dp)
                                    if(item.isIcon) {
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
