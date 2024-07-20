package com.dreamsoftware.fitflextv.ui.navigation

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.usecase.SelectProfileUseCase
import com.dreamsoftware.fitflextv.ui.screens.category.CategoryDetailScreenArgs
import com.dreamsoftware.fitflextv.ui.screens.moreoptions.MoreOptionsScreenArgs
import com.dreamsoftware.fitflextv.ui.screens.player.video.VideoPlayerScreenArgs
import com.dreamsoftware.fitflextv.ui.screens.profiles.save.SaveProfileScreenArgs
import com.dreamsoftware.fitflextv.ui.screens.profiles.secure.SecurePinScreenArgs
import com.dreamsoftware.fitflextv.ui.screens.trainingdetail.TrainingDetailScreenArgs

sealed class Screens(
    val route: String,
    val name: String,
    arguments: List<NamedNavArgument> = emptyList(),
    val isNavigationDrawerItem: Boolean = false,
    val navigationDrawerIcon: Int? = null
) {

    data object Splash: Screens(route = "splash", name = "Splash")
    data object Onboarding: Screens(route = "onboarding", name = "Onboarding")
    data object SignIn: Screens(route = "sign_in", name = "SignIn")
    data object SignUp: Screens(route = "sign_up", name = "SignUp")
    data object Dashboard: Screens(route = "dashboard", name = "Dashboard")
    data object Subscription: Screens(route = "subscription", name = "Subscription")
    data object Profiles: Screens(route = "profiles", name = "Profiles")
    data object ProfileSelector: Screens(route = "profile_selector", name = "ProfileSelector")
    data object ProfilesManagement: Screens(route = "profile_management", name = "ProfilesManagement")
    data object AddProfile: Screens(route = "add_profile", name = "AddProfile")
    data object EditProfile: Screens(route = "edit_profile/{id}", name = "EditProfile", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): SaveProfileScreenArgs? = with(args) {
            getString("id")?.let { id ->
                SaveProfileScreenArgs(
                    profileId = id,
                )
            }
        }
    }
    data object UnlockProfile: Screens(route = "unlock_profile/{id}", name = "UnlockProfile", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): SecurePinScreenArgs? = with(args) {
            getString("id")?.let { id ->
                SecurePinScreenArgs(id)
            }
        }
    }

    data object Home: Screens(route = "home", name = "Home", isNavigationDrawerItem = true, navigationDrawerIcon = R.drawable.home)
    data object Training: Screens(route = "training", name = "Training", isNavigationDrawerItem = true, navigationDrawerIcon = R.drawable.fitness_center)
    data object Favorite: Screens(route = "favorite", name = "Favorite", isNavigationDrawerItem = true, navigationDrawerIcon = R.drawable.favorite)
    data object Settings: Screens(route = "settings", name = "Settings", isNavigationDrawerItem = true, navigationDrawerIcon = R.drawable.settings)
    data object VideoPlayer: Screens(route = "video_player/{type}/{id}", name = "VideoPlayer", arguments = listOf(
        navArgument("type") {
            type = NavType.StringType
        },
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String, type: TrainingTypeEnum): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            ).replace(
                oldValue = "{type}",
                newValue = type.name
            )

        fun parseArgs(args: Bundle): VideoPlayerScreenArgs? = with(args) {
            getString("id")?.let { id ->
                getString("type")?.let(TrainingTypeEnum::valueOf)?.let { type ->
                    VideoPlayerScreenArgs(
                        id = id,
                        type = type
                    )
                }
            }
        }
    }
    data object AudioPlayer: Screens(route = "audio_player", name = "AudioPlayer")
    data object TrainingDetail : Screens(route = "training_detail/{type}/{id}", name = "TrainingDetail", arguments = listOf(
        navArgument("type") {
            type = NavType.StringType
        },
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String, type: TrainingTypeEnum): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            ).replace(
                oldValue = "{type}",
                newValue = type.name
            )

        fun parseArgs(args: Bundle): TrainingDetailScreenArgs? = with(args) {
            getString("id")?.let { id ->
                getString("type")?.let(TrainingTypeEnum::valueOf)?.let { type ->
                    TrainingDetailScreenArgs(
                        id = id,
                        type = type
                    )
                }
            }
        }
    }

    data object CategoryDetail : Screens(route = "category_detail/{id}", name = "CategoryDetail", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): CategoryDetailScreenArgs? = with(args) {
            getString("id")?.let { id ->
                CategoryDetailScreenArgs(id = id)
            }
        }
    }

    data object MoreOptions : Screens(route = "more_options/{type}/{id}", name = "MoreOptions", arguments = listOf(
        navArgument("type") {
            type = NavType.StringType
        },
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String, type: TrainingTypeEnum): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            ).replace(
                oldValue = "{type}",
                newValue = type.name
            )

        fun parseArgs(args: Bundle): MoreOptionsScreenArgs? = with(args) {
            getString("id")?.let { id ->
                getString("type")?.let(TrainingTypeEnum::valueOf)?.let { type ->
                    MoreOptionsScreenArgs(
                        id = id,
                        type = type
                    )
                }
            }
        }
    }
}