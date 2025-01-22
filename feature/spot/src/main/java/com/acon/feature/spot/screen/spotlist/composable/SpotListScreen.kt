package com.acon.feature.spot.screen.spotlist.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.acon.core.designsystem.blur.LocalHazeState
import com.acon.core.designsystem.blur.defaultHazeEffect
import com.acon.core.designsystem.component.loading.SkeletonItem
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.spot.R
import com.acon.feature.spot.screen.spotlist.ConditionState
import com.acon.feature.spot.screen.spotlist.SpotListUiState
import com.acon.feature.spot.screen.spotlist.composable.bottomsheet.SpotFilterBottomSheet
import com.acon.feature.spot.type.FloatingButtonType
import com.acon.feature.spot.type.SpotShowType
import com.github.fengdai.compose.pulltorefresh.PullToRefresh
import com.github.fengdai.compose.pulltorefresh.rememberPullToRefreshState
import dev.chrisbanes.haze.hazeSource

@Composable
internal fun SpotListScreen(
    state: SpotListUiState,
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit = {},
    onResetFilter: () -> Unit = {},
    onCompleteFilter: (ConditionState) -> Unit = {},
    onFilterBottomSheetShowStateChange: (Boolean) -> Unit = {},
    onSpotItemClick: (id: Int) -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier,
        color = AconTheme.color.Gray9
    ) {
        when (state) {
            is SpotListUiState.Success -> {
                if (state.showFilterBottomSheet) {
                    SpotFilterBottomSheet(
                        hazeState = LocalHazeState.current,
                        condition = state.currentCondition,
                        onComplete = onCompleteFilter,
                        onReset = onResetFilter,
                        onDismissRequest = {
                            onFilterBottomSheetShowStateChange(false)
                        },
                        modifier = Modifier.padding(top = 50.dp).fillMaxSize()
                    )
                }

                val isResultEmpty by remember {
                    derivedStateOf {
                        state.spotList.isEmpty()
                    }
                }
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    PullToRefresh(
                        modifier = Modifier,
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
                                .hazeSource(LocalHazeState.current)
                        ) {
                            Spacer(modifier = Modifier.height(44.dp))
                            Text(
                                text = stringResource(R.string.spot_name),
                                style = AconTheme.typography.title2_20_b,
                                color = AconTheme.color.White,
                                modifier = Modifier.padding(vertical = 14.dp)
                            )
                            if (isResultEmpty) {
                                EmptySpotListView(modifier = Modifier.fillMaxSize())
                            } else {
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
                                        modifier = Modifier
                                            .clickable {
                                                onSpotItemClick(spot.id)
                                            }
                                            .weight(if (isFirstRank) 3f else 1f),
                                    )
                                    if (spot !== state.spotList.last())
                                        Spacer(modifier = Modifier.height(12.dp))
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 16.dp)
                            .padding(horizontal = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FloatingButtonType.entries.fastForEach {
                            Icon(
                                imageVector = ImageVector.vectorResource(it.iconRes),
                                tint = if (it.enabled) AconTheme.color.White else AconTheme.color.Gray5,
                                contentDescription = stringResource(it.contentDescriptionRes),
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(48.dp)
                                    .defaultHazeEffect(hazeState = LocalHazeState.current, tintColor = AconTheme.color.Gla_w_30, blurRadius = 8.dp)
                                    .clickable {
                                        if (it.enabled) {
                                            when (it) {
                                                FloatingButtonType.LOCATION -> {
                                                    // TODO : 위치 버튼 클릭 시 동작
                                                }
                                                FloatingButtonType.MAP -> {
                                                    // TODO : 지도 버튼 클릭 시 동작
                                                }
                                                FloatingButtonType.FILTER -> {
                                                    onFilterBottomSheetShowStateChange(true)
                                                }
                                            }
                                        }
                                    }
                                    .padding(10.dp)
                            )
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