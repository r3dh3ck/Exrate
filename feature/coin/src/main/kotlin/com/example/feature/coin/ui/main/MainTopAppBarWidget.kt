package com.example.feature.coin.ui.main

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.R
import com.example.widget.TopAppBarWidget

const val MAIN_TOP_APP_BAR_WIDGET_SETTINGS_ACTION_TEST_TAG =
    "main_top_app_bar_widget_settings_action_test_tag"

@Composable
internal fun MainTopAppBarWidget(
    onSettingsClicked: () -> Unit
) {
    return TopAppBarWidget(
        title = stringResource(R.string.app_name),
        showBackButton = false,
        actions = {
            IconButton(
                onClick = onSettingsClicked,
                modifier = Modifier.testTag(MAIN_TOP_APP_BAR_WIDGET_SETTINGS_ACTION_TEST_TAG)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = null
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun MainTopAppBarWidgetPreview() {
    MainTopAppBarWidget {}
}