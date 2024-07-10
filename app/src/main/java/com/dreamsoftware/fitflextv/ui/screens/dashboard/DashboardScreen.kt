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
        screens = listOf(
            Screens.Home,
            Screens.Training,
            Screens.Favorite,
            Screens.Settings
        ),
        currentDestination = currentDestination,
    ) {
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
        ) {
            composable(Screens.Home.route) {
                with(navController) {
                    HomeScreen(
                        onStartSession = { id ->
                            //navigate(Screens.TrainingDetail.buildRoute(id))
                        },
                        onGoToCategory = { id ->
                            navigate(Screens.MoreOptions.buildRoute(id))
                        }
                    )
                }
            }
            composable(Screens.Training.route) {
                TrainingScreen(
                    onGoToTrainingProgramDetail = { id, type ->
                        navController.navigate(Screens.TrainingDetail.buildRoute(id, type))
                    }
                )
            }
            composable(Screens.Favorite.route) {
                with(navController) {
                    FavoritesScreen(
                        onBackPressed = {
                            popBackStack()
                        },
                        onStartWorkout = {
                            navigate(Screens.VideoPlayer.route)
                        }
                    )
                }
            }
            composable(Screens.VideoPlayer.route) {
                VideoPlayerScreen {
                    navController.popBackStack()
                }
            }
            composable(Screens.AudioPlayer.route) {
                AudioPlayerScreen {
                    navController.popBackStack()
                }
            }
            composable(Screens.Settings.route) {
                SettingsScreen()
            }
            composable(Screens.TrainingDetail.route) { navBackStackEntry ->
                navBackStackEntry.arguments?.let(Screens.TrainingDetail::parseArgs)?.let { args ->
                    with(navController) {
                        TrainingDetailScreen(
                            args = args,
                            onClickStart = {
                                navigate(Screens.VideoPlayer.route)
                            },
                            onBackPressed = {
                                popBackStack()
                            }
                        )
                    }
                }
            }
            composable(Screens.MoreOptions.route) { navBackStackEntry ->
                navBackStackEntry.arguments?.let(Screens.MoreOptions::parseArgs)?.let { args ->
                    with(navController) {
                        MoreOptionsScreen(
                            args = args,
                            onStartClick = {
                                navigate(Screens.AudioPlayer.route)
                            },
                            onBackPressed = {
                                popBackStack()
                            },
                            onFavouriteClick = {
                                navigate(Screens.Favorite.route)
                            }
                        )
                    }
                }
            }
            composable(Screens.Subscription.route) {
                with(navController) {
                    SubscriptionScreen(
                        onSubscribeClick = {
                            navigate(Screens.ProfileSelector.route)
                        },
                        onRestorePurchasesClick = {
                            navigate(Screens.ProfileSelector.route)
                        }
                    )
                }
            }
        }
    }
}