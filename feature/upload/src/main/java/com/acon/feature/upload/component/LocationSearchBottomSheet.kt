package com.acon.feature.upload.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.blur.LocalHazeState
import com.acon.core.designsystem.blur.defaultHazeEffect
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.upload.LocationItem
import com.acon.feature.upload.MockSpotData
import com.acon.feature.upload.R
import com.acon.feature.upload.SpotType

@Composable
fun LocationSearchBottomSheet(
    onDismiss: () -> Unit,
    onLocationSelected: (LocationItem) -> Unit,
) {
    var searchText by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchResults = remember(searchText) { MockSpotData.searchSpots(searchText) }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(AconTheme.color.Gray9.copy(alpha = 0.5f))
            .defaultHazeEffect(
                hazeState = LocalHazeState.current,
                tintColor = AconTheme.color.Gray8,
                alpha = 0.7f,
                blurRadius = 20.dp
            )
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .align(Alignment.CenterHorizontally)
                .size(width = 36.dp, height = 5.dp)
        )

        Spacer(modifier = Modifier.padding(top = 12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                // Empty space for left side
            }

            Text(
                text = "장소등록",
                style = AconTheme.typography.head8_16_sb,
                color = AconTheme.color.White,
            )

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "완료",
                    style = AconTheme.typography.body2_14_reg,
                    color = AconTheme.color.Gray5,
                    modifier = Modifier
                        .clickable { onDismiss() }
                        .padding(end = 28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.padding(top = 30.dp))

        AconSearchTextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = "가게명을 입력해주세요",
            modifier = Modifier.padding(horizontal = 20.dp),
            focusRequester = focusRequester
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 20.dp)
        ) {
            if (searchText.isEmpty()) {
                EmptySearchScreen()
            } else if (searchResults.isEmpty()) {
                NoResultsScreen()
            } else {
                LazyColumn {
                    items(searchResults) { location ->
                        LocationItem(
                            locationItem = location,
                            onClick = { onLocationSelected(location) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptySearchScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "장소를 검색해보세요",
            style = AconTheme.typography.body2_14_reg,
            color = AconTheme.color.Gray5
        )
    }
}

@Composable
private fun NoResultsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_no_location),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = "앗! 일치하는 장소가 없어요.",
                style = AconTheme.typography.body2_14_reg,
                color = AconTheme.color.Gray4
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1C1C1E)
@Composable
private fun LocationItemCafePreview() {
    AconTheme {
        Column(
            modifier = Modifier.background(AconTheme.color.Gray9)
        ) {
            LocationItem(
                locationItem = LocationItem(
                    spotId = 1,
                    name = "스타벅스 강남역점",
                    address = "서울 강남구 강남대로 390",
                    spotType = SpotType.CAFE
                ),
                onClick = {}
            )
        }
    }
}
