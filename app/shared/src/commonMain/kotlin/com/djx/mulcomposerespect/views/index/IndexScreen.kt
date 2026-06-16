package com.djx.mulcomposerespect.views.index

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.djx.mulcomposerespect.export.SystemStyles
import com.djx.mulcomposerespect.export.rememberFullScreen
import com.djx.mulcomposerespect.router.Routes
import com.djx.mulcomposerespect.viewmodels.IndexVM
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun IndexScreen(
    vm: IndexVM = koinViewModel(),
    go: (n: String) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        vm.toast.collect { msg -> snackbarHostState.showSnackbar(msg) }
    }
    val title by vm.title.collectAsState()
    val fullScreen by rememberFullScreen()
    val listCom: List<RouterList> = listOf(
        RouterList(Routes.Count.route, "Count Storage Demo"),
        RouterList(Routes.TodoList.route, "TodoList Demo"),
        RouterList(Routes.Scan.route, "Scan Demo"),
        RouterList(Routes.WebView.route, "WebView Demo"),
        RouterList(Routes.ImageLoader.route, "Go ImagerLoader"),
    )
    Scaffold(
        topBar = {
            TopAppBar(
                // ios full
//                windowInsets = WindowInsets(0),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(title)
                    }
                }, actions = {
                    IconButton(onClick = vm::toggleDark) {
                        val ic =
                            if (vm.isDark.value) Icons.Default.DarkMode else Icons.Default.LightMode
                        Icon(ic, null)
                    }
                    IconButton(
                        onClick = {
                            SystemStyles.toggleFullScreen()
                        }
                    ) {
                        Icon(
                            imageVector = if (!fullScreen) {
                                Icons.Default.Fullscreen
                            } else {
                                Icons.Default.FullscreenExit
                            },
                            contentDescription = if (fullScreen) "隐藏状态栏" else "显示状态栏"
                        )
                    }
                })
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }

    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(listCom.size) {
                val lc = listCom[it]
                TextButton({
                    go(lc.name)
                }) {
                    Text(buildAnnotatedString {
                        withStyle(SpanStyle(Color.Red)) {
                            append("${it + 1}.")
                        }
                        append(" ")
                        append(lc.title)
                    })
                }
            }
        }
    }
}

data class RouterList(val name: String, val title: String)