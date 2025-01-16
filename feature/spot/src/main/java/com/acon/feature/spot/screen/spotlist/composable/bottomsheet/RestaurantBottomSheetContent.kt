package com.acon.feature.spot.screen.spotlist.composable.bottomsheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.component.chip.AconChipFlowRow
import com.acon.core.designsystem.theme.AconTheme
import com.acon.domain.type.OptionType
import com.acon.feature.spot.R
import com.acon.feature.spot.getNameResId

@Composable
fun ColumnScope.RestaurantBottomSheetContent(
    selectedRestaurantFeatureIndexes: List<Int>,
    selectedCompanionTypeIndexes: List<Int>,
    onRestaurantFeatureChipSelected: (Int) -> Unit,
    onCompanionTypeChipSelected: (Int) -> Unit,
) {
    AconChipFlowRow(
        modifier = Modifier.padding(top = 12.dp),
        titles = OptionType.RestaurantFeatureOptionType.entries.map {
            stringResource(it.getNameResId())
        },
        selectedChipIndexes = selectedRestaurantFeatureIndexes.toIntArray(),
        onChipSelected = {
            onRestaurantFeatureChipSelected(it)
        }
    )

    Text(
        modifier = Modifier.padding(top = 32.dp),
        text = stringResource(R.string.people_together),
        style = AconTheme.typography.subtitle2_14_med,
        color = AconTheme.color.White,
    )
    AconChipFlowRow(
        modifier = Modifier.padding(top = 12.dp),
        titles = OptionType.CompanionTypeOptionType.entries.map {
            stringResource(
                it.getNameResId()
            )
        },
        selectedChipIndexes = selectedCompanionTypeIndexes.toIntArray(),
        onChipSelected = {
            onCompanionTypeChipSelected(it)
        }
    )
    Spacer(modifier = Modifier.weight(1f))
}