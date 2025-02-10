package com.example.exrate.screen.main

import androidx.compose.ui.test.SemanticsMatcher
import com.example.core.LazyListItemPosition
import com.example.feature.coin.ui.main.coinlist.COIN_LIST_WIDGET_TEST_TAG
import com.example.feature.coin.ui.main.MAIN_SCREEN_TEST_TAG
import com.example.feature.coin.ui.main.MAIN_TOP_APP_BAR_WIDGET_SETTINGS_ACTION_TEST_TAG
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListNode

object MainScreen : ComposeScreen<MainScreen>(
    viewBuilderAction = { hasTestTag(MAIN_SCREEN_TEST_TAG) }
) {

    val settingsAction: KNode = child {
        hasTestTag(MAIN_TOP_APP_BAR_WIDGET_SETTINGS_ACTION_TEST_TAG)
    }

    val coinList: KLazyListNode = KLazyListNode(
        viewBuilderAction = { hasTestTag(COIN_LIST_WIDGET_TEST_TAG) },
        itemTypeBuilder = { itemType(::CoinItem) },
        positionMatcher = { position ->
            SemanticsMatcher.expectValue(LazyListItemPosition, position)
        }
    )
}