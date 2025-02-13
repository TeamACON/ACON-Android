package com.acon.android.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.android.feature.onboarding.OnboardingRoute
import com.acon.android.feature.onboarding.screen.OnboardingScreen.composable.OnboardingContainer
import com.acon.android.feature.onboarding.screen.PrefResultLoadingScreen.composable.PrefResultLoadingScreenContainer
import com.acon.android.feature.spot.SpotRoute


internal fun NavGraphBuilder.onboardingNavigationNavigation(
    navController: NavHostController
) {

    navigation<OnboardingRoute.Graph>(
        startDestination = OnboardingRoute.OnboardingScreen
    ) {
        composable<OnboardingRoute.OnboardingScreen> {
            OnboardingContainer(
                navigateToLoadingView = {
                    navController.navigate(OnboardingRoute.LastLoading)
                },
                navigateToSpotListView = {
                    navController.navigate(SpotRoute.SpotList)
                }
            )
        }

        composable<OnboardingRoute.LastLoading> {
            PrefResultLoadingScreenContainer(
                navigateToSpotListView = {
                    navController.navigate(SpotRoute.SpotList)
                }
            )
        }

    }
}