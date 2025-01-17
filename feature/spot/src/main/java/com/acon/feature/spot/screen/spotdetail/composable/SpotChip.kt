package com.acon.feature.spot.screen.spotdetail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.component.chip.AconChip
import com.acon.core.designsystem.theme.AconTheme

@Composable
fun SpotChip(
    title: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = AconTheme.typography.body2_14_reg,
) {
    val containerColor =
        if (selected)
            AconTheme.color.Main_org35
        else AconTheme.color.Gray6

    val textColor =
        if (selected)
            AconTheme.color.White
        else AconTheme.color.Gray5

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(containerColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 7.dp, horizontal = 16.dp),
            text = title,
            color = textColor,
            style = textStyle
        )
    }
}

@Preview
@Composable
private fun SpotChipPreview() {
    SpotChip(
        title = "영업전",
        selected = false,
    )
}

@Preview
@Composable
private fun SelectedSpotChipPreview() {
    SpotChip(
        title = "영업중",
        selected = true,
    )
}
