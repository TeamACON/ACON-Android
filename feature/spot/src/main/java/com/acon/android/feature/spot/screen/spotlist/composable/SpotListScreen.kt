package com.acon.android.feature.spot.screen.spotlist.composable

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.acon.android.core.designsystem.blur.LocalHazeState
import com.acon.android.core.designsystem.blur.defaultHazeEffect
import com.acon.android.core.designsystem.component.loading.SkeletonItem
import com.acon.android.core.designsystem.theme.AconTheme
import com.acon.android.core.utils.feature.action.BackOnPressed
import com.acon.android.feature.spot.R
import com.acon.android.feature.spot.screen.spotlist.SpotListUiState
import com.acon.android.feature.spot.screen.spotlist.composable.bottomsheet.SpotFilterBottomSheet
import com.acon.android.feature.spot.state.ConditionState
import com.github.fengdai.compose.pulltorefresh.PullToRefresh
import com.github.fengdai.compose.pulltorefresh.rememberPullToRefreshState
import dev.chrisbanes.haze.hazeSource
import kotlinx.coroutines.launch

@Composable
internal fun SpotListScreen(
    state: SpotListUiState,
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit = {},
    onResetFilter: () -> Unit = {},
    onCompleteFilter: (ConditionState, () -> Unit) -> Unit = { _, _ -> },
    onFilterBottomSheetShowStateChange: (Boolean) -> Unit = {},
    onSpotItemClick: (id: Long) -> Unit = {},
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val isDragged by scrollState.interactionSource.collectIsDraggedAsState()

    var scrollableScreenHeightPx by remember {
        mutableIntStateOf(0)
    }
    var scrollableInvisibleHeightPx by remember {
        mutableIntStateOf(0)
    }
    var fullVisibleScreenHeight by remember {
        mutableIntStateOf(0)
    }

    BackOnPressed(context)

    LaunchedEffect(scrollState.value, isDragged) {
        if (isDragged.not()) {
            if (scrollState.value != 0 && scrollableScreenHeightPx != 0 && scrollableInvisibleHeightPx != 0 && fullVisibleScreenHeight != 0)
                if (scrollState.value > scrollableScreenHeightPx - scrollableInvisibleHeightPx - fullVisibleScreenHeight) {
                    scrollState.animateScrollTo(
                        value = scrollState.maxValue - scrollableInvisibleHeightPx,
                        animationSpec = SpringSpec(stiffness = Spring.StiffnessHigh)
                    )
                }
        }
    }

    Surface(
        modifier = modifier.onSizeChanged {
            fullVisibleScreenHeight = it.height
        },
        color = AconTheme.color.Gray9
    ) {
        when (state) {
            is SpotListUiState.Success -> {
                if (state.showFilterBottomSheet) {
                    SpotFilterBottomSheet(
                        hazeState = LocalHazeState.current,
                        condition = state.currentCondition, onComplete = {
                            onCompleteFilter(it) {
                                coroutineScope.launch {
                                    scrollState.animateScrollTo(0)
                                }
                            }
                        },
                        onReset = {
                            onResetFilter()
                            coroutineScope.launch {
                                scrollState.scrollTo(0)
                            }
                        },
                        onDismissRequest = {
                            onFilterBottomSheetShowStateChange(false)
                        },
                        modifier = Modifier
                            .padding(top = 50.dp)
                            .fillMaxSize(),
                        isFilteredResultFetching = state.isFilteredResultFetching
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
                        Column {
                            Text(
                                text = state.legalAddressName,
                                style = AconTheme.typography.head5_22_sb,
                                color = AconTheme.color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .defaultHazeEffect(
                                        hazeState = LocalHazeState.current,
                                        tintColor = AconTheme.color.Dim_b_30,
                                        backgroundColor = Color(0xFF25262A)
                                    )
                                    .padding(vertical = 14.dp, horizontal = 20.dp)
                                    .padding(top = 44.dp),
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(scrollState)
                                    .padding(horizontal = 20.dp)
                                    .hazeSource(LocalHazeState.current)
                                    .onSizeChanged { size ->
                                        scrollableScreenHeightPx = size.height
                                    }
                            ) {
                                if (isResultEmpty) {
                                    EmptySpotListView(modifier = Modifier.fillMaxSize())
                                } else {
                                    Text(
                                        text = stringResource(R.string.spot_recommendation_description),
                                        style = AconTheme.typography.head6_20_sb,
                                        color = AconTheme.color.White,
                                        modifier = Modifier.padding(top = 16.dp)
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    state.spotList.fastForEach { spot ->
                                        val isFirstRank = spot === state.spotList.first()
                                        SpotItem(
                                            spot = spot,
                                            isFirstRank = isFirstRank,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .aspectRatio(if (isFirstRank) 328f / 408f else 328f / 128f)
                                                .clickable {
                                                    onSpotItemClick(spot.id)
                                                },
                                        )
                                        if (spot !== state.spotList.last())
                                            Spacer(modifier = Modifier.height(12.dp))
                                    }
                                }
                                Column(
                                    modifier = Modifier
                                        .padding(top = 12.dp)
                                        .fillMaxWidth()
                                        .onSizeChanged { size ->
                                            scrollableInvisibleHeightPx = size.height
                                        },
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        modifier = Modifier.padding(top = 38.dp, bottom = 50.dp),
                                        text = stringResource(R.string.alert_max_spot_count),
                                        style = AconTheme.typography.body2_14_reg,
                                        color = AconTheme.color.Gray5
                                    )
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(bottom = 16.dp)
                                .padding(horizontal = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(com.acon.android.core.designsystem.R.drawable.ic_filter_w_28),
                                tint = if (state.currentCondition != null) {
                                    AconTheme.color.Main_org1
                                } else {
                                    AconTheme.color.White
                                },
                                contentDescription = stringResource(com.acon.android.core.designsystem.R.string.filter_content_description),
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(48.dp)
                                    .defaultHazeEffect(
                                        hazeState = LocalHazeState.current,
                                        tintColor = AconTheme.color.Dim_b_30,
                                        blurRadius = 8.dp
                                    )
                                    .clickable {
                                        onFilterBottomSheetShowStateChange(true)
                                    }
                                    .padding(12.dp)
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
                        text = "",
                        style = AconTheme.typography.head5_22_sb,
                        color = AconTheme.color.White,
                        modifier = Modifier.padding(vertical = 14.dp)
                    )
                    Text(
                        text = stringResource(R.string.spot_recommendation_description),
                        style = AconTheme.typography.head6_20_sb,
                        color = AconTheme.color.White,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    SkeletonItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(328f / 408f)
                            .clip(RoundedCornerShape(6.dp))
                            .hazeSource(LocalHazeState.current)
                    )
                    repeat(5) {
                        SkeletonItem(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .fillMaxWidth()
                                .aspectRatio(328f / 128f)
                                .clip(RoundedCornerShape(6.dp))
                                .hazeSource(LocalHazeState.current)
                        )
                    }
                    Spacer(modifier = Modifier.height(46.dp))
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
        state = SpotListUiState.Success(emptyList(), "법정동")
    )
}

@Preview
@Composable
private fun SpotListLoadingScreenPreview() {
    SpotListScreen(
        state = SpotListUiState.Loading
    )
}