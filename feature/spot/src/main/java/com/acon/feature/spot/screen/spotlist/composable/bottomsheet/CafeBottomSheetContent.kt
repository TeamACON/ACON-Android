package com.acon.feature.spot.screen.spotlist.composable.bottomsheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.acon.feature.spot.type.CafePriceRangeType

@Composable
fun ColumnScope.CafeBottomSheetContent(
    selectedCafeFeatures: List<OptionType.CafeFeatureOptionType>,
    selectedVisitPurposes: List<OptionType.VisitPurposeOptionType>,
    selectedWalkingTime: AvailableWalkingTimeType,
    selectedPriceRange: CafePriceRangeType,
    onCafeFeatureChipSelected: (OptionType.CafeFeatureOptionType) -> Unit,
    onVisitPurposeChipSelected: (OptionType.VisitPurposeOptionType) -> Unit,
    onWalkingTimeChange: (AvailableWalkingTimeType) -> Unit,
    onPriceRangeChange: (CafePriceRangeType) -> Unit,
) {

    AconChipFlowRow(
        modifier = Modifier.padding(top = 12.dp),
        titles = OptionType.CafeFeatureOptionType.entries.map {
            stringResource(it.getNameResId())
        },
        selectedChipIndexes = selectedCafeFeatures.map { it.ordinal }.toIntArray(),
        onChipSelected = {
            onCafeFeatureChipSelected(OptionType.CafeFeatureOptionType.entries[it])
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
        selectedChipIndexes = selectedVisitPurposes.map { it.ordinal }.toIntArray(),
        onChipSelected = {
            onVisitPurposeChipSelected(OptionType.VisitPurposeOptionType.entries[it])
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
        sliderIndex = selectedWalkingTime.ordinal,
        onSliderIndexChange = {
            onWalkingTimeChange(AvailableWalkingTimeType.entries[it])
        }
    )

    Text(
        modifier = Modifier.padding(top = 32.dp),
        text = stringResource(R.string.price_range),
        style = AconTheme.typography.subtitle2_14_med,
        color = AconTheme.color.White,
    )
    AconSlider(
        labels = CafePriceRangeType.entries.map {
            stringResource(it.titleResId)
        },
        sliderIndex = selectedPriceRange.ordinal,
        onSliderIndexChange = {
            onPriceRangeChange(CafePriceRangeType.entries[it])
        }
    )
}