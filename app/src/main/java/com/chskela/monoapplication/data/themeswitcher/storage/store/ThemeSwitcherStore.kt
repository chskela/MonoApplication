package com.chskela.monoapplication.data.themeswitcher.storage.store

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class ThemeSwitcherStore (private val context: Context) {

    companion object {
        private const val THEME_SWITCHER_DEFAULT = "THEME_SWITCHER_DEFAULT"
        private val THEME_SWITCHER_DEFAULT_KEY = booleanPreferencesKey(THEME_SWITCHER_DEFAULT)
        private val Context.dataStore by preferencesDataStore("setting")
    }

    val isDarkTheme: Flow<Boolean?> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[THEME_SWITCHER_DEFAULT_KEY]
        }

    suspend fun setDarkTheme(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_SWITCHER_DEFAULT_KEY] = value
        }
    }

}