package com.acon.acon.navigation.nested

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acon.acon.navigation.route.SpotRoute
import com.acon.feature.spot.screen.spotlist.composable.SpotListScreenContainer

internal fun NavGraphBuilder.spotNavigation(
    navController: NavHostController
) {

    navigation<SpotRoute.Graph>(
        startDestination = SpotRoute.SpotList
    ) {
        composable<SpotRoute.SpotList> {
            SpotListScreenContainer(
                modifier = Modifier.fillMaxSize(),
                onNavigateToSpotDetailScreen = {
                    navController.navigate(SpotRoute.SpotDetail(it))
                }
            )
        }

        composable<SpotRoute.SpotDetail> {
            // SpotDetailScreenContainer(...)
        }
    }
}