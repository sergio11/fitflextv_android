package com.dreamsoftware.fitflextv.ui.screens.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dreamsoftware.fitflextv.ui.navigation.Screens
import com.dreamsoftware.fitflextv.ui.screens.favorites.FavoritesScreen
import com.dreamsoftware.fitflextv.ui.screens.home.HomeScreen
import com.dreamsoftware.fitflextv.ui.screens.moreoptions.MoreOptionsScreen
import com.dreamsoftware.fitflextv.ui.screens.player.audio.AudioPlayerScreen
import com.dreamsoftware.fitflextv.ui.screens.player.video.VideoPlayerScreen
import com.dreamsoftware.fitflextv.ui.screens.settings.SettingsScreen
import com.dreamsoftware.fitflextv.ui.screens.subscription.SubscriptionScreen
import com.dreamsoftware.fitflextv.ui.screens.training.TrainingScreen
import com.dreamsoftware.fitflextv.ui.screens.trainingdetail.TrainingDetailScreen

@Composable
fun DashboardScreen(
    onBackPressed: () -> Unit,
    navController: NavHostController,
    onNavigateTo: (Screens) -> Unit,
    currentDestination: NavDestination?
) {
    BackPressHandledArea(onBackPressed = onBackPressed) {
        DashboardNavigationDrawer(
            modifier = Modifier,
            onNavigateTo = onNavigateTo,
            currentDestination = currentDestination,
        ) {
            NavHost(
                navController = navController,
                startDestination = Screens.Home(),
            ) {
                composable(Screens.Home()) {
                    HomeScreen(
                        onStartSession = {
                            navController.navigate(Screens.TrainingEntity())
                        },
                        onGoToCategory = {
                            navController.navigate(Screens.MoreOptions())
                        }
                    )
                }
                composable(Screens.Training()) {
                    TrainingScreen(
                        onClickItem = {
                            navController.navigate(Screens.TrainingEntity())
                        }
                    )
                }
                composable(Screens.Favorite()) {
                    FavoritesScreen(
                        onBackPressed = onBackPressed,
                        onStartWorkout = {
                            navController.navigate(Screens.VideoPlayer())
                        }
                    )
                }
                composable(Screens.VideoPlayer()) {
                    VideoPlayerScreen()
                }
                composable(Screens.AudioPlayer()) {
                    AudioPlayerScreen()
                }
                composable(Screens.Settings()) {
                    SettingsScreen()
                }
                composable(Screens.TrainingEntity()) {
                    with(navController) {
                        TrainingDetailScreen(
                            onClickStart = {
                                navigate(Screens.VideoPlayer())
                            },
                            onBackPressed = {
                                popBackStack()
                            }
                        )
                    }
                }
                composable(Screens.MoreOptions()) {
                    with(navController) {
                        MoreOptionsScreen(
                            onStartClick = {
                                navigate(Screens.AudioPlayer())
                            },
                            onBackPressed = onBackPressed,
                            onFavouriteClick = {
                                navigate(Screens.Favorite())
                            }
                        )
                    }
                }
                composable(Screens.Subscription()) {
                    SubscriptionScreen(
                        onSubscribeClick = {
                            navController.navigate(Screens.ProfileSelector())
                        },
                        onRestorePurchasesClick = {
                            navController.navigate(Screens.ProfileSelector())
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun BackPressHandledArea(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) =
    Box(
        modifier = Modifier
            .onPreviewKeyEvent {
                if (it.key == Key.Back && it.type == KeyEventType.KeyUp) {
                    onBackPressed()
                    true
                } else {
                    false
                }
            }
            .then(modifier),
        content = content
    )

