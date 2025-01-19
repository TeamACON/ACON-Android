package com.acon.feature.spot.screen.spotdetail.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.feature.spot.screen.spotdetail.SpotDetailViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SpotDetailScreenContainer(
    modifier: Modifier = Modifier,
    viewModel: SpotDetailViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    SpotDetailScreen(
        state = state,
        modifier = modifier.fillMaxSize(),
    )
}