package com.acon.core.designsystem.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.R
import com.acon.core.designsystem.theme.AconTheme

@Composable
fun AconGoogleLoginButton(
    backgroundColor: Color = AconTheme.color.White,
    borderColor: Color = AconTheme.color.Gray1,
    modifier: Modifier = Modifier,
    borderWidth: Dp = 1.dp,
    cornerRadius: Dp = 6.dp,
    textStyle: TextStyle = AconTheme.typography.subtitle2_14_med,
    textColor: Color = AconTheme.color.Black,
    onClick: () -> Unit = {}
) {
    AconButton(
        backGroundColor = backgroundColor,
        borderColor = borderColor,
        modifier = modifier
            .fillMaxWidth(),
        borderWidth = borderWidth,
        cornerRadius = cornerRadius,
        contentPadding = PaddingValues(horizontal = 93.dp, vertical = 12.dp),
        onClick = onClick
    ) {
        Row(
           verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_google),
                contentDescription = "구글 로그인 버튼"
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = "Google로 계속하기",
                style = textStyle,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
    }

}

@Preview
@Composable
internal fun PreviewAconGoogleLoginButton() {
    AconTheme {
        AconGoogleLoginButton(
            modifier = Modifier,
            textColor = AconTheme.color.Black,
            backgroundColor = AconTheme.color.White,
            onClick = {},
        )
    }
}