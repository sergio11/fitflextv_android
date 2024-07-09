package com.dreamsoftware.fitflextv.ui.navigation

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
import com.dreamsoftware.fitflextv.ui.screens.favorites.FavoritesScreen
import com.dreamsoftware.fitflextv.ui.screens.home.HomeScreen
import com.dreamsoftware.fitflextv.ui.screens.moreoptions.MoreOptionsScreen
import com.dreamsoftware.fitflextv.ui.screens.onboarding.OnboardingScreen
import com.dreamsoftware.fitflextv.ui.screens.player.audio.AudioPlayerScreen
import com.dreamsoftware.fitflextv.ui.screens.player.video.VideoPlayerScreen
import com.dreamsoftware.fitflextv.ui.screens.profileselector.ProfileSelectorScreen
import com.dreamsoftware.fitflextv.ui.screens.signin.SignInScreen
import com.dreamsoftware.fitflextv.ui.screens.signup.SignUpScreen
import com.dreamsoftware.fitflextv.ui.screens.subscription.SubscriptionScreen
import com.dreamsoftware.fitflextv.ui.screens.training.TrainingScreen
import com.dreamsoftware.fitflextv.ui.screens.trainingdetail.TrainingDetailScreen
import com.dreamsoftware.fitflextv.ui.utils.navigateSingleTopTo
import com.dreamsoftware.fitflextv.ui.utils.navigationDrawerGraph

@OptIn(ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = "root_host",
        startDestination = Screens.Onboarding.route,
        modifier = Modifier
            .semantics {
                testTagsAsResourceId = true
            },
        builder = {
            navigationDrawerGraph(
                onNavigateToRoot = {
                    navController.navigateSingleTopTo(it.route)
                }
            )
            composable(route = Screens.Onboarding.route) {
                with(navController) {
                    OnboardingScreen(
                        onGoToSignIn = {
                            navigate(Screens.SignIn.route)
                        },
                        onGoToSignUp = {
                            navigate(Screens.SignUp.route)
                        }
                    )
                }
            }
            composable(route = Screens.SignIn.route) {
                with(navController) {
                    SignInScreen(
                        onGoToHome = {
                            navigateSingleTopTo(Screens.Dashboard.route)
                        },
                        onGoToProfileSelector = {
                            navigateSingleTopTo(Screens.ProfileSelector.route)
                        },
                        onGoToSignUp = {
                            navigate(Screens.SignUp.route)
                        },
                        onBackPressed = {
                            popBackStack()
                        },
                    )
                }
            }
            composable(route = Screens.SignUp.route) {
                with(navController) {
                    SignUpScreen(
                        onBackPressed = {
                            popBackStack()
                        }
                    )
                }
            }
            composable(
                route = Screens.VideoPlayer.route,
            ) {
                VideoPlayerScreen {
                    navController.popBackStack()
                }
            }
            composable(
                route = Screens.AudioPlayer.route,
            ) {
                AudioPlayerScreen {
                    navController.popBackStack()
                }
            }
            composable(
                route = Screens.ProfileSelector.route
            ) {
                with(navController) {
                    ProfileSelectorScreen(
                        onGoToSignIn = {
                            navigate(Screens.Subscription.route)
                        },
                        onGoToDashboard = {
                            navigateSingleTopTo(Screens.Dashboard.route)
                        }
                    )
                }
            }

            composable(
                route = Screens.MoreOptions.route,
                arguments = listOf(
                    navArgument("") {
                        type = NavType.StringType
                    }
                )
            ) { navBackStackEntry ->
                navBackStackEntry.arguments?.let(Screens.MoreOptions::parseArgs)?.let { args ->
                    with(navController) {
                        MoreOptionsScreen(
                            args = args,
                            onBackPressed = {
                                popBackStack()
                            },
                            onStartClick = {
                                navigate(Screens.AudioPlayer.route)
                            },
                            onFavouriteClick = {
                                navigate(Screens.Favorite.route)
                            }
                        )
                    }
                }
            }
            composable(
                route = Screens.Favorite.route
            ) {
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
            composable(
                route = Screens.Home.route,
                arguments = listOf(
                    navArgument("") {
                        type = NavType.StringType
                    }
                )
            ) {
                with(navController) {
                    HomeScreen(
                        onStartSession = { id ->
                            navigate(Screens.TrainingDetail.buildRoute(id))
                        },
                        onGoToCategory = { categoryId ->
                            navigate(Screens.MoreOptions.buildRoute(categoryId))
                        }
                    )
                }
            }
            composable(
                route = Screens.Training.route,
                arguments = listOf(
                    navArgument("") {
                        type = NavType.StringType
                    }
                )
            ) {
                TrainingScreen(
                    onGoToTrainingDetail = { id ->
                        navController.navigate(Screens.TrainingDetail.buildRoute(id))
                    }
                )
            }

            composable(
                route = Screens.TrainingDetail.route,
                arguments = listOf(
                    navArgument("") {
                        type = NavType.StringType
                    }
                )
            ) { navBackStackEntry ->
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
            composable(
                route = Screens.Subscription.route,
            ) {
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
    )
}