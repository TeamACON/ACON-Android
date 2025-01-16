package com.acon.feature.onboarding.type

import androidx.annotation.DrawableRes
import com.acon.feature.onboarding.R

enum class PreferPlaceItems(
    @DrawableRes override val imageResId: Int,
    override val cardName: String,
) : CardItem {
    MOOD(R.drawable.placetype_img_1, "분위기와 인테리어가\n감각적인 곳"),
    NEWFOOD(R.drawable.placetype_img_2, "새로운 음식을\n경험할 수 있는 곳"),
    CHEAP(R.drawable.placetype_img_3, "가격과 양이\n합리적인 곳"),
    SPECIAL(R.drawable.placetype_img_4, "특별한 날을 위한\n고급스러운 장소"),
}