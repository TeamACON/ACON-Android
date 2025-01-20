package com.acon.feature.onboarding.type

import androidx.annotation.DrawableRes
import com.acon.feature.onboarding.R

enum class PreferPlaceItems(
    @DrawableRes override val imageResId: Int,
    override val cardName: String,
) : CardItem {
    MOOD(R.drawable.placetype_img_1, "분위기가 감각적인"),
    NEWFOOD(R.drawable.placetype_img_2, "새로운 음식의 경험"),
    CHEAP(R.drawable.placetype_img_3, "합리적인 가격과 양"),
    SPECIAL(R.drawable.placetype_img_4, "특별한 날, 고급스러운"),
}