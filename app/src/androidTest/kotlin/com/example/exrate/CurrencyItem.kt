package com.example.exrate

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.lazylist.KLazyListItemNode

class CurrencyItem(
    semanticNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider
) : KLazyListItemNode<CurrencyItem>(
    semanticNode = semanticNode,
    semanticsProvider = semanticsProvider
)