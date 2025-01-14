package com.acon.core.designsystem.component.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.theme.AconColors

@Composable
fun AconSnackBar(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(color = AconColors.Gray8)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun AconSnackBarPreview() {
    AconSnackBar {
        Text(
            text = "기본 스낵바 메시지입니다.",
            color = AconColors.White
        )
    }
}
