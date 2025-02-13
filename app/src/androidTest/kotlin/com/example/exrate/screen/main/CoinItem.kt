package com.example.exrate.screen.main

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.example.feature.coin.ui.main.coinlist.item.COIN_LIST_ITEM_PRICE_TEST_TAG
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListItemNode

class CoinItem(
    semanticNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider
) : KLazyListItemNode<CoinItem>(
    semanticNode = semanticNode,
    semanticsProvider = semanticsProvider
) {

    val price: KNode = child { hasTestTag(COIN_LIST_ITEM_PRICE_TEST_TAG) }
}