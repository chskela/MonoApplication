package com.chskela.monoapplication.data.onboarding.storage.store

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class OnBoardingStore(private val context: Context) {

    companion object {
        private const val ON_BOARDING_SKIP_DEFAULT = "ON_BOARDING_SKIP_DEFAULT"
        private val ON_BOARDING_DEFAULT_KEY = booleanPreferencesKey(ON_BOARDING_SKIP_DEFAULT)
        private val Context.dataStore by preferencesDataStore("on_boarding")
    }

    val onBoardingIsSkip: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[ON_BOARDING_DEFAULT_KEY] ?: true
        }

    suspend fun setOnBoardingIsSkip() {
        context.dataStore.edit { preferences ->
            preferences[ON_BOARDING_DEFAULT_KEY] = false
        }
    }

}