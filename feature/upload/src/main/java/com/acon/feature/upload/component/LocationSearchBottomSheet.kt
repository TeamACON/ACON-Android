package com.acon.feature.upload.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acon.core.designsystem.blur.LocalHazeState
import com.acon.core.designsystem.blur.defaultHazeEffect
import com.acon.core.designsystem.component.chip.AconChip
import com.acon.core.designsystem.theme.AconTheme
import com.acon.core.map.ProceedWithLocation
import com.acon.domain.model.upload.SpotListItem
import com.acon.feature.upload.R
import com.acon.feature.upload.UploadIntent
import com.acon.feature.upload.UploadViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LocationSearchBottomSheet(
    viewModel: UploadViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
    onLocationSelected: (SpotListItem) -> Unit,
) {
    var searchText by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    LaunchedEffect(searchText) {
        viewModel.searchFlow.emit(searchText)
    }

    ProceedWithLocation { location ->
        viewModel.onIntent(
            UploadIntent.LoadSuggestions(
                latitude = location.latitude,
                longitude = location.longitude
            )
        )
    }

//    LaunchedEffect(Unit) {
//        val mockLatitude = 37.5665 // 서울
//        val mockLongitude = 126.9780
//        viewModel.onIntent(
//            UploadIntent.LoadSuggestions(
//                latitude = mockLatitude,
//                longitude = mockLongitude
//            )
//        )
//    }

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
                        .clickable {
                            onDismiss()
                            state.selectedLocation?.let { onLocationSelected(it) }
                        }
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
            when {
                searchText.isEmpty() -> {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        state.suggestions.forEach { suggestion ->
                            AconChip(
                                title = suggestion.spotName,
                                isSelected = false,
                                onClick = {
                                    searchText = suggestion.spotName
                                    focusRequester.requestFocus()
                                }
                            )
                        }
                    }
                }

                searchText.isNotEmpty() && state.searchResults.isEmpty() -> {
                    NoResultsScreen()
                }

                searchText.isNotEmpty() -> {
                    LazyColumn {
                        items(state.searchResults.size) { index ->
                            LocationItem(
                                locationItem = state.searchResults[index],
                                onClick = {
                                    viewModel.onIntent(UploadIntent.SelectLocation(state.searchResults[index]))
                                    onLocationSelected(state.searchResults[index])
                                    onDismiss()
                                }
                            )
                        }
                    }
                }
            }
        }
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

