package com.acon.acon.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.acon.navigation.route.SignInRoute

internal fun NavGraphBuilder.signInNavigationNavigation(
    navController: NavHostController
) {

    navigation<SignInRoute.Graph>(
        startDestination = SignInRoute.SignIn
    ) {
        composable<SignInRoute.SignIn> {
//            SignInScreenContainer(
//                onSignInSuccess = {
//                    navController.navigate(AreaVerificationRoute)
//                }
//            )
        }
    }
}