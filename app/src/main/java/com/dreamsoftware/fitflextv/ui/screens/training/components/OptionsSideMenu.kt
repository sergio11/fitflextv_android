package com.dreamsoftware.fitflextv.ui.screens.training.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.ListItem
import androidx.tv.material3.ListItemDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.RadioButton
import androidx.tv.material3.RadioButtonDefaults
import androidx.tv.material3.Text
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonFillButton

@Composable
fun OptionsSideMenu(
    onDismissSideMenu: () -> Unit,
    @StringRes titleRes: Int,
    items: List<String>,
    selectedIndex: Int,
    onSelectedItem: (currentIndex: Int) -> Unit,
) {
    with(MaterialTheme.colorScheme) {
        TvLazyColumn(
            contentPadding = PaddingValues(vertical = 32.dp, horizontal = 20.dp)
        ) {
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(titleRes),
                        style = MaterialTheme.typography.headlineSmall,
                        color = onSurface
                    )
                    CommonFillButton(
                        text = stringResource(R.string.reset),
                        onClick = onDismissSideMenu
                    )
                }
            }
            items(items.size) { index ->
                ListItem(
                    modifier = Modifier.padding(top = 16.dp),
                    selected = false,
                    onClick = { onSelectedItem(index) },
                    trailingContent = {
                        RadioButton(
                            selected = selectedIndex == index,
                            onClick = { /*TODO*/ },
                            modifier = Modifier.size(ListItemDefaults.IconSizeDense),
                            colors = RadioButtonDefaults.colors(
                                selectedColor = inversePrimary,
                                unselectedColor = border
                            )
                        )
                    },
                    headlineContent = {
                        Text(
                            text = items[index],
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = Color.Transparent,
                        contentColor = onSurfaceVariant,
                        focusedContentColor = surfaceVariant
                    ),
                    shape = ListItemDefaults.shape(shape = RoundedCornerShape(16.dp))
                )
            }
        }
    }
}