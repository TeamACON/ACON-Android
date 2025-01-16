package com.acon.feature.spot.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.acon.core.designsystem.R

enum class FloatingButtonType(
    @DrawableRes val iconRes: Int,
    @StringRes val contentDescriptionRes: Int,
    val action: () -> Unit,
    val enabled: Boolean = true
) {

    LOCATION(
        iconRes = R.drawable.ic_my_location_16,
        contentDescriptionRes = R.string.location_content_description,
        action = { /* TODO */ },
        enabled = false
    ),
    MAP(
        iconRes = R.drawable.ic_map_w_24,
        contentDescriptionRes = R.string.map_content_description,
        action = { /* TODO */ }
    ),
    FILTER(
        iconRes = R.drawable.ic_filter_w_24,
        contentDescriptionRes = R.string.filter_content_description,
        action = { /* TODO */ }
    )
}