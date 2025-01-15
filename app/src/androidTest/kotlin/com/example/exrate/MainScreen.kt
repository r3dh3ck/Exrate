package com.example.exrate

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.example.exrate.ui.main.CURRENCY_LIST_WIDGET_TEST_TAG
import com.example.exrate.ui.main.MAIN_SCREEN_TEST_TAG
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.lazylist.KLazyListNode

class MainScreen(
    semanticsProvider: SemanticsNodeInteractionsProvider
) : ComposeScreen<MainScreen>(
    semanticsProvider = semanticsProvider,
    viewBuilderAction = { hasTestTag(MAIN_SCREEN_TEST_TAG) }
) {

    val list: KLazyListNode = KLazyListNode(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(CURRENCY_LIST_WIDGET_TEST_TAG) },
        itemTypeBuilder = { itemType(::CurrencyItem) },
        positionMatcher = { position ->
            SemanticsMatcher.expectValue(LazyListItemPosition, position)
        }
    )
}