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
fun AconOutlinedLargeButton(
    text: String,
    enabledBorderColor: Color,
    enabledBackgroundColor: Color,
    disabledBorderColor: Color,
    disabledBackgroundColor: Color,
    textColor: Color = AconTheme.color.White,
    textStyle: TextStyle = AconTheme.typography.head8_16_sb,
    isEnabled: Boolean = true,
    borderWidth: Dp = 1.dp,
    cornerRadius: Dp = 6.dp,
    modifier: Modifier = Modifier,
    paddingHorizontal: Dp = 16.dp,
    paddingVertical: Dp = 14.dp,
    onClick: () -> Unit,
) {
    AconButton (
        backGroundColor = if(isEnabled) enabledBackgroundColor else disabledBackgroundColor,
        borderColor = if(isEnabled) enabledBorderColor else disabledBorderColor,
        borderWidth = borderWidth,
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
internal fun PreviewAconOutlinedButton() {
    AconTheme {
        AconOutlinedLargeButton(
            text = "button",
            textStyle = AconTheme.typography.head8_16_sb,
            textColor = AconTheme.color.Gray3,
            enabledBorderColor = AconTheme.color.Gray5,
            enabledBackgroundColor = AconTheme.color.Gray9,
            disabledBorderColor = AconTheme.color.Gray6,
            disabledBackgroundColor =  AconTheme.color.Gray8,
            isEnabled = true,
            borderWidth = 1.dp,
            cornerRadius = 6.dp,
            modifier = Modifier,
            paddingHorizontal = 16.dp,
            paddingVertical = 14.dp,
            onClick = {}
        )
    }
}