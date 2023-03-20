package com.chskela.monoapplication.domain.themeswitcher.usecase

import com.chskela.monoapplication.domain.themeswitcher.repository.ThemeSwitcher
import javax.inject.Inject

class SetDarkThemeUseCase @Inject constructor(private val themeSwitcher: ThemeSwitcher) {

    suspend operator fun invoke(value: Boolean) {
        themeSwitcher.setDarkTheme(value)
    }
}