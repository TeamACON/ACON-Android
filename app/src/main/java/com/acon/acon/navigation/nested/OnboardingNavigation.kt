package com.acon.acon.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.feature.onboarding.LinkedHashSetNavType
import com.acon.feature.onboarding.OnboardingResultNavType
import com.acon.feature.onboarding.OnboardingRoute
import com.acon.feature.onboarding.StringListNavType
import com.acon.feature.onboarding.StringSetNavType
import com.acon.feature.onboarding.screen.OnboardingScreen.OnboardingResult
import com.acon.feature.onboarding.screen.delete.FrequentPlaceSelectScreen.composable.FrequentPlaceSelectScreenContainer
import com.acon.feature.onboarding.screen.OnboardingScreen.composable.OnboardingContainer
import com.acon.feature.onboarding.screen.delete.PerferredPlaceRateScreen.composable.PreferredPlaceRateScreenContainer
import com.acon.feature.onboarding.screen.PrefResultLoadingScreen.composable.PrefResultLoadingScreenContainer
import com.acon.feature.onboarding.screen.delete.PreferredFoodRateScreen.composable.PreferredFoodRateScreenContainer
import com.acon.feature.onboarding.screen.delete.PreferredPlaceSelectScreen.composable.PreferredPlaceSelectScreenContainer
import com.acon.feature.onboarding.screen.delete.UnlikeFoodSelectScreen.composable.UnlikeFoodScreenContainer
import com.acon.feature.spot.com.acon.feature.spot.SpotRoute
import kotlin.reflect.typeOf


internal fun NavGraphBuilder.onboardingNavigationNavigation(
    navController: NavHostController
) {

    navigation<OnboardingRoute.Graph>(
        startDestination = OnboardingRoute.OnboardingScreen
    ) {
        composable<OnboardingRoute.LastLoading>(
            typeMap = mapOf(
                typeOf<Set<String>>() to StringSetNavType,
                typeOf<List<String>>() to StringListNavType,
                typeOf<LinkedHashSet<String>>() to LinkedHashSetNavType,
                typeOf<OnboardingResult>() to OnboardingResultNavType,
            )
        ) {
            PrefResultLoadingScreenContainer(
                navigateToSpotListView = {
                    navController.navigate(SpotRoute.SpotList)
                }
            )
        }
        composable<OnboardingRoute.OnboardingScreen> {
            OnboardingContainer(
                navigateToLoadingView = {
                    navController.navigate(OnboardingRoute.LastLoading(it))
                },
                navigateToSpotListView = {
                    navController.navigate(SpotRoute.SpotList)
                }
            )
        }
    }
}