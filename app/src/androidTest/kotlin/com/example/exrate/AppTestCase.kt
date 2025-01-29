package com.example.exrate

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.exrate.screen.DetailsScreen
import com.example.exrate.screen.main.MainScreen
import com.example.exrate.screen.selectcurrency.SelectCurrencyScreen
import com.example.exrate.screen.SettingsScreen
import com.example.exrate.screen.main.CoinItem
import com.example.exrate.screen.selectcurrency.CurrencyItem
import com.example.exrate.ui.MainActivity
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class AppTestCase : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport()
) {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val kakaoTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true
    )

    @Test
    fun endToEnd() = run {
        onComposeScreen<MainScreen> {
            coinList.firstChild<CoinItem> {
                price.assertTextContains("USD", substring = true)
            }
            settingsAction.performClick()
        }
        onComposeScreen<SettingsScreen> {
            defaultCurrency.value.assertTextEquals("USD")
            defaultCurrency.performClick()
        }
        onComposeScreen<SelectCurrencyScreen> {
            query.performTextInput("usd")
            currencyList.firstChild<CurrencyItem> {
                name.assertTextEquals("USD")
                checkIcon.assertIsDisplayed()
            }
            query.clearButton.performClick()
            query.performTextInput("eur")
            currencyList.firstChild<CurrencyItem> {
                performClick()
            }
        }
        onComposeScreen<SettingsScreen> {
            defaultCurrency.value.assertTextEquals("EUR")
            topAppBarButton.performClick()
        }
        onComposeScreen<MainScreen> {
            coinList.firstChild<CoinItem> {
                price.assertTextContains("EUR", substring = true)
                performClick()
            }
        }
        onComposeScreen<DetailsScreen> {
            marketCap.value.assertTextContains("EUR", substring = true)
            price.value.assertTextContains("EUR", substring = true)
            topAppBarButton.performClick()
        }
        onComposeScreen<MainScreen> {
            assertIsDisplayed()
        }
    }
}