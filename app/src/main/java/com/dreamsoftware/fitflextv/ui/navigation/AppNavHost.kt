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
import com.dreamsoftware.fitflextv.ui.screens.profiles.ProfilesScreen
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
        startDestination = Screen.Onboarding.route,
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
            composable(route = Screen.Onboarding.route) {
                with(navController) {
                    OnboardingScreen(
                        onGoToSignIn = {
                            navigate(Screen.SignIn.route)
                        },
                        onGoToSignUp = {
                            navigate(Screen.SignUp.route)
                        }
                    )
                }
            }
            composable(route = Screen.SignIn.route) {
                with(navController) {
                    SignInScreen(
                        onGoToHome = {
                            navigateSingleTopTo(Screen.Dashboard.route)
                        },
                        onGoToProfiles = {
                            navigateSingleTopTo(Screen.Profiles.route)
                        },
                        onGoToSignUp = {
                            navigate(Screen.SignUp.route)
                        },
                        onBackPressed = {
                            popBackStack()
                        },
                    )
                }
            }
            composable(route = Screen.SignUp.route) {
                with(navController) {
                    SignUpScreen(
                        onBackPressed = {
                            popBackStack()
                        }
                    )
                }
            }

            composable(route = Screen.Profiles.route) {
                ProfilesScreen(
                    onGoToHome = {
                        navController.navigateSingleTopTo(Screen.Dashboard.route)
                    }
                )
            }
        }
    )
}