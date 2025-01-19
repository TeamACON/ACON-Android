package com.acon.feature.spot.screen.spotdetail.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.acon.core.designsystem.theme.AconTheme
import com.acon.domain.model.spot.SpotDetailInfo
import com.acon.domain.type.SpotType
import com.acon.feature.spot.screen.spotdetail.SpotDetailUiState
import com.acon.feature.spot.screen.spotdetail.composable.component.MenuItem
import com.acon.feature.spot.screen.spotdetail.composable.component.MoveToTopFAB
import com.acon.feature.spot.screen.spotdetail.composable.component.RestaurantBottomActionBar
import com.acon.feature.spot.screen.spotdetail.composable.component.SpotChip
import com.acon.feature.spot.screen.spotdetail.composable.component.SpotDetailTopBar
import kotlinx.coroutines.launch

@Composable
internal fun SpotDetailScreen(
    state: SpotDetailUiState,
    modifier: Modifier = Modifier,
    onNavigateToSpotListScreen: () -> Unit = {},
) {
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val scrollIsAtTop by remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex == 0 && scrollState.firstVisibleItemScrollOffset == 0
        }
    }

    Surface(
        modifier = modifier
            .background(AconTheme.color.Gray9)
    ) {
        when(state) {
            is SpotDetailUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    SpotDetailTopBar(
                        storeName = state.spotDetailInfo.name,
                        spotType = state.spotDetailInfo.spotType,
                        onLeadingIconClicked = onNavigateToSpotListScreen,
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        LazyColumn(
                            state = scrollState,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(90f / 49f)
                                        //.height(196.dp) // 임시 수치
                                        .background(AconTheme.color.Gray5)
                                ) {
                                    AsyncImage(
                                        model = state.spotDetailInfo.imageList[0],
                                        contentDescription = "가게 이미지",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop,
                                    )
                                }
                            }

                            item {
                                SpotChip(
                                    title = state.spotDetailInfo.name,
                                    selected = state.spotDetailInfo.openStatus,
                                    modifier = Modifier
                                        .padding(start = 20.dp, top = 16.dp)
                                )
                            }
                            item {
                                Row(
                                    modifier = Modifier
                                        .padding(start = 20.dp, top = 8.dp)
                                ) {
                                    Image(
                                        imageVector = ImageVector.vectorResource(
                                            com.acon.core.designsystem.R.drawable.ic_location_gray_16
                                        ),
                                        contentDescription = "위치 아이콘"
                                    )
                                    Text(
                                        text = state.spotDetailInfo.address,
                                        style = AconTheme.typography.body4_12_reg,
                                        color = AconTheme.color.Gray4,
                                        modifier = Modifier
                                            .padding(start = 2.dp, top = 1.dp, bottom = 1.dp)
                                    )
                                }
                            }

                            item {
                                Spacer(modifier = Modifier.height(40.dp))
                            }

                            stickyHeader {
                                Column(
                                    modifier = Modifier
                                        .padding(start = 20.dp)
                                        .width(intrinsicSize = IntrinsicSize.Max),
                                ) {
                                    Text(
                                        text = "메뉴",
                                        style = AconTheme.typography.subtitle2_14_med,
                                        color = AconTheme.color.White,
                                        modifier = Modifier
                                            .padding(horizontal = 20.dp, vertical = 8.dp)
                                    )
                                    HorizontalDivider(
                                        color = AconTheme.color.White,
                                        thickness = 2.dp,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                    )
                                }
                            }

                            item {
                                Spacer(modifier = Modifier.height(17.dp))
                            }

                            itemsIndexed(
                                items = state.spotDetailMenuList,
                                key = { _, menu ->
                                    menu.id
                                }
                            ) { _, menu ->
                                MenuItem(
                                    menu = menu,
                                    modifier = Modifier
                                        .padding(horizontal = 20.dp)
                                )
                            }
                        }

                        MoveToTopFAB(
                            onClickFab = {
                                scope.launch {
                                    if (!scrollIsAtTop) {
                                        scrollState.animateScrollToItem(index = 0)
                                    }
                                }
                            },
                            modifier = Modifier
                                .align(alignment = Alignment.BottomEnd)
                                .padding(end = 20.dp, bottom = 16.dp)
                        )
                    }
                    RestaurantBottomActionBar(
                        localAcornCount = state.spotDetailInfo.localAcornCount,
                        basicAcornCount = state.spotDetailInfo.basicAcornCount,
                        onClickFindDirections = {},
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            is SpotDetailUiState.Loading -> {

            }
            is SpotDetailUiState.LoadFailed -> {
                // TODO : 로드 실패 뷰
            }
        }

    }
}

@Preview
@Composable
private fun SpotDetailScreenPreview() {
    AconTheme {
        SpotDetailScreen(
            state = SpotDetailUiState.Success(
                SpotDetailInfo(
                     id = 1,
                     name = "",
                     spotType= SpotType.CAFE,
                     imageList = emptyList(),
                     openStatus = true,
                     address = "경기도 고양시 고양구 고양이",
                     localAcornCount = 1,
                     basicAcornCount = 1,
                     latitude = 1.11,
                     longitude = 1.11
                ),
                spotDetailMenuList = emptyList()
            ),
            onNavigateToSpotListScreen = {}
        )
    }
}

@Preview
@Composable
private fun SpotDetailLoadingScreenPreview() {
    AconTheme {
//        SpotDetailScreen(
//            state = SpotDetailUiState.Success(SpotDetailInfo(), SpotDetailMenu()),
//            onNavigateToSpotListScreen = {}
//        )
    }
}
