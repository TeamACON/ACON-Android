package com.acon.feature.areaverification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.theme.AconTheme

@Composable
fun PreferenceMapScreen(
    onConfirmClick: () -> Unit,
    latitude: Double,
    longitude: Double,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AconTheme.color.Gray9)
    ) {
        Text(
            text = "지도에서 위치 확인하기",
            style = AconTheme.typography.head6_20_sb,
            color = AconTheme.color.Gray1,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            LocationMapScreen(
                onLocationObtained = { _, _ -> /* 여기서는 위치 업데이트가 필요 없을 듯*/ },
                initialLatitude = latitude,
                initialLongitude = longitude,
                modifier = Modifier.fillMaxSize()
            )
        }

        AconFilledLargeButton(
            text = "인증완료",
            textStyle = AconTheme.typography.head8_16_sb,
            enabledBackgroundColor = AconTheme.color.Gray5,
            disabledBackgroundColor = AconTheme.color.Gray8,
            enabledTextColor = AconTheme.color.White,
            onClick = onConfirmClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        )
    }
}
