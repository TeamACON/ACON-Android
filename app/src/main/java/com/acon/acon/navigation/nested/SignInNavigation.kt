package com.acon.acon.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.acon.navigation.route.AreaVerificationRoute
import com.acon.acon.navigation.route.SignInRoute
import com.acon.acon.navigation.route.SpotRoute
import com.acon.domain.repository.GoogleTokenRepository
import com.acon.feature.signin.screen.SignInScreenContent

internal fun NavGraphBuilder.signInNavigationNavigation(
    navController: NavHostController,
    googleTokenRepository: GoogleTokenRepository
) {

    navigation<SignInRoute.Graph>(
        startDestination = SignInRoute.SignIn,
    ) {
        composable<SignInRoute.SignIn> {
            SignInScreenContent(
                navigateToSpotListView = {
                    //navController.navigate(SpotRoute.SpotList)
                },
                navigateToAreaVerification = {
                    //navController.navigate(AreaVerificationRoute.RequireAreaVerification)
                },
                googleSignInRepository = googleTokenRepository,
            )
        }
    }
}