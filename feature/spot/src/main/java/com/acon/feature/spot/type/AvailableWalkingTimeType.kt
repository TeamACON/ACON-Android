package com.acon.feature.spot.type

import androidx.annotation.StringRes
import com.acon.feature.spot.R

enum class AvailableWalkingTimeType(
    @StringRes val titleResId: Int,
) {
    UNDER_5_MINUTES(titleResId = R.string.under_5_minutes),
    UNDER_10_MINUTES(titleResId = R.string.under_10_minutes),
    UNDER_15_MINUTES(titleResId = R.string.under_15_minutes),
    UNDER_20_MINUTES(titleResId = R.string.under_20_minutes),
    OVER_20_MINUTES(titleResId = R.string.over_20_minutes),
}