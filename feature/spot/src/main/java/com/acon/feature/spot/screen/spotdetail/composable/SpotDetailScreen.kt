package com.acon.feature.spot.screen.spotdetail.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.theme.AconTheme
import com.acon.domain.model.spot.Menu
import com.acon.domain.model.spot.SpotDetailInfo
import com.acon.domain.type.SpotType
import com.acon.feature.spot.screen.spotdetail.composable.component.MenuItem
import com.acon.feature.spot.screen.spotdetail.composable.component.MoveToTopFAB
import com.acon.feature.spot.screen.spotdetail.composable.component.RestaurantBottomActionBar
import com.acon.feature.spot.screen.spotdetail.composable.component.SpotChip
import com.acon.feature.spot.screen.spotdetail.composable.component.SpotDetailTopBar
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

@Composable
fun SpotDetailScreen(
    menuList: PersistentList<Menu>, // 임시 변수 (추 후 뷰모델에서 처리)
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AconTheme.color.Gray9)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        SpotDetailTopBar(
            storeName = "고양이",
            spotType = SpotType.CAFE,
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
                            .height(196.dp) // 임시 수치
                            .background(AconTheme.color.Gray5)
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(
                                com.acon.core.designsystem.R.drawable.ic_error_1_120
                            ),
                            contentDescription = ""
                        )
//            AsyncImage(
//                model = menu.image,
//                contentDescription = "",
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.Crop,
//            )
                    }
                }

                item {
                    SpotChip(
                        title = "영업중",
                        selected = true,
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
                            text = "경북 문경시 문경읍 세제로 000",
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
                    items = menuList,
                    key = { index, menu ->
                        menu.id
                    }
                ) { index, menu ->
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
                            Log.d("로그", "scrollIsAtTop : $scrollIsAtTop")
                        }
                    }
                },
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
            )
        }
        RestaurantBottomActionBar(
            spotDetailInfo = SpotDetailInfo(
                name = "",
                spotType = "CAFE",
                imageList = emptyList(),
                openStatus = true,
                address = "서울시 마포동 동교동",
                localAcornCount = 2221,
                basicAcornCount = 1111,
                latitude = 1.1,
                longitude = 1.1,
            ),
            onClickFindDirections = {},
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}

@Preview
@Composable
private fun SpotDetailScreenPreview() {
    val menuList = persistentListOf(
        Menu("1", "Americano", 4000, ""),
        Menu("2", "Latte", 4500, ""),
        Menu("3", "Mocha", 5000, ""),
        Menu("4", "고양이", 400000, ""),
        Menu("5", "강아지", 45000, ""),
        Menu("6", "아르마딜로", 5000000, ""),
        Menu("7", "기린", 4500, ""),
        Menu("8", "흰꼬리 긴 원숭이", 5000, ""),
        Menu("9", "Americano", 4000, ""),
    )
    AconTheme {
        SpotDetailScreen(
            menuList = menuList,
            onNavigateToSpotListScreen = {}
        )
    }
}
