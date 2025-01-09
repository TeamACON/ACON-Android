package com.acon.acon.navigation.tree

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.acon.navigation.Routes

internal fun NavGraphBuilder.signInNavigationTree(
    navController: NavHostController
) {

    navigation<Routes.SignInRoute>(
        startDestination = Routes.SignInRoute.SignIn
    ) {
        composable<Routes.SignInRoute.SignIn> {
//            SignInScreen(
//                onSignInSuccess = {
//                    navController.navigate(Routes.AreaVerificationRoute)
//                }
//            )
        }
    }
}