package com.example.widget

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

const val TOP_APP_BAR_WIDGET_BUTTON_TEST_TAG = "top_app_bar_widget_button_test_tag"

@Composable
fun TopAppBarWidget(
    title: String,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = true,
    onBack: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    val navigationIcon = if (showBackButton) {
        @Composable { BackButton(onBack) }
    } else {
        null
    }
    TopAppBar(
        windowInsets = WindowInsets.statusBars,
        navigationIcon = navigationIcon,
        actions = actions,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        modifier = modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
        ),
        title = {
            Text(
                text = title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    )
}

@Composable
private fun BackButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.testTag(TOP_APP_BAR_WIDGET_BUTTON_TEST_TAG)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TopAppBarWidgetPreview() {
    TopAppBarWidget(title = "Bitcoin")
}

@Preview(showBackground = true)
@Composable
private fun TopAppBarWidgetEmptyTitlePreview() {
    TopAppBarWidget(title = "")
}