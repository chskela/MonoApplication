package com.chskela.monoapplication.data.currency.storage.store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class CurrencyStore(private val context: Context) {

    companion object {
        private const val CURRENCY_DEFAULT = "CURRENCY_DEFAULT"
        private val CURRENCY_DEFAULT_KEY = longPreferencesKey(CURRENCY_DEFAULT)
        private val Context.dataStore by preferencesDataStore("currency")
    }

    val getDefaultCurrency: Flow<Long> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[CURRENCY_DEFAULT_KEY] ?: 1
        }

    suspend fun saveDefaultCurrency(id: Long) {
        context.dataStore.edit { preferences ->
            preferences[CURRENCY_DEFAULT_KEY] = id
        }
    }

}