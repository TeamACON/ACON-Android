package com.acon.acon.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.acon.acon.navigation.nested.areaVerificationNavigation
import com.acon.acon.navigation.nested.onboardingNavigationNavigation
import com.acon.acon.navigation.nested.signInNavigationNavigation
import com.acon.acon.navigation.route.SignInRoute

@Composable
fun AconNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = SignInRoute.Graph,
        modifier = modifier,
    ) {

        signInNavigationNavigation(navController)

        areaVerificationNavigation(navController)

        onboardingNavigationNavigation(navController)
    }
}