package com.example.exrate.ui.details

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

const val TOP_APP_BAR_WIDGET_BUTTON_TEST_TAG = "top_app_bar_widget_button_test_tag"

@Composable
fun TopAppBarWidget(
    state: TopAppBarState,
    onBack: () -> Unit
) {
    val title = when (state) {
        TopAppBarState.Empty -> ""
        is TopAppBarState.Title -> state.value
    }
    TopAppBar(
        title = { Text(title) },
        windowInsets = WindowInsets.statusBars,
        navigationIcon = {
            IconButton(
                onClick = onBack,
                modifier = Modifier.testTag(TOP_APP_BAR_WIDGET_BUTTON_TEST_TAG)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}

@Preview(showBackground = true)
@Composable
private fun TopAppBarWidgetEmptyPreview() {
    TopAppBarWidget(TopAppBarState.Empty) {}
}

@Preview(showBackground = true)
@Composable
private fun TopAppBarWidgetPreview() {
    val state = TopAppBarState.Title("Bitcoin")
    TopAppBarWidget(state) {}
}