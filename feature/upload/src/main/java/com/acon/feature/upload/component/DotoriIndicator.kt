package com.acon.feature.upload.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun DotoriIndicator(
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clickable(onClick = onClick)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(
                id = if (isSelected) {
                    com.acon.core.designsystem.R.drawable.ic_review_w_40
                } else {
                    com.acon.core.designsystem.R.drawable.ic_review_g_40
                }
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}
