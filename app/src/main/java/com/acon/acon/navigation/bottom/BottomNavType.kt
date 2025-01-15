package com.acon.acon.navigation.bottom

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.acon.acon.R

enum class BottomNavType(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int
) {
    SPOT(R.string.title_spot, com.acon.core.designsystem.R.drawable.ic_spot_w_24),
    UPLOAD(R.string.title_upload, com.acon.core.designsystem.R.drawable.ic_upload_gla_24),
    PROFILE(R.string.title_profile, com.acon.core.designsystem.R.drawable.ic_mypage_gla_24)
}