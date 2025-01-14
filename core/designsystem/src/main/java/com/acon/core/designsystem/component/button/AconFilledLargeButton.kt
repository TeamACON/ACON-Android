package com.acon.core.designsystem.component.button

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.theme.AconTheme

@Composable
fun AconFilledLargeButton(
    text: String,
    textStyle: TextStyle,
    enabledBackgroundColor: Color,
    disabledBackgroundColor: Color,
    textColor: Color = AconTheme.color.White,
    isEnabled: Boolean = true,
    cornerRadius: Dp = 6.dp,
    modifier: Modifier = Modifier,
    paddingHorizontal: Dp = 16.dp,
    paddingVertical: Dp = 14.dp,
    onClick: () -> Unit,
) {
    AconButton(
        backGroundColor = if(isEnabled) enabledBackgroundColor else disabledBackgroundColor,
        modifier = modifier,
        cornerRadius = cornerRadius,
        paddingHorizontal = paddingHorizontal,
        paddingVertical = paddingVertical,
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = textStyle,
            color = textColor
        )
    }
}

@Preview
@Composable
internal fun PreviewAconLargeButton() {
    AconTheme {
        AconFilledLargeButton(
            text = "button",
            textStyle = AconTheme.typography.head8_16_sb,
            textColor = AconTheme.color.White,
            enabledBackgroundColor = AconTheme.color.Main_org1,
            disabledBackgroundColor =  AconTheme.color.Main_org1,
            isEnabled = true,
            cornerRadius = 6.dp,
            modifier = Modifier,
            paddingHorizontal = 16.dp,
            paddingVertical = 14.dp,
            onClick = {}
        )
    }
}