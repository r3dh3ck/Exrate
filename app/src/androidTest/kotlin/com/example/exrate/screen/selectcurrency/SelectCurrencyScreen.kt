package com.example.exrate.screen.selectcurrency

import androidx.compose.ui.test.SemanticsMatcher
import com.example.core.LazyListItemPosition
import com.example.feature.selectcurrency.ui.CURRENCY_LIST_WIDGET_TEST_TAG
import com.example.feature.selectcurrency.ui.QUERY_WIDGET_TEST_TAG
import com.example.feature.selectcurrency.ui.SELECT_CURRENCY_SCREEN_TEST_TAG
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.lazylist.KLazyListNode

object SelectCurrencyScreen : ComposeScreen<SelectCurrencyScreen>(
    viewBuilderAction = { hasTestTag(SELECT_CURRENCY_SCREEN_TEST_TAG) }
) {

    val query: QueryWidget = QueryWidget {
        hasTestTag(QUERY_WIDGET_TEST_TAG)
    }

    val currencyList: KLazyListNode = KLazyListNode(
        viewBuilderAction = { hasTestTag(CURRENCY_LIST_WIDGET_TEST_TAG) },
        itemTypeBuilder = { itemType(::CurrencyItem) },
        positionMatcher = { position ->
            SemanticsMatcher.expectValue(LazyListItemPosition, position)
        }
    )
}