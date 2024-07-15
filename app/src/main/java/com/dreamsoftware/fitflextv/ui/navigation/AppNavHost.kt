package com.dreamsoftware.fitflextv.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dreamsoftware.fitflextv.ui.screens.onboarding.OnboardingScreen
import com.dreamsoftware.fitflextv.ui.screens.profiles.ProfileSelectorScreen
import com.dreamsoftware.fitflextv.ui.screens.signin.SignInScreen
import com.dreamsoftware.fitflextv.ui.screens.signup.SignUpScreen
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

            composable(route = Screens.ProfileSelector.route) {
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
        }
    )
}