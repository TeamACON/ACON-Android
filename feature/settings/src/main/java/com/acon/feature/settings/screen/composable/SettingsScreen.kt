package com.acon.feature.settings.screen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.component.dialog.AconTwoButtonDialog
import com.acon.core.designsystem.component.topbar.AconTopBar
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.settings.R
import com.acon.feature.settings.component.SettingSectionItem
import com.acon.feature.settings.component.SettingSectionVersionItem
import com.acon.feature.settings.screen.SettingsUiState
import com.acon.feature.settings.type.LoginType
import com.acon.feature.settings.type.SettingsType

@Composable
fun SettingsScreen(
    state: SettingsUiState,
    loginType: LoginType,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    onTermOfUse: () -> Unit = {},
    onPrivatePolicy: () -> Unit = {},
    onRetryOnBoarding: () -> Unit = {},
    onUpdateVersion: () -> Unit = {},
    onSignOut: () -> Unit = {},
    onDeleteAccount: () -> Unit = {},
) {
    var showSinOutDialog by remember { mutableStateOf(false) }

    if(showSinOutDialog) {
        AconTwoButtonDialog(
            title = "로그아웃 하시겠어요?",
            content = "현재 계정으로 다시 로그인하면 데이터를 불러올 수 있어요",
            leftButtonContent = "취소",
            rightButtonContent = "로그아웃하기",
            onDismissRequest = { showSinOutDialog = false },
            onClickLeft = onSignOut,
            onClickRight = { showSinOutDialog = false },
            isImageEnabled = false
        )
    }

    when(state) {
        is SettingsUiState.Default -> {
            Column(
                modifier = modifier
                    .background(AconTheme.color.Gray9)
                    .padding(horizontal = 16.dp)
            ) {
                if(loginType == LoginType.USER) {
                    Spacer(Modifier.height(42.dp))
                    AconTopBar(
                        modifier = Modifier
                            .padding(vertical = 14.dp),
                        paddingValues = PaddingValues(0.dp),
                        leadingIcon = {
                            IconButton(
                                onClick = navigateBack
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.ic_arrow_left_28),
                                    contentDescription = stringResource(R.string.go_back_content_description),
                                    tint = AconTheme.color.White
                                )
                            }
                        },
                        content = {
                            Text(
                                text = stringResource(R.string.settings_screen_topbar),
                                style = AconTheme.typography.head5_22_sb,
                                color = AconTheme.color.White
                            )
                        },
                    )

                    Text(
                        text = stringResource(R.string.settings_title_version_info),
                        style = AconTheme.typography.subtitle2_14_med,
                        color = AconTheme.color.Gray5
                    )

                    Spacer(Modifier.height(16.dp))
                    SettingSectionVersionItem(
                        currentVersion = "최신버전",
                        onClickContinue = onUpdateVersion // TODO -> 최신버전 아니면 플레이스토어로 이동
                    )

                    Spacer(Modifier.height(40.dp))
                    Text(
                        text = stringResource(R.string.settings_title_terms_and_policies),
                        style = AconTheme.typography.subtitle2_14_med,
                        color = AconTheme.color.Gray5
                    )

                    Spacer(Modifier.height(16.dp))
                    SettingSectionItem(settingsType = SettingsType.TERM_OF_USE)

                    Spacer(Modifier.height(16.dp))
                    SettingSectionItem(settingsType = SettingsType.PRIVACY_POLICY)

                    Spacer(Modifier.height(40.dp))
                    Text(
                        text = stringResource(R.string.settings_title_service_settings),
                        style = AconTheme.typography.subtitle2_14_med,
                        color = AconTheme.color.Gray5
                    )

                    Spacer(Modifier.height(16.dp))
                    SettingSectionItem(settingsType = SettingsType.ONBOARDING_AGAIN)

                    Spacer(Modifier.height(40.dp))
                    Text(
                        text = stringResource(R.string.settings_title_login_and_delete_account),
                        style = AconTheme.typography.subtitle2_14_med,
                        color = AconTheme.color.Gray5
                    )

                    Spacer(Modifier.height(16.dp))
                    SettingSectionItem(
                        settingsType = SettingsType.LOGOUT,
                        showContinueImage = true,
                        onClickContinue = { }
                    )

                    Spacer(Modifier.height(16.dp))
                    SettingSectionItem(
                        settingsType = SettingsType.DELETE_ACCOUNT,
                        showContinueImage = true,
                        onClickContinue = onDeleteAccount
                    )
                }

                else {
                    Spacer(Modifier.height(42.dp))
                    AconTopBar(
                        modifier = Modifier
                            .padding(vertical = 14.dp),
                        paddingValues = PaddingValues(0.dp),
                        leadingIcon = {
                            IconButton(
                                onClick = navigateBack
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.ic_arrow_left_28),
                                    contentDescription = stringResource(R.string.go_back_content_description),
                                    tint = AconTheme.color.White
                                )
                            }
                        },
                        content = {
                            Text(
                                text = stringResource(R.string.settings_screen_topbar),
                                style = AconTheme.typography.head5_22_sb,
                                color = AconTheme.color.White
                            )
                        },
                    )

                    Text(
                        text = stringResource(R.string.settings_title_version_info),
                        style = AconTheme.typography.subtitle2_14_med,
                        color = AconTheme.color.Gray5
                    )

                    Spacer(Modifier.height(16.dp))
                    // TODO -> 앱 버전 확인 필요
                    SettingSectionVersionItem(
                        currentVersion = "최신버전",
                        onClickContinue = onUpdateVersion
                    )

                    Spacer(Modifier.height(40.dp))
                    Text(
                        text = stringResource(R.string.settings_title_terms_and_policies),
                        style = AconTheme.typography.subtitle2_14_med,
                        color = AconTheme.color.Gray5
                    )

                    Spacer(Modifier.height(16.dp))
                    SettingSectionItem(settingsType = SettingsType.TERM_OF_USE)

                    Spacer(Modifier.height(16.dp))
                    SettingSectionItem(settingsType = SettingsType.PRIVACY_POLICY)
                }
            }
        }
    }

}

@Preview
@Composable
fun SettingsScreenPreview() {
    AconTheme {
        SettingsScreen(
            state = SettingsUiState.Default,
            loginType = LoginType.USER
        )
    }
}