package com.acon.acon.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acon.acon.navigation.route.SplashRoute
import com.acon.feature.signin.screen.SignInRoute
import com.acon.feature.signin.splash.SplashScreenContent
import com.acon.feature.spot.com.acon.feature.spot.SpotRoute

internal fun NavGraphBuilder.splashNavigationNavigation(
    navController: NavHostController
) {
    navigation<SplashRoute.Graph>(
        startDestination = SplashRoute.Splash
    ) {
        composable<SplashRoute.Splash> {
            SplashScreenContent(
                navigationToSignIn = {
                    navController.navigate(SignInRoute.Graph) {
                        popUpTo(SignInRoute.Graph) {
                            inclusive = true
                        }
                    }
                },
                navigationToSpotListView = {
                    navController.navigate(SpotRoute.Graph) {
                        popUpTo(SpotRoute.Graph) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
