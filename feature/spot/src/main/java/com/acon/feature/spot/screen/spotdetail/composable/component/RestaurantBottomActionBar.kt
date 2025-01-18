package com.acon.feature.spot.screen.spotdetail.composable.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.R
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.theme.AconTheme
import com.acon.domain.model.spot.SpotDetailInfo

@Composable
fun RestaurantBottomActionBar(
    spotDetailInfo: SpotDetailInfo,
    onClickFindDirections: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
       modifier = modifier
           .fillMaxWidth()
           .background(AconTheme.color.Black)
           .padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 32.dp)
    ) {
        AconIconAndCount(
            aconIcon =  R.drawable.ic_local_acon_24,
            aconCount = spotDetailInfo.localAcornCount.toString(),
            aconContentDescription = "로컬 도토리",
        )
        Spacer(modifier = Modifier.width(8.dp))

        AconIconAndCount(
            aconIcon =  R.drawable.ic_visitor_acon_24,
            aconCount = spotDetailInfo.basicAcornCount.toString(),
            aconContentDescription = "여행자 도토리",
        )
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(24.dp))

        AconFilledLargeButton(
            text = "길 찾기",
            textStyle = AconTheme.typography.head8_16_sb,
            enabledBackgroundColor = AconTheme.color.Main_org1,
            disabledBackgroundColor = AconTheme.color.Main_org1,
            onClick = onClickFindDirections,
            //modifier = ,
            contentPadding = PaddingValues(horizontal = 68.dp, vertical = 10.dp),
        )
    }
}

@Composable
fun AconIconAndCount(
    @DrawableRes aconIcon: Int,
    aconCount: String,
    aconContentDescription: String,
) {
    Row (
        modifier = Modifier
            .padding(vertical = 10.dp)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = aconIcon),
            contentDescription = aconContentDescription
        )
        Text(
            text = aconCount,
            style = AconTheme.typography.body4_12_reg,
            color = AconTheme.color.White,
            modifier = Modifier
                .padding(start = 2.dp, top = 3.dp, bottom = 3.dp)
        )
    }
}

@Preview
@Composable
private fun RestaurantBottomActionBarPreview() {
    AconTheme {
        RestaurantBottomActionBar(
            spotDetailInfo = SpotDetailInfo(
                name = "",
                spotType = "CAFE",
                imageList = emptyList(),
                openStatus = true,
                address = "서울시 마포동 동교동",
                localAcornCount = 2221,
                basicAcornCount = 1111,
                latitude =  1.1,
                longitude = 1.1,
            ),
            onClickFindDirections = {}
        )
    }
}