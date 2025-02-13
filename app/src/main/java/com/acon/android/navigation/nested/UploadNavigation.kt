package com.acon.android.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acon.android.feature.spot.SpotRoute
import com.acon.android.feature.upload.UploadContainer
import com.acon.android.feature.upload.UploadRoute
import com.acon.android.feature.upload.UploadSuccessContainer

internal fun NavGraphBuilder.uploadNavigation(
    navController: NavHostController
) {
    navigation<UploadRoute.Graph>(
        startDestination = UploadRoute.Upload
    ) {
        composable<UploadRoute.Upload> {
            UploadContainer(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToSuccess = {
                    navController.navigate(UploadRoute.Success)
                }
            )
        }

        composable<UploadRoute.Success> {
            UploadSuccessContainer(
                onNavigateToSpotList = {
                    navController.navigate(SpotRoute.SpotList) {
                        popUpTo(UploadRoute.Graph) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
