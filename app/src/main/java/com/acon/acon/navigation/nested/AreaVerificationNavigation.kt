package com.acon.acon.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.acon.navigation.route.AreaVerificationRoute

internal fun NavGraphBuilder.areaVerificationNavigation(
    navController: NavHostController
) {

    navigation<AreaVerificationRoute>(
        startDestination = AreaVerificationRoute.RequireAreaVerification
    ) {
        composable<AreaVerificationRoute.RequireAreaVerification> {
//            RequireAreaVerificationScreenContainer(
//                onNewAreaClick = {
//                    navController.navigate(AreaVerificationRoute.CheckInMap)
//                }, onExistAreaClick = {
//                    // ??
//                }, onSkip = {
//                    navController.navigate(AreaVerificationRoute.Complete)
//                }
//            )
        }
        composable<AreaVerificationRoute.CheckInMap> {
//            CheckInMapScreenContainer(...)
        }
        composable<AreaVerificationRoute.Complete> {
//            CompleteScreenContainer(
//                onFinish = {
//                    navController.navigate(OnboardingRoute)
//                }
//            )
        }
    }
}