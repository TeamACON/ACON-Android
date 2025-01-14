package com.acon.core.designsystem.component.snackbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.theme.AconTheme

@Composable
fun AconTextSnackBarWithButton(
    message: String,
    buttonText: String = "button",
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        AconSnackBar {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = message,
                    style = AconTheme.typography.body3_13_reg,
                    color = AconTheme.color.White,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = buttonText,
                    style = AconTheme.typography.body3_13_reg,
                    color = AconTheme.color.Success_blue1,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAconTextSnackBarWithButton() {
    AconTheme {
        AconTextSnackBarWithButton(
            message = "메세지가 작성됩니다."
        ) {

        }
    }
}

@Preview
@Composable
private fun PreviewAconTextSnackBarWithButton2() {
    AconTheme {
        AconTextSnackBarWithButton(
            message = "모든 추천 리스트를 확인하셨습니다.",
            buttonText = "맵뷰로 보기"
        ) {

        }
    }
}
