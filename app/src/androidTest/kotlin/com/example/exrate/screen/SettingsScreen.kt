package com.example.exrate.screen

import com.example.exrate.widget.OneLineWidget
import com.example.feature.settings.ui.DEFAULT_CURRENCY_WIDGET_TEST_TAG
import com.example.feature.settings.ui.SETTINGS_SCREEN_TEST_TAG
import com.example.widget.TOP_APP_BAR_WIDGET_BUTTON_TEST_TAG
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

object SettingsScreen : ComposeScreen<SettingsScreen>(
    viewBuilderAction = { hasTestTag(SETTINGS_SCREEN_TEST_TAG) }
) {

    val topAppBarButton: KNode = child {
        hasTestTag(TOP_APP_BAR_WIDGET_BUTTON_TEST_TAG)
    }

    val defaultCurrency: OneLineWidget = OneLineWidget {
        hasTestTag(DEFAULT_CURRENCY_WIDGET_TEST_TAG)
    }
}