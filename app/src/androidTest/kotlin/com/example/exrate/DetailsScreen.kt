package com.example.exrate

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.example.exrate.ui.details.DETAILS_SCREEN_TEST_TAG
import com.example.exrate.ui.details.TOP_APP_BAR_WIDGET_BUTTON_TEST_TAG
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class DetailsScreen(
    semanticsProvider: SemanticsNodeInteractionsProvider
) : ComposeScreen<DetailsScreen>(
    semanticsProvider = semanticsProvider,
    viewBuilderAction = { hasTestTag(DETAILS_SCREEN_TEST_TAG) }
) {

    val topAppBarButton: KNode = child { hasTestTag(TOP_APP_BAR_WIDGET_BUTTON_TEST_TAG) }
}