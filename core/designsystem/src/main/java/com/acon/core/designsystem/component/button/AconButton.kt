package com.acon.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.theme.AconTheme

@Composable
fun AconButton(
    backGroundColor: Color,
    borderColor: Color = Color.Transparent,
    modifier: Modifier = Modifier,
    borderWidth: Dp = 0.dp,
    cornerRadius: Dp = 0.dp,
    paddingHorizontal: Dp = 0.dp,
    paddingVertical: Dp = 0.dp,
    onClick: () -> Unit,
    content: @Composable () -> Unit
    ) {
    Box(
      modifier = modifier
          .clip(RoundedCornerShape(cornerRadius))
          .background(color = backGroundColor)
          .clickable { onClick() }
          .border(width = borderWidth, color = borderColor, shape = RoundedCornerShape(cornerRadius))
          .padding(horizontal = paddingHorizontal, vertical = paddingVertical),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Preview
@Composable
internal fun PreviewAcornButton() {
    AconTheme {
        AconButton(
            backGroundColor = AconTheme.color.Main_org1,
            borderColor = AconTheme.color.Main_org1,
            modifier = Modifier,
            borderWidth = Dp.Hairline,
            cornerRadius = 4.dp,
            paddingHorizontal = 16.dp,
            paddingVertical = 14.dp,
            onClick = {},
            content = {}
        )
    }
}