package com.acon.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.theme.AconTheme

@Composable
fun AconButton(
    text: String,
    backGroundColor: Color,
    borderColor: Color,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    borderWidth: Dp = 0.dp,
    cornerRadius: Dp = 0.dp,
    paddingHorizontal: Dp = 0.dp,
    paddingVertical: Dp = 0.dp,
    textStyle: TextStyle = TextStyle.Default,
    onClick: suspend () -> Unit,
    ) {
    Box(
      modifier = modifier
          .clip(RoundedCornerShape(cornerRadius))
          .background(color = backGroundColor)
          .border(width = borderWidth, color = borderColor, shape = RoundedCornerShape(cornerRadius))
          .padding(horizontal = paddingHorizontal, vertical = paddingVertical),
        contentAlignment = Alignment.Center
    ) {

    }
}

@Preview
@Composable
internal fun PreviewAcornButton() {
    AconTheme {
        AconButton(
            text = "button",
            backGroundColor = AconTheme.color.o,
            textStyle = AconTheme.typography.cap1_11_reg
        )
    }
}