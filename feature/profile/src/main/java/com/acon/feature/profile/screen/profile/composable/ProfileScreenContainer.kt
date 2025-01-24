package com.acon.feature.profile.screen.profile.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreenContainer(
    modifier: Modifier = Modifier
) {

    ProfileScreen(
        modifier = modifier.padding(top = 58.dp).fillMaxSize()
    )
}