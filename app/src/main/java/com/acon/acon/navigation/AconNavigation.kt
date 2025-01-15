package com.acon.acon.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.acon.acon.navigation.bottom.BottomBar
import com.acon.acon.navigation.nested.areaVerificationNavigation
import com.acon.acon.navigation.nested.onboardingNavigationNavigation
import com.acon.acon.navigation.nested.signInNavigationNavigation
import com.acon.acon.navigation.nested.spotNavigation
import com.acon.acon.navigation.route.SignInRoute
import com.acon.acon.navigation.route.SpotRoute
import com.acon.core.designsystem.theme.AconTheme

@Composable
fun AconNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            if (backStackEntry?.destination?.shouldShowBottomNav() == true) {
                BottomBar(
                    modifier = Modifier.fillMaxWidth().background(color = AconTheme.color.Gla_b_30)
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SignInRoute.Graph,
            modifier = modifier.padding(innerPadding),
        ) {

            signInNavigationNavigation(navController)

            areaVerificationNavigation(navController)

            onboardingNavigationNavigation(navController)

            spotNavigation(navController)
        }
    }
}

private fun NavDestination.shouldShowBottomNav(): Boolean {
    return when (route) {
        SpotRoute.SpotList::class.simpleName -> true
        else -> false
    }
}