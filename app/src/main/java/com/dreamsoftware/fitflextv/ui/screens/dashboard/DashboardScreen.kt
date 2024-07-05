package com.dreamsoftware.fitflextv.ui.screens.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    navController: NavHostController,
    onNavigateTo: (Screens) -> Unit,
    currentDestination: NavDestination?
) {
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
                with(navController) {
                    HomeScreen(
                        onStartSession = {
                            navigate(Screens.TrainingDetail())
                        },
                        onGoToCategory = {
                            navigate(Screens.MoreOptions())
                        }
                    )
                }
            }
            composable(Screens.Training()) {
                TrainingScreen(
                    onClickItem = {
                        navController.navigate(Screens.TrainingDetail())
                    }
                )
            }
            composable(Screens.Favorite()) {
                with(navController) {
                    FavoritesScreen(
                        onBackPressed = {
                            popBackStack()
                        },
                        onStartWorkout = {
                            navigate(Screens.VideoPlayer())
                        }
                    )
                }
            }
            composable(Screens.VideoPlayer()) {
                VideoPlayerScreen {
                    navController.popBackStack()
                }
            }
            composable(Screens.AudioPlayer()) {
                AudioPlayerScreen {
                    navController.popBackStack()
                }
            }
            composable(Screens.Settings()) {
                SettingsScreen()
            }
            composable(Screens.TrainingDetail()) {
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
                        onBackPressed = {
                            popBackStack()
                        },
                        onFavouriteClick = {
                            navigate(Screens.Favorite())
                        }
                    )
                }
            }
            composable(Screens.Subscription()) {
                with(navController) {
                    SubscriptionScreen(
                        onSubscribeClick = {
                            navigate(Screens.ProfileSelector())
                        },
                        onRestorePurchasesClick = {
                            navigate(Screens.ProfileSelector())
                        }
                    )
                }
            }
        }
    }
}