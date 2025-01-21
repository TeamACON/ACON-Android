package com.acon.feature.upload.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.upload.LocationItem
import com.acon.feature.upload.SpotType

@Composable
fun LocationItem(
    locationItem: LocationItem,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 7.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = locationItem.name,
                style = AconTheme.typography.subtitle2_14_med,
                color = AconTheme.color.White
            )

            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(
                text = locationItem.address,
                style = AconTheme.typography.cap1_11_reg,
                color = AconTheme.color.Gray4
            )
        }

        Text(
            text = when (locationItem.spotType) {
                SpotType.CAFE -> "카페"
                SpotType.RESTAURANT -> "음식점"
            },
            style = AconTheme.typography.body3_13_reg,
            color = AconTheme.color.Gray4
        )
    }
}
