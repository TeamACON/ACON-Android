package com.acon.feature.profile.screen.profile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.profile.R

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {

    var descWidthPx by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier,
    ) {

        Text(
            text = "동네 이름", // TODO 법정동 동네 이름
            style = AconTheme.typography.head5_22_sb,
            color = AconTheme.color.White,
            modifier = Modifier.padding(start = 20.dp)
        )

        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier = with(LocalDensity.current) { Modifier.width(descWidthPx.toDp() + 20.dp) },
                imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.img_upload_finish_320),
                contentDescription = null,
            )
            Text(
                modifier = Modifier.onSizeChanged {
                    descWidthPx = it.width
                }.padding(top = 10.dp, bottom = 20.dp),
                text = stringResource(R.string.alert_profile_preparing),
                style = AconTheme.typography.subtitle1_16_med,
                color = AconTheme.color.Gray4,
            )
        }
    }
}