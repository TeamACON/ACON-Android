package com.acon.android.feature.spot.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.acon.android.core.designsystem.R

enum class FloatingButtonType(
    @DrawableRes val iconRes: Int,
    @StringRes val contentDescriptionRes: Int,
    val enabled: Boolean = true
) {

    LOCATION(
        iconRes = R.drawable.ic_my_g_location_28,
        contentDescriptionRes = R.string.location_content_description,
        enabled = false
    ),
    MAP(
        iconRes = R.drawable.ic_map_w_28,
        contentDescriptionRes = R.string.map_content_description,
    ),
    FILTER(
        iconRes = R.drawable.ic_filter_w_28,
        contentDescriptionRes = R.string.filter_content_description,
    )
}