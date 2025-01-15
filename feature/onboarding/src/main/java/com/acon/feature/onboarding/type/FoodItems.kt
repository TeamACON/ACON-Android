package com.acon.feature.onboarding.type

import androidx.annotation.DrawableRes
import com.acon.feature.onboarding.R

enum class FoodItems(
    @DrawableRes val imageResId: Int,
    val foodName: String,
    //@StringRes val koreanName: Int,
) {

    DAKBAL(R.drawable.food_img_1, "닭발"),
    RAW(R.drawable.food_img_2, "회/육회"),
    INTESTINES(R.drawable.food_img_3, "곱창/대창/막창"),
    SUNDAE(R.drawable.food_img_4, "순대/선지"),
    SHEEP(R.drawable.food_img_5, "양고기"),
    NONE(0, "없음")
}
