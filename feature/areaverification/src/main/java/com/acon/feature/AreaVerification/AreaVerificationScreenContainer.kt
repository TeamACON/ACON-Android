package com.acon.feature.AreaVerification

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.AreaVerification.component.AreaVerificationButton
import android.provider.Settings
import com.acon.core.designsystem.component.dialog.AconOneButtonDialog
import com.acon.core.utils.feature.permission.CheckAndRequestLocationPermission

@Composable
fun AreaVerificationScreenContainer(
    onNewAreaClick: (Double, Double) -> Unit,
    onNextScreen: (Double, Double) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AreaVerificationViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    CheckAndRequestLocationPermission {
        if (uiState.isLocationObtained) {
            onNewAreaClick(uiState.latitude, uiState.longitude)
        }
    }

    AreaVerificationScreen(
        uiState = uiState,
        onNewLocationSelected = { viewModel.onNewLocationSelected() },
        onDismissPermissionDialog = { viewModel.updateShowPermissionDialog(false) },
        onPermissionSettingClick = {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
            }
            context.startActivity(intent)
            viewModel.updateShowPermissionDialog(false)
        },
        onNextButtonClick = {
            viewModel.onNewLocationSelected()
            onNextScreen(uiState.latitude, uiState.longitude)
        },
        onLocationObtained = { latitude, longitude ->
            viewModel.updateLocation(latitude, longitude)
        },
        modifier = modifier
    )
}

@Composable
fun AreaVerificationScreen(
    uiState: AreaVerificationUiState,
    onNewLocationSelected: () -> Unit,
    onDismissPermissionDialog: () -> Unit,
    onPermissionSettingClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    onLocationObtained: (Double, Double) -> Unit,
    modifier: Modifier = Modifier
) {
    if (uiState.showPermissionDialog) {
        AconOneButtonDialog(
            title = "위치 권한 필요",
            content = "정확한 위치 확인이 필요합니다.\n설정에서 '정확한 위치' 권한을 허용해주세요.",
            buttonContent = "설정으로 이동",
            onDismissRequest = onDismissPermissionDialog,
            onClickConfirm = onPermissionSettingClick,
            isImageEnabled = false
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AconTheme.color.Gray9)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "로컬 맛집 추천을 위해\n지역 인증이 필요해요.",
                style = AconTheme.typography.head6_20_sb,
                color = AconTheme.color.Gray1
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "지주 거는 동네로 지역 인증을 해보세요",
                style = AconTheme.typography.body3_13_reg,
                color = AconTheme.color.Gray3
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AreaVerificationButton(
                    selected = uiState.isNewLocationSelected,
                    onClick = onNewLocationSelected,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        AconFilledLargeButton(
            text = "다음",
            textStyle = AconTheme.typography.head8_16_sb,
            enabledBackgroundColor = AconTheme.color.Gray5,
            disabledBackgroundColor = AconTheme.color.Gray8,
            enabledTextColor = AconTheme.color.White,
            disabledTextColor = AconTheme.color.Gray6,
            onClick = onNextButtonClick,
            isEnabled = uiState.isNewLocationSelected,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )
    }
}
