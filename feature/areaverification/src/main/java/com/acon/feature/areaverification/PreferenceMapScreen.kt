package com.acon.feature.areaverification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.component.dialog.AconTwoButtonDialog
import com.acon.core.designsystem.component.topbar.AconTopBar
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.areaverification.component.DottoriSelectionBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceMapScreen(
    onConfirmClick: () -> Unit,
    onNavigateToNext: () -> Unit,
    onBackClick: () -> Unit,
    latitude: Double,
    longitude: Double,
    modifier: Modifier = Modifier,
    viewModel: AreaVerificationViewModel = hiltViewModel()
) {
    var showExitDialog by remember { mutableStateOf(false) }
    var currentLatitude by remember { mutableDoubleStateOf(latitude) }
    var currentLongitude by remember { mutableDoubleStateOf(longitude) }
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    if (showExitDialog) {
        AconTwoButtonDialog(
            title = "동네 인증을 그만둘까요?",
            content = "동네 인증만이 남아있어요!\n1분 내로 빠르게 끝내실 수 있어요.",
            leftButtonContent = "그만두기",
            rightButtonContent = "계속하기",
            contentImage = com.acon.core.designsystem.R.drawable.ic_review_g_40,
            onDismissRequest = { showExitDialog = false },
            onClickLeft = onBackClick,
            onClickRight = { showExitDialog = false },
            isImageEnabled = false
        )
    }

    if (state.verifiedArea != null)  {
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
        )

        ModalBottomSheet(
            onDismissRequest = { viewModel.resetVerifiedArea() },
            containerColor = Color.Transparent,
            dragHandle = null,
            sheetState = sheetState,
        ) {
            DottoriSelectionBottomSheet(
                onDismiss = { viewModel.resetVerifiedArea() },
                onNavigateToNext = onNavigateToNext
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AconTheme.color.Gray9)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        AconTopBar(
            leadingIcon = {
                IconButton(
                    onClick = { showExitDialog = true }
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(
                            id = com.acon.core.designsystem.R.drawable.ic_arrow_left_28
                        ),
                        contentDescription = "Back",
                    )
                }
            },
            content = {
                Text(
                    text = stringResource(R.string.check_location_on_map),
                    style = AconTheme.typography.title2_20_b,
                    color = AconTheme.color.White
                )
            },
            isStartAlignment = true
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            LocationMapScreen(
                onLocationObtained = { lat, lng ->
                    currentLatitude = lat
                    currentLongitude = lng
                },
                initialLatitude = latitude,
                initialLongitude = longitude,
                modifier = Modifier.fillMaxSize()
            )
        }

        AconFilledLargeButton(
            text = stringResource(R.string.verify_complete),
            textStyle = AconTheme.typography.head8_16_sb,
            enabledBackgroundColor = AconTheme.color.Gray5,
            disabledBackgroundColor = AconTheme.color.Gray8,
            enabledTextColor = AconTheme.color.White,
            onClick = {
                viewModel.verifyArea(currentLatitude, currentLongitude)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp, bottom = 40.dp)
        )
    }
}
