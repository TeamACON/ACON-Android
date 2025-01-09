package com.acon.acon.navigation.tree

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.acon.navigation.Routes

internal fun NavGraphBuilder.areaVerificationTree(
    navController: NavHostController
) {

    navigation<Routes.AreaVerificationRoute>(
        startDestination = Routes.AreaVerificationRoute.RequireAreaVerification
    ) {
        composable<Routes.AreaVerificationRoute.RequireAreaVerification> {
//            RequireAreaVerificationScreen(
//                onNewAreaClick = {
//                    navController.navigate(Routes.AreaVerificationRoute.CheckInMap)
//                }, onExistAreaClick = {
//                    // ??
//                }, onSkip = {
//                    navController.navigate(Routes.AreaVerificationRoute.Complete)
//                }
//            )
        }
        composable<Routes.AreaVerificationRoute.CheckInMap> {
//            CheckInMapScreen(...)
        }
        composable<Routes.AreaVerificationRoute.Complete> {
//            CompleteScreen(
//                onFinish = {
//                    navController.navigate(Routes.OnboardingRoute)
//                }
//            )
        }
    }
}