package com.acon.feature.spot.screen.spotlist.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.acon.core.designsystem.component.loading.SkeletonItem
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.spot.R
import com.acon.feature.spot.screen.spotlist.SpotListUiState
import com.acon.feature.spot.type.SpotShowType
import com.github.fengdai.compose.pulltorefresh.PullToRefresh
import com.github.fengdai.compose.pulltorefresh.rememberPullToRefreshState

@Composable
internal fun SpotListScreen(
    state: SpotListUiState,
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit = {},
    onNavigateToSpotDetailScreen: (id: Int) -> Unit = {},
) {
    val scrollState = rememberScrollState()
    Surface(
        modifier = modifier,
        color = AconTheme.color.Gray9
    ) {
        when (state) {
            is SpotListUiState.Success -> {
                PullToRefresh(
                    state = rememberPullToRefreshState(state.isRefreshing),
                    onRefresh = onRefresh,
                    dragMultiplier = .35f,
                    refreshTriggerDistance = 60.dp,
                    refreshingOffset = 60.dp,
                    indicator = { state, refreshTriggerDistance, _ ->
                        SpotListPullToRefreshIndicator(refreshTriggerDistance, state)
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
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
                            val isFirstRank =
                                state.spotShowType == SpotShowType.BEST1 && spot === state.spotList.first()
                            SpotItem(
                                spot = spot,
                                isFirstRank = isFirstRank,
                                modifier = Modifier.clickable {
                                    onNavigateToSpotDetailScreen(spot.id)
                                }.weight(if (isFirstRank) 3f else 1f),
                            )
                            if (spot !== state.spotList.last())
                                Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
            }

            SpotListUiState.Loading -> {
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
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
                    SkeletonItem(
                        modifier = Modifier
                            .weight(3f)
                            .clip(RoundedCornerShape(6.dp))
                            .fillMaxWidth()
                    )
                    SkeletonItem(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .weight(1f)
                            .clip(RoundedCornerShape(6.dp))
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
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
@Preview
@Composable
private fun SpotListLoadingScreenPreview() {
    SpotListScreen(
        state = SpotListUiState.Loading
    )
}