package com.acon.android.feature.spot.state

import androidx.compose.runtime.Immutable
import com.acon.android.domain.type.CategoryType
import com.acon.android.domain.type.OptionType
import com.acon.android.domain.type.SpotType
import com.acon.android.feature.spot.type.AvailableWalkingTimeType
import com.acon.android.feature.spot.type.CafePriceRangeType
import com.acon.android.feature.spot.type.RestaurantPriceRangeType

@Immutable
data class ConditionState(
    val spotType: SpotType,
    val restaurantFeatureOptionType: List<OptionType.RestaurantFeatureOptionType>,
    val companionTypeOptionType: List<OptionType.CompanionTypeOptionType>,
    val cafeFeatureOptionType: List<OptionType.CafeFeatureOptionType>,
    val visitPurposeOptionType: List<OptionType.VisitPurposeOptionType>,
    val restaurantWalkingTime: AvailableWalkingTimeType,
    val cafeWalkingTime: AvailableWalkingTimeType,
    val restaurantPriceRange: RestaurantPriceRangeType,
    val cafePriceRange: CafePriceRangeType
) {

    fun toCondition(): com.acon.android.domain.model.spot.Condition {
        return com.acon.android.domain.model.spot.Condition(
            spotType = spotType,
            filterList = getFilterList(),
            walkingTime = getWalkingTime(),
            priceRange = getPriceRange()
        )
    }

    private fun getPriceRange() : Int {
        return when (spotType) {
            SpotType.RESTAURANT -> restaurantPriceRange.value
            SpotType.CAFE -> cafePriceRange.value
        }
    }

    private fun getWalkingTime() : Int {
        return when (spotType) {
            SpotType.RESTAURANT -> restaurantWalkingTime.value
            SpotType.CAFE -> cafeWalkingTime.value
        }
    }

    private fun getFilterList() : List<com.acon.android.domain.model.spot.Filter> {
        return when (spotType) {
            SpotType.RESTAURANT -> {
                listOf(
                    com.acon.android.domain.model.spot.Filter(
                        category = CategoryType.RESTAURANT_FEATURE,
                        optionList = restaurantFeatureOptionType
                    ),
                    com.acon.android.domain.model.spot.Filter(
                        category = CategoryType.COMPANION_TYPE,
                        optionList = companionTypeOptionType
                    ),
                )
            }

            SpotType.CAFE -> {
                listOf(
                    com.acon.android.domain.model.spot.Filter(
                        category = CategoryType.CAFE_FEATURE,
                        optionList = cafeFeatureOptionType
                    ),
                    com.acon.android.domain.model.spot.Filter(
                        category = CategoryType.VISIT_PURPOSE,
                        optionList = visitPurposeOptionType
                    ),
                )
            }
        }
    }
}