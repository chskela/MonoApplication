package com.chskela.monoapplication.data.themeswitcher.repository

import com.chskela.monoapplication.data.themeswitcher.storage.store.ThemeSwitcherStore
import com.chskela.monoapplication.domain.common.repository.AbstractRepository
import com.chskela.monoapplication.domain.themeswitcher.repository.ThemeSwitcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class ThemeSwitcherImpl(private val themeSwitcherStore: ThemeSwitcherStore) : ThemeSwitcher,
    AbstractRepository() {

    override fun isDarkTheme(): Flow<Boolean?> {
        return themeSwitcherStore.isDarkTheme.flowOn(coroutineContext)
    }

    override suspend fun setDarkTheme(value: Boolean) {
        withContext(coroutineContext) {
            themeSwitcherStore.setDarkTheme(value)
        }
    }
}