package com.dreamsoftware.fitflextv.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dreamsoftware.fitflextv.ui.screens.profiles.management.ProfilesManagementScreen
import com.dreamsoftware.fitflextv.ui.screens.profiles.save.SaveProfileScreen
import com.dreamsoftware.fitflextv.ui.screens.profiles.secure.SecurePinScreen
import com.dreamsoftware.fitflextv.ui.screens.profiles.selector.ProfileSelectorScreen

@Composable
fun ProfilesNavigation(
    navController: NavHostController,
    onGoToHome: () -> Unit
) {
    NavHost(navController = navController, startDestination = Screens.ProfileSelector.route) {

        composable(Screens.ProfileSelector.route) {
            with(navController) {
                ProfileSelectorScreen(
                    onProfileSelected = onGoToHome,
                    onProfileLocked = {
                        navigate(Screens.UnlockProfile.buildRoute(it))
                    },
                    onGoToAddProfile = {
                        navigate(Screens.AddProfile.route)
                    },
                    onGoToProfileManagement = {
                        navigate(Screens.ProfilesManagement.route)
                    }
                )
            }
        }

        composable(Screens.AddProfile.route) {
            SaveProfileScreen(
                onBackPressed = {
                    navController.navigateUp()
                }
            )
        }

        composable(Screens.EditProfile.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let { args ->
                Screens.EditProfile.parseArgs(args)?.let {
                    with(navController) {
                        SaveProfileScreen(
                            args = it,
                            onGoToAdvanceConfiguration = {

                            },
                            onBackPressed = {
                                navigateUp()
                            },
                        )
                    }
                }
            }
        }


        composable(Screens.UnlockProfile.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let { args ->
                Screens.UnlockProfile.parseArgs(args)?.let {
                    SecurePinScreen(
                        args = it,
                        onGoToHome = onGoToHome,
                        onBackPressed = {
                            navController.navigateUp()
                        },
                    )
                }
            }
        }

        composable(Screens.ProfilesManagement.route) {
            with(navController) {
                ProfilesManagementScreen(
                    onGoToEditProfile = {
                        navigate(Screens.EditProfile.buildRoute(it))
                    },
                    onBackPressed = {
                        popBackStack()
                    }
                )
            }
        }
    }
}