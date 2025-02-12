package com.acon.feature.profile.screen.profile.composable.screen.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.blur.LocalHazeState
import com.acon.core.designsystem.component.topbar.AconTopBar
import com.acon.core.designsystem.noRippleClickable
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.profile.R
import com.acon.feature.profile.screen.profile.composable.type.ProfileInfoType
import com.acon.feature.profile.screen.profile.composable.component.ProfileInfo
import com.acon.feature.profile.screen.profile.composable.component.LoginBottomSheet
import com.acon.feature.profile.screen.profile.composable.screen.ProfileUiState
import dev.chrisbanes.haze.hazeSource

@Composable
fun ProfileScreen(
    state: ProfileUiState,
    modifier: Modifier = Modifier,
    type: String = "게스트", //TODO 로그인 여부 설정해야 함
    onSettings: () -> Unit = {},
    onEditProfile: () -> Unit = {},
    onGoogleSignIn: () -> Unit = {},
    onTermOfUse: () -> Unit = {},
    onPrivatePolicy: () -> Unit = {},
    onBottomSheetShowStateChange: (Boolean) -> Unit = {}
) {
    when(state) {
        is ProfileUiState.Success -> {
          Column(
                modifier = modifier
                    .background(AconTheme.color.Gray9)
                    .padding(horizontal = 16.dp)
                    .hazeSource(LocalHazeState.current)
          ) { 
              Spacer(Modifier.height(42.dp))
              AconTopBar(
                  modifier = Modifier.padding(vertical = 14.dp),
                  paddingValues = PaddingValues(0.dp),
                  content = {
                        Text(
                            text = stringResource(R.string.profile_topbar),
                            style = AconTheme.typography.head5_22_sb,
                            color = AconTheme.color.White
                        )
                    },
                  trailingIcon = {
                      IconButton(onClick = onSettings) {
                            Icon(
                                imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.ic_setting_w_28),
                                contentDescription = stringResource(R.string.content_description_settings),
                                tint = AconTheme.color.White
                            )
                      }
                  },
              )

              Row(
                  modifier = Modifier
                 .padding(vertical = 32.dp)
              ) {
                    // 이미지가 없으면 디폴트 이미지
                    Image(
                        imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.ic_default_profile_40),
                        contentDescription = stringResource(R.string.content_description_default_profile_image),
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(AconTheme.color.Gray7)
                            .padding(10.dp)
                    )

                    Column(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "유저",
                            style = AconTheme.typography.head5_22_sb,
                            color = AconTheme.color.White,
                        )

                        Spacer(Modifier.height(4.dp))
                        Row {
                            Text(
                                text = stringResource(R.string.edit_profile),
                                style = AconTheme.typography.subtitle2_14_med,
                                color = AconTheme.color.Gray4,
                            )

                            Image(
                                imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.ic_edit_g_20),
                                contentDescription = stringResource(R.string.content_description_edit_profile),
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .noRippleClickable { onEditProfile() }
                            )
                        }
                    }
                }
              Row(
                 modifier = Modifier
                     .fillMaxWidth(),
                 horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                    ProfileInfo(
                        profileInfoType = ProfileInfoType.ACON,
                        aconCount = "11",
                        modifier = Modifier.weight(1f)
                    )
                    ProfileInfo(
                        profileInfoType = ProfileInfoType.AREA,
                        area = "망원동",
                        modifier = Modifier.weight(1f)
                    )
                }
          }
        }
        is ProfileUiState.Loading -> {}
        is ProfileUiState.LoadFailed -> {
            if(state.showLoginBottomSheet) {
                LoginBottomSheet(
                    hazeState = LocalHazeState.current,
                    onDismissRequest = { onBottomSheetShowStateChange(false) },
                    onGoogleSignIn = onGoogleSignIn,
                    onTermOfUse = onTermOfUse,
                    onPrivatePolicy = onPrivatePolicy
                )
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(AconTheme.color.Gray9)
                    .padding(horizontal = 16.dp)
                    .hazeSource(LocalHazeState.current)
            ) {
                Spacer(Modifier.height(42.dp))
                AconTopBar(
                    modifier = Modifier.padding(vertical = 14.dp),
                    paddingValues = PaddingValues(0.dp),
                    content = {
                        Text(
                            text = stringResource(R.string.profile_topbar),
                            style = AconTheme.typography.head5_22_sb,
                            color = AconTheme.color.White
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = onSettings) {
                            Icon(
                                imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.ic_setting_w_28),
                                contentDescription = stringResource(R.string.content_description_settings),
                                tint = AconTheme.color.White
                            )
                        }
                    },
                )

                Row(
                    modifier = Modifier
                        .padding(vertical = 32.dp)
                ) {
                    // 이미지가 없으면 디폴트 이미지
                    Image(
                        imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.ic_default_profile_40),
                        contentDescription = stringResource(R.string.content_description_default_profile_image),
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(AconTheme.color.Gray7)
                            .padding(10.dp)
                    )

                    Text(
                        text = stringResource(R.string.you_need_login),
                        style = AconTheme.typography.head5_22_sb,
                        color = AconTheme.color.White,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .padding(vertical = 15.dp)
                            .noRippleClickable { onBottomSheetShowStateChange(true) }
                    )

                    Icon(
                        imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.ic_arrow_right_24),
                        contentDescription = stringResource(R.string.content_description_edit_profile),
                        modifier = Modifier
                            .padding(start = 2.dp)
                            .padding(vertical = 15.dp)
                            .size(28.dp)
                            .noRippleClickable { onBottomSheetShowStateChange(true) },
                        tint = AconTheme.color.White
                    )
                }

                Spacer(Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ProfileInfo(
                        profileInfoType = ProfileInfoType.ACON,
                        aconCount = "11",
                        modifier = Modifier.weight(1f)
                    )
                    ProfileInfo(
                        profileInfoType = ProfileInfoType.AREA,
                        area = stringResource(R.string.profile_info_not_verified),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    AconTheme {
        ProfileScreen(
            state = ProfileUiState.LoadFailed(),
            type = "게스트1"
        )
    }
}
