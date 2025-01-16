package com.acon.feature.spot.type

import androidx.annotation.StringRes
import com.acon.feature.spot.R

enum class PriceRangeType(
    @StringRes val titleResId: Int,
) {
    UNDER_5000(titleResId = R.string.under_5000),
    UNDER_10000(titleResId = R.string.under_10000),
    UNDER_30000(titleResId = R.string.under_30000),
    UNDER_50000(titleResId = R.string.under_50000),
    OVER_50000(titleResId = R.string.over_50000),
}