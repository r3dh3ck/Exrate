package com.example.datastore.currency

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.feature.currency.Currency
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class CurrencyDataStore(
    private val dataStore: DataStore<Preferences>
) {

    private val selectedCurrencyKey = stringPreferencesKey("selected_currency_key")

    suspend fun getSelectedCurrency(): Currency {
        val name = dataStore.data
            .map { preferences -> preferences[selectedCurrencyKey] }
            .firstOrNull()
        return if (name != null) Currency(name) else Currency.USD
    }

    suspend fun setSelectedCurrency(currency: Currency) {
        dataStore.edit { preferences ->
            preferences[selectedCurrencyKey] = currency.name
        }
    }
}