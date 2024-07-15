package com.dreamsoftware.fitflextv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dreamsoftware.fitflextv.ui.screens.category.CategoryDetailScreen
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
fun DashboardNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
    ) {
        composable(Screens.Home.route) {
            with(navController) {
                HomeScreen(
                    onOpenTrainingCategory = { id ->
                        navigate(Screens.CategoryDetail.buildRoute(id))
                    },
                    onOpenTrainingProgram = {  id, type ->
                        navigate(Screens.TrainingDetail.buildRoute(id, type))
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

        composable(Screens.CategoryDetail.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screens.CategoryDetail::parseArgs)?.let { args ->
                with(navController) {
                    CategoryDetailScreen(
                        args = args,
                        onOpenTrainingProgramDetail = {id, type ->
                            navigate(Screens.TrainingDetail.buildRoute(id, type))
                        },
                        onBackPressed = {
                            popBackStack()
                        }
                    )
                }
            }
        }

        composable(Screens.VideoPlayer.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screens.VideoPlayer::parseArgs)?.let { args ->
                with(navController) {
                    VideoPlayerScreen(args = args) {
                        popBackStack()
                    }
                }
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
                        onPlayingTrainingProgram = { id, type ->
                            navigate(Screens.VideoPlayer.buildRoute(id, type))
                        },
                        onOpenMoreDetails = { id, type ->
                            navigate(Screens.MoreOptions.buildRoute(id, type))
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