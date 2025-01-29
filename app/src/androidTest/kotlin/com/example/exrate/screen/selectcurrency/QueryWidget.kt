package com.example.exrate.screen.selectcurrency

import com.example.feature.selectcurrency.ui.QUERY_WIDGET_CLEAR_BUTTON_TEST_TAG
import io.github.kakaocup.compose.node.builder.ViewBuilder
import io.github.kakaocup.compose.node.element.KNode

class QueryWidget(
    viewBuilderAction: ViewBuilder.() -> Unit
) : KNode(
    viewBuilderAction = viewBuilderAction
) {

    val clearButton: KNode = child { hasTestTag(QUERY_WIDGET_CLEAR_BUTTON_TEST_TAG) }
}