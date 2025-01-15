package com.acon.acon.navigation.nested

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.acon.navigation.route.AreaVerificationRoute
import com.acon.feature.AreaVerification.AreaVerificationScreenContainer

internal fun NavGraphBuilder.areaVerificationNavigation(
    navController: NavHostController
) {

    navigation<AreaVerificationRoute.Graph>(
        startDestination = AreaVerificationRoute.RequireAreaVerification
    ) {
        composable<AreaVerificationRoute.RequireAreaVerification> {
            AreaVerificationScreenContainer(
                modifier = Modifier.fillMaxSize(),
                onNewAreaClick = {
                    navController.navigate(AreaVerificationRoute.CheckInMap)
                },
//            RequireAreaVerificationScreenContainer(
//                onNewAreaClick = {
//                    navController.navigate(AreaVerificationRoute.CheckInMap)
//                }, onExistAreaClick = {
//                    // ??
//                }, onSkip = {
//                    navController.navigate(AreaVerificationRoute.Complete)
//                }
            )
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