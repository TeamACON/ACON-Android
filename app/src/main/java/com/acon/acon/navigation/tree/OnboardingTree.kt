package com.acon.acon.navigation.tree

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.acon.navigation.Routes

internal fun NavGraphBuilder.onboardingNavigationTree(
    navController: NavHostController
) {

    navigation<Routes.OnboardingRoute>(
        startDestination = Routes.OnboardingRoute.ChooseUnlikeFoods
    ) {
        composable<Routes.OnboardingRoute.ChooseUnlikeFoods> {
            // ChooseUnlikeFoodsScreen()
        }
        composable<Routes.OnboardingRoute.ChoosePreferFoods> {
            // ChoosePreferFoodsScreen()
        }
        composable<Routes.OnboardingRoute.ChoosePlace> {
            // ChoosePlaceScreen()
        }
    }
}