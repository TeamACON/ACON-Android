package com.acon.feature.areaverification.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.component.button.AconButton
import com.acon.core.designsystem.component.radiobutton.AconRadioButton
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.localcerti.R

@Composable
fun AreaVerificationButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    AconButton(
        backGroundColor = if (isEnabled) AconTheme.color.Gray9 else AconTheme.color.Gray8,
        borderColor = if (isEnabled) AconTheme.color.Gray8 else AconTheme.color.Gray7,
        borderWidth = 1.dp,
        modifier = modifier,
        cornerRadius = 6.dp,
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 14.dp),
        onClick = onClick,
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            AconRadioButton(
                selected = selected,
                onClick = onClick,
                enabled = isEnabled
            )
            Spacer(modifier = Modifier.width(8.dp))
            Row {
                Text(
                    text = stringResource(R.string.area_verification_new),
                    style = AconTheme.typography.head8_16_sb,
                    color = AconTheme.color.Main_org1
                )
                Text(
                    text = stringResource(R.string.area_verification_certify_my_area),
                    style = AconTheme.typography.head8_16_sb,
                    color = AconTheme.color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAreaVerificationButton() {
    AconTheme {
        AreaVerificationButton(
            selected = true,
            onClick = {},
            isEnabled = true
        )
    }
}

@Preview
@Composable
private fun PreviewUnselectedAreaVerificationButton() {
    AconTheme {
        AreaVerificationButton(
            selected = false,
            onClick = {},
            isEnabled = true
        )
    }
}

@Preview
@Composable
private fun PreviewDisabledAreaVerificationButton() {
    AconTheme {
        AreaVerificationButton(
            selected = false,
            onClick = {},
            isEnabled = false
        )
    }
}
