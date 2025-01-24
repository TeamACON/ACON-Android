package com.acon.acon.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.feature.onboarding.OnboardingRoute
import com.acon.feature.onboarding.screen.FrequentPlaceSelectScreen.composable.FrequentPlaceSelectScreenContainer
import com.acon.feature.onboarding.screen.OnboardingScreen.composable.OnboardingContainer
import com.acon.feature.onboarding.screen.PerferredPlaceRateScreen.composable.PreferredPlaceRateScreenContainer
import com.acon.feature.onboarding.screen.PrefResultLoadingScreen.composable.PrefResultLoadingScreenContainer
import com.acon.feature.onboarding.screen.PreferredFoodRateScreen.composable.PreferredFoodRateScreenContainer
import com.acon.feature.onboarding.screen.PreferredPlaceSelectScreen.composable.PreferredPlaceSelectScreenContainer
import com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen.composable.UnlikeFoodScreenContainer
import com.acon.feature.spot.com.acon.feature.spot.SpotRoute


internal fun NavGraphBuilder.onboardingNavigationNavigation(
    navController: NavHostController
) {

    navigation<OnboardingRoute.Graph>(
        startDestination = OnboardingRoute.OnboardingScreen
    ) {
        composable<OnboardingRoute.ChooseUnlikeFoods> {
            UnlikeFoodScreenContainer(
                navigateToNextPage = {
                    navController.navigate(OnboardingRoute.RatePreferFoods)
                },
                navigateToSpotListView = {
                    navController.navigate(SpotRoute.SpotList)
                }
            )
        }

        composable<OnboardingRoute.RatePreferFoods> {
             PreferredFoodRateScreenContainer(
                 navigateToPreviousPage = {
                     navController.popBackStack()
                 },
                 navigateToNextPage = {
                     navController.navigate(OnboardingRoute.SelectFrequentPlace)
                 },
                 navigateToSpotListView = {
                     navController.navigate(SpotRoute.SpotList)
                 }
             )


        }
        composable<OnboardingRoute.SelectFrequentPlace> {
            FrequentPlaceSelectScreenContainer(
                navigateToPreviousPage = {
                    navController.popBackStack()
                },
                navigateToNextPage = {
                    navController.navigate(OnboardingRoute.SelectPreferPlace)
                },
                navigateToSpotListView = {
                    navController.navigate(SpotRoute.SpotList)
                }
            )
        }
        composable<OnboardingRoute.SelectPreferPlace> {
            PreferredPlaceSelectScreenContainer(
                navigateToPreviousPage = {
                    navController.popBackStack()
                },
                navigateToNextPage = {
                    navController.navigate(OnboardingRoute.RatePreferPlace)
                },
                navigateToSpotListView = {
                    navController.navigate(SpotRoute.SpotList)
                }
            )
        }
        composable<OnboardingRoute.RatePreferPlace> {
            PreferredPlaceRateScreenContainer(
                 navigateToPreviousPage = {
                     navController.popBackStack()
                 },
                 navigateToNextPage = {
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
    }
}