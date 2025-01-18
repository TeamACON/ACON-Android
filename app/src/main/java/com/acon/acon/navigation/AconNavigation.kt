package com.acon.acon.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.acon.acon.navigation.bottom.BottomBar
import com.acon.acon.navigation.bottom.BottomNavType
import com.acon.acon.navigation.nested.areaVerificationNavigation
import com.acon.acon.navigation.nested.onboardingNavigationNavigation
import com.acon.acon.navigation.nested.signInNavigationNavigation
import com.acon.acon.navigation.nested.spotNavigation
import com.acon.acon.navigation.nested.uploadNavigation
import com.acon.acon.navigation.route.OnboardingRoute
import com.acon.acon.navigation.route.SpotRoute
import com.acon.core.designsystem.animation.defaultEnterTransition
import com.acon.core.designsystem.animation.defaultExitTransition
import com.acon.core.designsystem.animation.defaultPopEnterTransition
import com.acon.core.designsystem.animation.defaultPopExitTransition
import com.acon.core.designsystem.theme.AconTheme
import com.acon.acon.navigation.route.SignInRoute
import com.acon.domain.repository.GoogleTokenRepository

@Composable
fun AconNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    googleTokenRepository: GoogleTokenRepository,
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    var selectedBottomNavItem by rememberSaveable { mutableStateOf(BottomNavType.SPOT) }
    val currentRoute by remember { derivedStateOf { backStackEntry?.destination?.route } }

    Scaffold(
        modifier = modifier,
        topBar = {
            Spacer(modifier = Modifier.padding(0.dp))
        },
        bottomBar = {
            if (backStackEntry?.destination?.shouldShowBottomNav() == true) {
                BottomBar(
                    modifier = Modifier
                        .background(color = AconTheme.color.Black)  // TODO Color?
                        .fillMaxWidth(),
                    selectedItem = selectedBottomNavItem,
                    onItemClick = {
                        selectedBottomNavItem = it
                    }
                )
            } else {
                Spacer(modifier = Modifier.padding(0.dp))
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SpotRoute.Graph,
            modifier = Modifier.padding(innerPadding),
            enterTransition = {
                defaultEnterTransition()
            }, exitTransition = {
                defaultExitTransition()
            }, popEnterTransition = {
                defaultPopEnterTransition()
            }, popExitTransition = {
                defaultPopExitTransition()
            }
        ) {

        signInNavigationNavigation(navController, googleTokenRepository)

        areaVerificationNavigation(navController)

        onboardingNavigationNavigation(navController)

        spotNavigation(navController)

        uploadNavigation(navController)
        }
    }

    LaunchedEffect(selectedBottomNavItem) {
        navController.navigate(when(selectedBottomNavItem) {
            BottomNavType.SPOT -> SpotRoute.SpotList
            else -> SpotRoute.SpotList // TODO : Route
        }) {
            popUpTo(SpotRoute.SpotList) { inclusive = false }
            launchSingleTop = true
        }
    }

    LaunchedEffect(currentRoute) {   // 뒤로가기에 의한 하단 탭 선택 상태 변경 처리
        selectedBottomNavItem = when (currentRoute) {
            SpotRoute.SpotList::class.qualifiedName -> BottomNavType.SPOT
            else -> BottomNavType.SPOT // TODO : Route
        }
    }
}

private fun NavDestination.shouldShowBottomNav(): Boolean {
    return when (route) {
        SpotRoute.SpotList::class.qualifiedName -> true
        else -> false
    }
}