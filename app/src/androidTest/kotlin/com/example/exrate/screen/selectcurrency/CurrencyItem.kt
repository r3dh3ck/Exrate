package com.example.exrate.screen.selectcurrency

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.example.feature.selectcurrency.ui.CURRENCY_ITEM_CHECK_ICON_TEST_TAG
import com.example.feature.selectcurrency.ui.CURRENCY_ITEM_NAME_TEST_TAG
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListItemNode

class CurrencyItem(
    semanticNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider
) : KLazyListItemNode<CurrencyItem>(
    semanticNode = semanticNode,
    semanticsProvider = semanticsProvider
) {

    val name: KNode = child { hasTestTag(CURRENCY_ITEM_NAME_TEST_TAG) }

    val checkIcon: KNode = child { hasTestTag(CURRENCY_ITEM_CHECK_ICON_TEST_TAG) }
}