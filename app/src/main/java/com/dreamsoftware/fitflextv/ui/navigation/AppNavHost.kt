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
import com.dreamsoftware.fitflextv.ui.utils.navigateTo
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
        startDestination = Screens.Onboarding(),
        modifier = Modifier
            .semantics {
                testTagsAsResourceId = true
            },
        builder = {
            navigationDrawerGraph(
                onNavigateToRoot = navController::navigateTo
            )
            composable(route = Screens.Onboarding()) {
                with(navController) {
                    OnboardingScreen(
                        onGoToSignIn = {
                            navigate(Screens.SignIn())
                        },
                        onGoToSignUp = {
                            navigate(Screens.SignUp())
                        }
                    )
                }
            }
            composable(route = Screens.SignIn()) {
                with(navController) {
                    SignInScreen(
                        onGoToHome = {
                            navigateTo(Screens.Dashboard)
                        },
                        onGoToProfileSelector = {
                            navigateTo(Screens.ProfileSelector)
                        },
                        onGoToSignUp = {
                            navigate(Screens.SignUp())
                        },
                        onBackPressed = {
                            popBackStack()
                        },
                    )
                }
            }
            composable(route = Screens.SignUp()) {
                with(navController) {
                    SignUpScreen(
                        onBackPressed = {
                            popBackStack()
                        }
                    )
                }
            }
            composable(
                route = Screens.VideoPlayer(),
            ) {
                VideoPlayerScreen {
                    navController.popBackStack()
                }
            }
            composable(
                route = Screens.AudioPlayer(),
            ) {
                AudioPlayerScreen {
                    navController.popBackStack()
                }
            }
            composable(
                route = Screens.ProfileSelector()
            ) {
                with(navController) {
                    ProfileSelectorScreen(
                        onGoToSignIn = {
                            navigate(Screens.Subscription())
                        },
                        onGoToDashboard = {
                            navigateTo(Screens.Dashboard)
                        }
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
                with(navController) {
                    MoreOptionsScreen(
                        onBackPressed = {
                            popBackStack()
                        },
                        onStartClick = {
                            navigate(Screens.AudioPlayer())
                        },
                        onFavouriteClick = {
                            navigate(Screens.Favorite())
                        }
                    )
                }
            }
            composable(
                route = Screens.Favorite()
            ) {
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
            composable(
                route = Screens.Home(),
                arguments = listOf(
                    navArgument("") {
                        type = NavType.StringType
                    }
                )
            ) {
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
                        navController.navigate(Screens.TrainingDetail())
                    }
                )
            }

            composable(
                route = Screens.TrainingDetail(),
                arguments = listOf(
                    navArgument("") {
                        type = NavType.StringType
                    }
                )
            ) {
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
            composable(
                route = Screens.Subscription(),
            ) {
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
    )
}