package com.acon.android.navigation.bottom

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.acon.android.R

enum class BottomNavType(
    @StringRes val titleRes: Int,
    @DrawableRes val selectedIconRes: Int,
    @DrawableRes val unselectedIconRes: Int
) {
    SPOT(R.string.title_spot, com.acon.android.core.designsystem.R.drawable.ic_spot_w_24, com.acon.android.core.designsystem.R.drawable.ic_spot_gla_24),
    UPLOAD(R.string.title_upload, com.acon.android.core.designsystem.R.drawable.ic_upload_gla_24 , com.acon.android.core.designsystem.R.drawable.ic_upload_gla_24),
    PROFILE(R.string.title_profile, com.acon.android.core.designsystem.R.drawable.ic_mypage_w_24, com.acon.android.core.designsystem.R.drawable.ic_mypage_gla_24)
}