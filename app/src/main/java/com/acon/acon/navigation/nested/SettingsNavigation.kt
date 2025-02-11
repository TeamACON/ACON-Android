package com.acon.acon.navigation.nested

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.feature.SettingsRoute
import com.acon.feature.onboarding.OnboardingRoute
import com.acon.feature.profile.ProfileRoute
import com.acon.feature.settings.screen.composable.SettingsScreenContainer
import com.acon.feature.signin.screen.SignInRoute
import com.acon.feature.withdraw.screen.composable.DeleteAccountScreenContainer

internal fun NavGraphBuilder.settingsNavigation(
    navController: NavHostController
) {
    navigation<SettingsRoute.Graph>(
        startDestination = SettingsRoute.DeleteAccount,
    ) {
        composable<SettingsRoute.Settings> {
            SettingsScreenContainer(
                modifier = Modifier.fillMaxSize(),
                onNavigateToProfileScreen = {
                    navController.navigate(ProfileRoute.Profile)
                },
                onNavigateToOnboardingScreen = {
                    navController.navigate(OnboardingRoute.OnboardingScreen)
                },
                onNavigateToSignInScreen = {
                    navController.navigate(SignInRoute.SignIn)
                },
                onNavigateToDeleteAccountScreen = {
                    navController.navigate(SettingsRoute.DeleteAccount)
                }
            )
        }

        composable<SettingsRoute.DeleteAccount> {
            DeleteAccountScreenContainer(

            )
        }
    }
}