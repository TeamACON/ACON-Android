package com.acon.acon.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.feature.signin.screen.SignInRoute
import com.acon.domain.repository.SocialRepository
import com.acon.feature.areaverification.AreaVerificationRoute
import com.acon.feature.signin.screen.SignInScreenContent
import com.acon.feature.spot.com.acon.feature.spot.SpotRoute

internal fun NavGraphBuilder.signInNavigationNavigation(
    navController: NavHostController,
    socialRepository: SocialRepository
) {

    navigation<SignInRoute.Graph>(
        startDestination = SignInRoute.SignIn,
    ) {
        composable<SignInRoute.SignIn> {
            SignInScreenContent(
                navigateToSpotListView = {
                    navController.navigate(SpotRoute.SpotList)
                },
                navigateToAreaVerification = {
                    navController.navigate(AreaVerificationRoute.RequireAreaVerification)
                },
                socialRepository = socialRepository,
            )
        }
    }
}
