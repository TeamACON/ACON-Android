package com.acon.android.navigation.nested

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.android.BuildConfig
import com.acon.android.feature.SettingsRoute
import com.acon.android.feature.onboarding.OnboardingRoute
import com.acon.android.feature.profile.composable.ProfileRoute
import com.acon.android.feature.settings.screen.composable.SettingsScreenContainer
import com.acon.android.feature.signin.screen.SignInRoute
import com.acon.android.feature.spot.SpotRoute
import com.acon.android.feature.withdraw.screen.composable.DeleteAccountScreenContainer

internal fun NavGraphBuilder.settingsNavigation(
    navController: NavHostController
) {
    val versionName = BuildConfig.VERSION_NAME

    navigation<SettingsRoute.Graph>(
        startDestination = SettingsRoute.Settings,
    ) {
        composable<SettingsRoute.Settings> {
            SettingsScreenContainer(
                modifier = Modifier.fillMaxSize(),
                versionName = versionName,
                onNavigateToProfileScreen = {
                    navController.navigate(ProfileRoute.Profile) {
                        popUpTo(SettingsRoute.Graph) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToOnboardingScreen = {
                    navController.navigate(OnboardingRoute.OnboardingScreen)
                },
                onNavigateToSignInScreen = {
                    navController.navigate(SignInRoute.SignIn) {
                        popUpTo(SettingsRoute.Graph) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToDeleteAccountScreen = {
                    navController.navigate(SettingsRoute.DeleteAccount)
                }
            )
        }

        composable<SettingsRoute.DeleteAccount> {
            DeleteAccountScreenContainer(
                modifier = Modifier.fillMaxSize(),
                navigateToSettings = {
                    navController.navigate(SettingsRoute.Settings) {
                        popUpTo(SettingsRoute.Graph) {
                            inclusive = true
                        }
                    }
                },
                navigateToSignIn = {
                    navController.navigate(SignInRoute.SignIn) {
                        popUpTo(SpotRoute.Graph) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}