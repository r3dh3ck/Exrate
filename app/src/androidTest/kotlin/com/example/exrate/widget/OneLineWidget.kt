package com.example.exrate.widget

import com.example.widget.ONE_LINE_WIDGET_VALUE_TEST_TAG
import io.github.kakaocup.compose.node.builder.ViewBuilder
import io.github.kakaocup.compose.node.element.KNode

class OneLineWidget(
    viewBuilderAction: ViewBuilder.() -> Unit
) : KNode(
    viewBuilderAction = viewBuilderAction
) {

    val value: KNode = child {
        hasTestTag(ONE_LINE_WIDGET_VALUE_TEST_TAG)
    }
}