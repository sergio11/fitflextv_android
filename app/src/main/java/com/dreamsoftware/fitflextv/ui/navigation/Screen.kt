package com.dreamsoftware.fitflextv.ui.navigation

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.ui.screens.category.CategoryDetailScreenArgs
import com.dreamsoftware.fitflextv.ui.screens.moreoptions.MoreOptionsScreenArgs
import com.dreamsoftware.fitflextv.ui.screens.player.audio.AudioPlayerScreenArgs
import com.dreamsoftware.fitflextv.ui.screens.player.video.VideoPlayerScreenArgs
import com.dreamsoftware.fitflextv.ui.screens.profiles.advance.ProfileAdvanceScreenArgs
import com.dreamsoftware.fitflextv.ui.screens.profiles.delete.DeleteProfileScreenArgs
import com.dreamsoftware.fitflextv.ui.screens.profiles.save.SaveProfileScreenArgs
import com.dreamsoftware.fitflextv.ui.screens.profiles.secure.SecurePinScreenArgs
import com.dreamsoftware.fitflextv.ui.screens.trainingdetail.TrainingDetailScreenArgs

sealed class Screen(
    val route: String,
    val name: String,
    arguments: List<NamedNavArgument> = emptyList()
) {

    data object Splash: Screen(route = "splash", name = "Splash")
    data object Onboarding: Screen(route = "onboarding", name = "Onboarding")
    data object SignIn: Screen(route = "sign_in", name = "SignIn")
    data object SignUp: Screen(route = "sign_up", name = "SignUp")
    data object Dashboard: Screen(route = "dashboard", name = "Dashboard")
    data object Subscription: Screen(route = "subscription", name = "Subscription")
    data object Profiles: Screen(route = "profiles", name = "Profiles")
    data object ProfileSelector: Screen(route = "profile_selector", name = "ProfileSelector")
    data object ProfilesManagement: Screen(route = "profile_management", name = "ProfilesManagement")
    data object AddProfile: Screen(route = "add_profile", name = "AddProfile")
    data object EditProfile: Screen(route = "edit_profile/{id}", name = "EditProfile", arguments = listOf(
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
    data object ProfileAdvance: Screen(route = "profile_advance/{id}", name = "ProfileAdvance", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): ProfileAdvanceScreenArgs? = with(args) {
            getString("id")?.let { id ->
                ProfileAdvanceScreenArgs(
                    profileId = id,
                )
            }
        }
    }
    data object DeleteProfile: Screen(route = "delete_profile/{id}", name = "DeleteProfile", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): DeleteProfileScreenArgs? = with(args) {
            getString("id")?.let { id ->
                DeleteProfileScreenArgs(
                    profileId = id,
                )
            }
        }
    }
    data object UnlockProfile: Screen(route = "unlock_profile/{id}", name = "UnlockProfile", arguments = listOf(
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

    data object Home: Screen(route = "home", name = "Home")
    data object Training: Screen(route = "training", name = "Training")
    data object Favorite: Screen(route = "favorite", name = "Favorite")
    data object Settings: Screen(route = "settings", name = "Settings")
    data object VideoPlayer: Screen(route = "video_player/{type}/{id}", name = "VideoPlayer", arguments = listOf(
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
    data object AudioPlayer: Screen(route = "audio_player/{id}", name = "AudioPlayer", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): AudioPlayerScreenArgs? = with(args) {
            getString("id")?.let { id ->
                AudioPlayerScreenArgs(
                    id = id
                )
            }
        }
    }
    data object TrainingDetail : Screen(route = "training_detail/{type}/{id}", name = "TrainingDetail", arguments = listOf(
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

    data object CategoryDetail : Screen(route = "category_detail/{id}", name = "CategoryDetail", arguments = listOf(
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

    data object MoreOptions : Screen(route = "more_options/{type}/{id}", name = "MoreOptions", arguments = listOf(
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