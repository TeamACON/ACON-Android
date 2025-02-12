package com.acon.feature.profile.screen.profile.composable.screen.composable

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.feature.profile.screen.profile.composable.screen.ProfileUiSideEffect
import com.acon.feature.profile.screen.profile.composable.screen.ProfileViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ProfileScreenContainer(
    modifier: Modifier = Modifier,
    onNavigateToSettingsScreen: () -> Unit = {},
    onNavigateToProfileEditScreen: () -> Unit = {},
    onNavigateToSignInScreen: () -> Unit = {},
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.collectAsState()

    ProfileScreen(
        state = state,
        modifier = modifier.fillMaxSize(),
        onSettings = viewModel::onSettings,
        onEditProfile = viewModel::onEditProfile,
        onGoogleSignIn = viewModel::onGoogleSignIn,
        onTermOfUse = viewModel::onTermOfUse,
        onPrivatePolicy = viewModel::onPrivatePolicy,
        onBottomSheetShowStateChange = viewModel::onBottomSheetShowStateChange,
    )

    viewModel.collectSideEffect {
        when(it) {
            is ProfileUiSideEffect.OnNavigateToSettingsScreen -> { onNavigateToSettingsScreen() }
            is ProfileUiSideEffect.OnNavigateToProfileEditScreen -> { onNavigateToProfileEditScreen() }
            is ProfileUiSideEffect.OnNavigateToSignInScreen -> { onNavigateToSignInScreen() }
            is ProfileUiSideEffect.OnPrivatePolicy -> {
                val url = "https://bit.ly/4jq9D88"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
            is ProfileUiSideEffect.OnTermOfUse -> {
                val url = "https://bit.ly/40qFrkz"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        }
    }

}