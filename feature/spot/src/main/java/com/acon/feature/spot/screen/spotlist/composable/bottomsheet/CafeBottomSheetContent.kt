package com.acon.feature.spot.screen.spotlist.composable.bottomsheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.component.chip.AconChipFlowRow
import com.acon.core.designsystem.component.slider.AconSlider
import com.acon.core.designsystem.theme.AconTheme
import com.acon.domain.type.OptionType
import com.acon.feature.spot.R
import com.acon.feature.spot.getNameResId
import com.acon.feature.spot.type.AvailableWalkingTimeType
import com.acon.feature.spot.type.PriceRangeType

@Composable
fun ColumnScope.CafeBottomSheetContent(
    selectedCafeFeatureIndexes: List<Int>,
    selectedVisitPurposeIndexes: List<Int>,
    onCafeFeatureChipSelected: (Int) -> Unit,
    onCompanionTypeChipSelected: (Int) -> Unit,
) {

    var availableWalkingTimeSliderIndex by remember { mutableIntStateOf(AvailableWalkingTimeType.entries.size / 2) }
    var priceRangeSliderIndex by remember { mutableIntStateOf(1.coerceIn(0, PriceRangeType.entries.size)) }

    AconChipFlowRow(
        modifier = Modifier.padding(top = 12.dp),
        titles = OptionType.CafeFeatureOptionType.entries.map {
            stringResource(
                it.getNameResId()
            )
        },
        selectedChipIndexes = selectedCafeFeatureIndexes.toIntArray(),
        onChipSelected = {
            onCafeFeatureChipSelected(it)
        }
    )

    Text(
        modifier = Modifier.padding(top = 32.dp),
        text = stringResource(R.string.spot_purpose),
        style = AconTheme.typography.subtitle2_14_med,
        color = AconTheme.color.White,
    )
    AconChipFlowRow(
        modifier = Modifier.padding(top = 12.dp),
        titles = OptionType.VisitPurposeOptionType.entries.map {
            stringResource(it.getNameResId())
        },
        selectedChipIndexes = selectedVisitPurposeIndexes.toIntArray(),
        onChipSelected = {
            onCompanionTypeChipSelected(it)
        }
    )

    Text(
        modifier = Modifier.padding(top = 32.dp),
        text = stringResource(R.string.available_walking_time),
        style = AconTheme.typography.subtitle2_14_med,
        color = AconTheme.color.White,
    )
    AconSlider(
        labels = AvailableWalkingTimeType.entries.map {
            stringResource(it.titleResId)
        },
        sliderIndex = availableWalkingTimeSliderIndex,
        onSliderIndexChange = {
            availableWalkingTimeSliderIndex = it
        }
    )

    Text(
        modifier = Modifier.padding(top = 32.dp),
        text = stringResource(R.string.price_range),
        style = AconTheme.typography.subtitle2_14_med,
        color = AconTheme.color.White,
    )
    AconSlider(
        labels = PriceRangeType.entries.map {
            stringResource(it.titleResId)
        },
        sliderIndex = priceRangeSliderIndex,
        onSliderIndexChange = {
            priceRangeSliderIndex = it
        }
    )
}