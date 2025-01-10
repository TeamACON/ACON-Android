package com.acon.acon.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.acon.navigation.route.OnboardingRoute

internal fun NavGraphBuilder.onboardingNavigationNavigation(
    navController: NavHostController
) {

    navigation<OnboardingRoute>(
        startDestination = OnboardingRoute.ChooseUnlikeFoods
    ) {
        composable<OnboardingRoute.ChooseUnlikeFoods> {
            // ChooseUnlikeFoodsScreenContainer()
        }
        composable<OnboardingRoute.ChoosePreferFoods> {
            // ChoosePreferFoodsScreenContainer()
        }
        composable<OnboardingRoute.ChoosePlace> {
            // ChoosePlaceScreenContainer()
        }
    }
}