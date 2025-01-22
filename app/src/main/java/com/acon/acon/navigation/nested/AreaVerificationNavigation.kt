package com.acon.acon.navigation.nested

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.acon.feature.areaverification.AreaVerificationRoute
import com.acon.feature.areaverification.AreaVerificationScreenContainer
import com.acon.feature.areaverification.PreferenceMapScreen

fun NavGraphBuilder.areaVerificationNavigation(
    navController: NavHostController
) {
    navigation<AreaVerificationRoute.Graph>(
        startDestination = AreaVerificationRoute.RequireAreaVerification
    ) {
        composable<AreaVerificationRoute.RequireAreaVerification> {
            AreaVerificationScreenContainer(
                modifier = Modifier.fillMaxSize(),
                onNewAreaClick = { latitude, longitude ->
                    navController.navigate(
                        AreaVerificationRoute.CheckInMap(latitude, longitude)
                    )
                },
                onNextScreen = { latitude, longitude ->
                    navController.navigate(
                        AreaVerificationRoute.CheckInMap(latitude, longitude)
                    )
                }
            )
        }
        composable<AreaVerificationRoute.CheckInMap> { backStackEntry ->
            val route = backStackEntry.toRoute<AreaVerificationRoute.CheckInMap>()

            PreferenceMapScreen(
                latitude = route.latitude,
                longitude = route.longitude,
                onConfirmClick = {
                    navController.navigate(AreaVerificationRoute.Complete)
                },
                onNavigateToNext = {
                    //Skip to next screen
                    navController.navigate(AreaVerificationRoute.Complete)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable<AreaVerificationRoute.Complete> {
            // CompleteScreenContainer(
            //     onFinish = {
            //         navController.navigate(OnboardingRoute)
            //     }
            // )
        }
    }
}