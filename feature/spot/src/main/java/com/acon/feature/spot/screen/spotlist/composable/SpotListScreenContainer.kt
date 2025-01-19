package com.acon.feature.spot.screen.spotlist.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.core.map.onLocationReady
import com.acon.core.utils.feature.permission.CheckAndRequestLocationPermission
import com.acon.feature.spot.screen.spotlist.SpotListUiState
import com.acon.feature.spot.screen.spotlist.SpotListViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SpotListScreenContainer(
    modifier: Modifier = Modifier,
    onNavigateToSpotDetailScreen: (id: Int) -> Unit = {},
    viewModel: SpotListViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val state by viewModel.collectAsState()

    CheckAndRequestLocationPermission(
        onPermissionGranted = {
            if (state !is SpotListUiState.Success)
                context.onLocationReady {
                    viewModel.onLocationReady(it)
                }
        }
    )

    SpotListScreen(
        state = state,
        modifier = modifier.fillMaxSize(),
        onRefresh = {
            context.onLocationReady {
                viewModel.onRefresh(it)
            }
        }, onFilterBottomSheetShowStateChange = viewModel::onFilterBottomSheetStateChange,
        onResetFilter = {
            viewModel.onResetFilter()
            context.onLocationReady {
                viewModel.onLocationReady(it)
            }
        },
        onCompleteFilter = { condition ->
            viewModel.onCompleteFilter(condition)
            context.onLocationReady {
                viewModel.onLocationReady(it)
            }
        },
        onNavigateToSpotDetailScreen = onNavigateToSpotDetailScreen
    )
}