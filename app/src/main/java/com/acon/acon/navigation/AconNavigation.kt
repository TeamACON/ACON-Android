package com.acon.acon.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.acon.acon.navigation.tree.areaVerificationTree
import com.acon.acon.navigation.tree.onboardingNavigationTree
import com.acon.acon.navigation.tree.signInNavigationTree

@Composable
fun AconNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Routes.SignInRoute.SignIn,
        modifier = modifier,
    ) {

        signInNavigationTree(navController)

        areaVerificationTree(navController)

        onboardingNavigationTree(navController)
    }
}