package com.acon.feature.upload.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.noRippleClickable
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.upload.LocationItem
import com.acon.feature.upload.R

@Composable
fun LocationSelectionButton(
    selectedLocation: LocationItem?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (selectedLocation == null) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .border(
                    width = 1.dp,
                    color = AconTheme.color.Gray5,
                    shape = RoundedCornerShape(4.dp)
                )
                .background(
                    color = AconTheme.color.Gray9,
                    shape = RoundedCornerShape(4.dp)
                )
                .noRippleClickable(onClick = onClick)
                .padding(vertical = 14.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector =  ImageVector.vectorResource(id = R.drawable.and_ic_add_20),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "장소 등록하기",
                style = AconTheme.typography.subtitle1_16_med,
                color = AconTheme.color.White
            )
        }
    } else {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(
                    color = AconTheme.color.Gray8,
                    shape = RoundedCornerShape(4.dp)
                )
                .noRippleClickable(onClick = onClick)
                .padding(vertical = 14.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.and_ic_location_gray_28),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = selectedLocation.name,
                style = AconTheme.typography.subtitle1_16_med,
                color = AconTheme.color.White
            )
        }
    }
}
