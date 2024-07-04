package com.dreamsoftware.fitflextv.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dreamsoftware.fitflextv.ui.screens.Screens
import com.dreamsoftware.fitflextv.ui.screens.favorites.FavoritesScreen
import com.dreamsoftware.fitflextv.ui.screens.home.HomeScreen
import com.dreamsoftware.fitflextv.ui.screens.moreoptions.MoreOptionsScreen
import com.dreamsoftware.fitflextv.ui.screens.player.audio.AudioPlayerScreen
import com.dreamsoftware.fitflextv.ui.screens.player.video.VideoPlayerScreen
import com.dreamsoftware.fitflextv.ui.screens.profileselector.ProfileSelectorScreen
import com.dreamsoftware.fitflextv.ui.screens.subscription.SubscriptionScreen
import com.dreamsoftware.fitflextv.ui.screens.training.TrainingScreen
import com.dreamsoftware.fitflextv.ui.screens.trainingdetail.TrainingDetailScreen
import com.dreamsoftware.fitflextv.ui.utils.navigateTo
import com.dreamsoftware.fitflextv.ui.utils.navigationDrawerGraph

@OptIn(ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun App(
    navController: NavHostController,
    onBackPressed: () -> Unit
) {
    NavHost(
        navController = navController,
        route = "root_host",
        startDestination = Screens.ProfileSelector(),
        modifier = Modifier
            .semantics {
                testTagsAsResourceId = true
            },
        builder = {
            navigationDrawerGraph(
                onNavigateToRoot = navController::navigateTo,
                onBackPressed = onBackPressed
            )
            composable(
                route = Screens.VideoPlayer(),
            ) {
                VideoPlayerScreen()
            }
            composable(
                route = Screens.AudioPlayer(),
            ) {
                AudioPlayerScreen()
            }
            composable(
                route = Screens.ProfileSelector()
            ) {
                with(navController) {
                    ProfileSelectorScreen(
                        onGoToSignIn = { navigate(Screens.Subscription()) },
                        onGoToDashboard = { navigateTo(Screens.Dashboard) }
                    )
                }
            }

            composable(
                route = Screens.MoreOptions(),
                arguments = listOf(
                    navArgument("") {
                        type = NavType.StringType
                    }
                )
            ) {
                MoreOptionsScreen(
                    onBackPressed = onBackPressed,
                    onStartClick = { navController.navigate(Screens.AudioPlayer()) },
                    onFavouriteClick = { navController.navigate(Screens.Favorite()) }
                )
            }
            composable(
                route = Screens.Favorite()
            ) {
                FavoritesScreen(onBackPressed = onBackPressed,
                    onStartWorkout = { navController.navigate(Screens.VideoPlayer()) })
            }
            composable(
                route = Screens.Home(),
                arguments = listOf(
                    navArgument("") {
                        type = NavType.StringType
                    }
                )
            ) {
                HomeScreen(
                    onStartSession = {
                        navController.navigate(Screens.TrainingEntity())
                    },
                    onGoToCategory = {
                        navController.navigate(Screens.MoreOptions())
                    }
                )
            }
            composable(
                route = Screens.Training(),
                arguments = listOf(
                    navArgument("") {
                        type = NavType.StringType
                    }
                )
            ) {
                TrainingScreen(
                    onClickItem = {
                        navController.navigate(Screens.TrainingEntity())
                    }
                )
            }

            composable(
                route = Screens.TrainingEntity(),
                arguments = listOf(
                    navArgument("") {
                        type = NavType.StringType
                    }
                )
            ) {
                TrainingDetailScreen(
                    onClickStart = {
                        navController.navigate(Screens.VideoPlayer())
                    }
                )
            }
            composable(
                route = Screens.Subscription(),
            ) {
                SubscriptionScreen(
                    onSubscribeClick = { navController.navigate(Screens.ProfileSelector()) },
                    onRestorePurchasesClick = { navController.navigate(Screens.ProfileSelector()) }
                )
            }
        }
    )
}