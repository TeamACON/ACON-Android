package com.acon.feature.spot.screen.spotdetail.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.R
import com.acon.core.designsystem.theme.AconTheme
import com.acon.domain.type.SpotType
import com.acon.feature.spot.getNameResId

@Composable
fun SpotDetailTopBar(
    storeName: String,
    spotType: SpotType,
    modifier: Modifier = Modifier,
    onLeadingIconClicked: () -> Unit = {},
    leadingIcon: @Composable () -> Unit = {
        Image (
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left_24),
            contentDescription = "뒤로가기",
            modifier = Modifier
                .padding(start = 20.dp, top = 2.dp, bottom = 2.dp)
                .clickable { onLeadingIconClicked() }
        )
    },
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AconTheme.color.Black)
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingIcon()
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = storeName,
            style = AconTheme.typography.title2_20_b,
            color = AconTheme.color.White
        )
        Text(
            text = stringResource(spotType.getNameResId()),
            style = AconTheme.typography.body2_14_reg,
            color = AconTheme.color.Gray4,
            modifier = Modifier
                .padding(start = 4.dp, top = 4.dp, bottom = 4.dp)
        )
    }
}


@Composable
@Preview
private fun SpotDetailTopBarPreview(){
    AconTheme {
        SpotDetailTopBar(
            storeName = "냐옹이",
            spotType = SpotType.CAFE
        )
    }
}