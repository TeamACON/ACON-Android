package com.acon.feature.areaverification.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.vectorResource
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.localcerti.R

@Composable
fun DottoriSelectionBottomSheet(
    onDismiss: () -> Unit,
    onNavigateToNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(
                color = AconTheme.color.Gray9
            )
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .align(Alignment.CenterHorizontally)
                .size(width = 36.dp, height = 5.dp)
                .background(
                    color = AconTheme.color.Gray5,
                    shape = RoundedCornerShape(2.dp)
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = onDismiss) {
                Icon(
                    imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.ic_dissmiss_24),
                    contentDescription = stringResource(R.string.close),
                    tint = AconTheme.color.White
                )
            }
        }

        Text(
            text = stringResource(R.string.local_dottori_available),
            style = AconTheme.typography.head6_20_sb,
            color = AconTheme.color.White,
            modifier = Modifier.padding(start = 20.dp)
        )

        Text(
            text = stringResource(R.string.local_dottori_description),
            color = AconTheme.color.Gray3,
            modifier = Modifier
                .padding(start = 20.dp, top = 8.dp)
        )

        Spacer(modifier = Modifier.padding(top = 72.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_local_acon),
                    contentDescription = stringResource(R.string.local_dottori)
                )
                Text(
                    text = stringResource(R.string.local_dottori),
                    style = AconTheme.typography.subtitle1_16_med,
                    color = AconTheme.color.Gray1
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_normal_acon),
                    contentDescription = stringResource(R.string.normal_dottori),
                )
                Text(
                    text = stringResource(R.string.normal_dottori),
                    style = AconTheme.typography.subtitle1_16_med,
                    color = AconTheme.color.Gray1
                )
            }
        }

        Spacer(modifier = Modifier.padding(top = 96.dp))
        AconFilledLargeButton(
            text = stringResource(R.string.start),
            textStyle = AconTheme.typography.head8_16_sb,
            enabledBackgroundColor = AconTheme.color.Gray5,
            disabledBackgroundColor = AconTheme.color.Gray8,
            enabledTextColor = AconTheme.color.White,
            onClick = {
                onDismiss()
                onNavigateToNext()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
                .padding(horizontal = 20.dp)
        )
    }
}
