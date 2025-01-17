package com.acon.feature.onboarding.type

import androidx.annotation.DrawableRes
import com.acon.feature.onboarding.R

enum class PlaceItems(
    @DrawableRes override val imageResId: Int,
    override val cardName: String,
) : CardItem {

    RESTAURANT(R.drawable.place_img_3, "음식점"),
    CAFE(R.drawable.place_img_4, "카페"),
}

enum class MoodItems(
    @DrawableRes override val imageResId: Int,
    override val cardName: String,
) : CardItem {
    OLD(R.drawable.place_img_1, "노포"),
    MODERN(R.drawable.place_img_2, "모던"),
}