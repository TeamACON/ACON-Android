package com.acon.domain.model.spot

import com.acon.domain.type.CategoryType
import com.acon.domain.type.OptionType
import com.acon.domain.type.SpotType

data class Condition(
    val spotType: SpotType,
    val filterList: List<Filter>
)

data class Filter(
    val category: CategoryType,
    val optionList: List<OptionType>
)