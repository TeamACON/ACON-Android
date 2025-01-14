package com.acon.feature.spot.screen.spotlist.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.spot.R
import com.acon.feature.spot.screen.spotlist.SpotListUiState
import com.acon.feature.spot.type.SpotShowType

@Composable
internal fun SpotListScreen(
    state: SpotListUiState,
    modifier: Modifier = Modifier,
    onNavigateToSpotDetailScreen: (id: Int) -> Unit = {},
) {

    Surface(
        modifier = modifier,
        color = AconTheme.color.Gray9
    ) {
        when (state) {
            is SpotListUiState.Success -> {
                val itemInnerSpacing = when (state.spotShowType) {
                    SpotShowType.BEST1 -> 288.dp
                    SpotShowType.BEST2 -> 20.dp
                }

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                ) {
                    Spacer(modifier = Modifier.height(44.dp))
                    Text(
                        text = stringResource(R.string.spot_name),
                        style = AconTheme.typography.title2_20_b,
                        color = AconTheme.color.White,
                        modifier = Modifier.padding(vertical = 14.dp)
                    )
                    Text(
                        text = stringResource(R.string.spot_recommendation_description),
                        style = AconTheme.typography.head7_18_sb,
                        color = AconTheme.color.White,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    state.spotList.fastForEach { spot ->
                        SpotItem(
                            spot = spot,
                            isFirstRank = state.spotShowType == SpotShowType.BEST1 && spot === state.spotList.first(),
                            modifier = Modifier.clickable {
                                onNavigateToSpotDetailScreen(spot.id)
                            },
                            spacing = itemInnerSpacing
                        )
                        if (spot !== state.spotList.last())
                            Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }

            SpotListUiState.Loading -> {
                // TODO : 로딩 뷰
            }

            SpotListUiState.LoadFailed -> {
                // TODO : 로드 실패 뷰
            }
        }
    }
}

@Preview
@Composable
private fun SpotListScreenPreview() {
    SpotListScreen(
        state = SpotListUiState.Success(emptyList(), SpotShowType.BEST1)
    )
}