package com.acon.feature.spot.screen.spotdetail.composable.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.dropShadow
import com.acon.core.designsystem.noRippleClickable
import com.acon.core.designsystem.theme.AconTheme

@Composable
fun MoveToTopFAB(
    onClickFab: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(44.dp)
            .background(
                color = AconTheme.color.Gray7,
                shape = CircleShape
            )
            .border(
                width = 1.dp,
                color = AconTheme.color.Gray6,
                shape = CircleShape
            )
            .dropShadow(
                shape = CircleShape,
                color = AconTheme.color.Fab_shaodw_1,
                blur = 4.dp,
                offsetX = (0).dp,
                offsetY = 2.dp,
            )
            .noRippleClickable { onClickFab() }
    ) {
        Image(
            imageVector = ImageVector.vectorResource(
                com.acon.core.designsystem.R.drawable.ic_arrow_up_24
            ),
            contentDescription = "",
            modifier = Modifier
                .padding(all = 10.dp),
        )
    }
}

@Preview
@Composable
private fun MoveToTopFABPreview() {
    AconTheme {
        MoveToTopFAB(
            onClickFab = {}
        )
    }
}
