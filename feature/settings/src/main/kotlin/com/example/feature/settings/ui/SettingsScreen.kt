package com.example.feature.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feature.currency.Currency
import com.example.feature.settings.R
import com.example.feature.settings.di.SettingsComponentHolder
import com.example.widget.OneLineWidget
import com.example.widget.TopAppBarWidget

const val SETTINGS_SCREEN_TEST_TAG = "settings_screen_test_tag"
const val DEFAULT_CURRENCY_WIDGET_TEST_TAG = "default_currency_widget_test_tag"

@Composable
internal fun SettingsScreen(
    onBack: () -> Unit,
    onCurrencyClicked: () -> Unit
) {
    val component = SettingsComponentHolder.get()
    val viewModelFactory = component.viewModelFactory
    val viewModel = viewModel<SettingsViewModel>(factory = viewModelFactory)
    SettingsScreen(
        viewModel = viewModel,
        onBack = onBack,
        onCurrencyClicked = onCurrencyClicked
    )
}

@Composable
private fun SettingsScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit,
    onCurrencyClicked: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle(
        minActiveState = Lifecycle.State.STARTED,
    )
    SettingsScreen(
        state = state,
        onBack = onBack,
        onCurrencyClicked = onCurrencyClicked
    )
    LaunchedEffect(Unit) {
        viewModel.load()
    }
}

@Composable
private fun SettingsScreen(
    state: SettingsState,
    onBack: () -> Unit,
    onCurrencyClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier.testTag(SETTINGS_SCREEN_TEST_TAG),
        topBar = {
            TopAppBarWidget(
                title = stringResource(R.string.settings_title),
                onBack = onBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
        ) {
            OneLineWidget(
                label = stringResource(R.string.settings_default_currency_title),
                value = state.selectedCurrency.name.uppercase(),
                modifier = Modifier.padding(top = 2.dp)
                    .clickable { onCurrencyClicked() }
                    .testTag(DEFAULT_CURRENCY_WIDGET_TEST_TAG)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SettingsScreenPreview() {
    val state = SettingsState(
        selectedCurrency = Currency.USD
    )
    SettingsScreen(
        state = state,
        onBack = {},
        onCurrencyClicked = {}
    )
}