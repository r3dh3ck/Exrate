package com.example.exrate

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.exrate.ui.MainActivity
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class AppTestCase : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport()
) {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun endToEnd() = run {
        onComposeScreen<MainScreen>(composeTestRule) {
            list.firstChild<CurrencyItem> {
                performClick()
            }
        }
        onComposeScreen<DetailsScreen>(composeTestRule) {
            topAppBarButton.performClick()
        }
        onComposeScreen<MainScreen>(composeTestRule) {
            assertIsDisplayed()
        }
    }
}