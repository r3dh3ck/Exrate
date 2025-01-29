package com.example.exrate.screen

import com.example.exrate.widget.OneLineWidget
import com.example.feature.coin.ui.details.COIN_DETAILS_WIDGET_MARKET_CAP_TEST_TAG
import com.example.feature.coin.ui.details.COIN_DETAILS_WIDGET_PRICE_TEST_TAG
import com.example.feature.coin.ui.details.DETAILS_SCREEN_TEST_TAG
import com.example.widget.TOP_APP_BAR_WIDGET_BUTTON_TEST_TAG
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

object DetailsScreen : ComposeScreen<DetailsScreen>(
    viewBuilderAction = { hasTestTag(DETAILS_SCREEN_TEST_TAG) }
) {

    val topAppBarButton: KNode = child {
        hasTestTag(TOP_APP_BAR_WIDGET_BUTTON_TEST_TAG)
    }

    val marketCap: OneLineWidget = OneLineWidget {
        hasTestTag(COIN_DETAILS_WIDGET_MARKET_CAP_TEST_TAG)
    }

    val price: OneLineWidget = OneLineWidget {
        hasTestTag(COIN_DETAILS_WIDGET_PRICE_TEST_TAG)
    }
}