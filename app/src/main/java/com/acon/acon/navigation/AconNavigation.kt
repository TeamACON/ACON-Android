package com.acon.acon.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.acon.acon.navigation.nested.areaVerificationNavigation
import com.acon.acon.navigation.nested.onboardingNavigationNavigation
import com.acon.acon.navigation.nested.signInNavigationNavigation
import com.acon.acon.navigation.nested.spotNavigation
import com.acon.acon.navigation.route.SignInRoute
import com.acon.domain.repository.GoogleTokenRepository

@Composable
fun AconNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    googleTokenRepository: GoogleTokenRepository,
) {

    NavHost(
        navController = navController,
        startDestination = SignInRoute.Graph,
        modifier = modifier,
    ) {

        signInNavigationNavigation(navController, googleTokenRepository)

        areaVerificationNavigation(navController)

        onboardingNavigationNavigation(navController)

        spotNavigation(navController)
    }
}