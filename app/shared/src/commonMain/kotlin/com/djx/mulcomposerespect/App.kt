package com.djx.mulcomposerespect


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djx.i18n.runtime.AppLangState
import com.djx.i18n.runtime.AppLocalLangProvider
import com.djx.i18n.runtime.I18nRuntime
import com.djx.i18n.runtime.interfaces.I18nEngine
import com.djx.mulcomposerespect.app.AppState
import com.djx.mulcomposerespect.export.SystemBarStyle
import com.djx.mulcomposerespect.export.isDesktop
import com.djx.mulcomposerespect.i18n.I18nZeal
import com.djx.mulcomposerespect.router.Router
import com.djx.mulcomposerespect.theme.AppTheme
import com.djx.mulcomposerespect.utils.ToastManager
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import org.koin.compose.koinInject


@Composable
@Preview
fun App() {
    val appState= koinInject<AppState>()
    val isDark by appState.isDark.collectAsState()
    SystemBarStyle(isDark)
    // i18n tr trn 函数对象注入
    I18nRuntime.init(I18nZeal)
    AppTheme(darkTheme = isDark) {
        Surface {
            val toaster = rememberToasterState()
            LaunchedEffect(Unit) {
                ToastManager.toast.collect { toastEvent ->
                    toaster.show(
                        message = toastEvent.message,
                        id = toastEvent.id,
                        icon = toastEvent.icon,
                        action = toastEvent.actionBuilder?.invoke(toaster),
                        type = toastEvent.type,
                        duration = toastEvent.duration
                    )
                }
            }
            CompositionLocalProvider(
                AppLocalLangProvider provides AppLangState.current.value
            ){
                Box(modifier = Modifier.padding(top = if (isDesktop) 36.dp else 0.dp)) {
                    Router()
                    Toaster(state = toaster, alignment = Alignment.TopCenter)
                }
            }
        }
    }
}