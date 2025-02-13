package com.acon.android.core.designsystem.component.dialog

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.android.core.designsystem.component.button.AconFilledMediumButton
import com.acon.android.core.designsystem.R
import com.acon.android.core.designsystem.theme.AconTheme


@Composable
fun AconOneButtonDialog(
    title: String,
    content: String,
    buttonContent: String,
    @DrawableRes contentImage: Int? = null,
    onDismissRequest: () -> Unit,
    onClickConfirm: () -> Unit,
    isImageEnabled: Boolean = false,
) {
    AconDialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(AconTheme.color.Gray8)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(isImageEnabled && contentImage != null) {
                Image(
                    imageVector = ImageVector.vectorResource(contentImage),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            Text(
                text = title,
                style = AconTheme.typography.head8_16_sb,
                textAlign = TextAlign.Center,
                color = AconTheme.color.White
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = content,
                style = AconTheme.typography.body2_14_reg,
                textAlign = TextAlign.Center,
                color = AconTheme.color.Gray3
            )
            Spacer(modifier = Modifier.height(16.dp))

            AconFilledMediumButton(
                text = buttonContent,
                textStyle = AconTheme.typography.body2_14_reg,
                enabledBackgroundColor = AconTheme.color.Gray5,
                disabledBackgroundColor = AconTheme.color.Gray5,
                onClick = onClickConfirm,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAconOneButtonDialog() {
    AconOneButtonDialog(
        title = "인증 실패",
        content = "인증 가능한 지역이 없어요.\n현재 위치로 인증을 진행할 수 있어요.",
        buttonContent = "설정으로 가기",
        contentImage = R.drawable.ic_review_g_40,
        onDismissRequest = {},
        onClickConfirm = {},
        isImageEnabled = false
    )
}
