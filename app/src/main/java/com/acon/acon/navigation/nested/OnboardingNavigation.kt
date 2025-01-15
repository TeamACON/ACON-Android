package com.acon.acon.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.acon.navigation.route.OnboardingRoute
import com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen.composable.UnlikeFoodScreenContainer

internal fun NavGraphBuilder.onboardingNavigationNavigation(
    navController: NavHostController
) {

    navigation<OnboardingRoute.Graph>(
        startDestination = OnboardingRoute.ChooseUnlikeFoods
    ) {
        composable<OnboardingRoute.ChooseUnlikeFoods> {
            UnlikeFoodScreenContainer()
        }
        composable<OnboardingRoute.RatePreferFoods> {
            // PreferredFoodRateScreenContainer()

//            onSignInSuccess = {
//                    navController.navigate(OnboardingRoute)
//                } <- NavigateToPreviousPage 이런 건 Container 안에 이렇게 넣어주면 됨

        }
        composable<OnboardingRoute.SelectFrequentPlace> {
            // FrequentPlaceSelectScreenContainer()
        }
        composable<OnboardingRoute.SelectPreferPlace> {
            // PreferredPlaceSelectScreenContainer()
        }
        composable<OnboardingRoute.RatePreferPlace> {
            // PerferredPlaceRateScreenContainer()
        }
    }
}