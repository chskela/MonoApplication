package com.chskela.monoapplication.domain.themeswitcher.usecase

import com.chskela.monoapplication.domain.themeswitcher.repository.ThemeSwitcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsDarkThemeUseCase @Inject constructor(private val themeSwitcher: ThemeSwitcher) {

    operator fun invoke(): Flow<Boolean> {
        return themeSwitcher.isDarkTheme()
    }
}