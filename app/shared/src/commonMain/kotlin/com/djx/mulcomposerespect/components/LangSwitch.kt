package com.djx.mulcomposerespect.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.djx.i18n.runtime.tr
import com.djx.mulcomposerespect.app.AppState
import com.djx.mulcomposerespect.i18n.I18nKeys
import org.koin.compose.koinInject

@Composable
fun LangSwitch(modifier: Modifier = Modifier) {
    val appState = koinInject<AppState>()
    val lang by appState.currentLang.collectAsState()
    val langList = listOf(
        "system" to I18nKeys.lang_system,
        "zh" to I18nKeys.lang_zh,
        "en" to I18nKeys.lang_en,
    )
    var showDrop by remember {
        mutableStateOf(false)
    }
    val currentLangText = langList
        .firstOrNull { it.first == lang }
        ?.second
        ?: lang
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box() {
            TextButton(
                {
                    showDrop = true
                }
            ) {
                Text(tr(currentLangText))
            }
            DropdownMenu(showDrop, {
                showDrop = false
            }) {
                langList.forEach { (k, v) ->
                    DropdownMenuItem(text = {
                        Text(
                            tr(v),
                            color = if (k == lang) MaterialTheme.colorScheme.primary else Color.Unspecified
                        )
                    }, onClick = {
                        appState.lanSwitch(k)
                        showDrop = false
                    })
                }
            }
        }
    }
}