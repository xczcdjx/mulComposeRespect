package com.djx.mulcomposerespect.app

import com.djx.i18n.runtime.AppLangState
import com.djx.i18n.runtime.export.Locale
import com.djx.i18n.runtime.export.systemLocale
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single

@Single
class AppState {
    private val _token = MutableStateFlow<String?>(null)
    private val _isDark = MutableStateFlow<Boolean>(false)
    private val _currentLang = MutableStateFlow("system")

    val token = _token.asStateFlow()
    val isDark = _isDark.asStateFlow()
    val currentLang = _currentLang.asStateFlow()

    fun setToken(value: String?) {
        _token.value = value
    }

    fun toggleDark() {
        _isDark.value = !_isDark.value
    }

    fun lanSwitch(lang: String) {
        _currentLang.value = lang
        AppLangState.change(Locale(getSwitchSupportLanguage(lang)))
    }
}

private fun getSwitchSupportLanguage(lang: String) = when (lang) {
    "system" -> systemLocale().code
    "zh" -> "zh"
    "en" -> "en"
    else -> "en"
}