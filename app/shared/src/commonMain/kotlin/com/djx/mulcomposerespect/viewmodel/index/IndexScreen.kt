package com.djx.mulcomposerespect.viewmodel.index

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material.icons.filled.Light
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.QrCode
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.djx.mulcomposerespect.export.SystemStyles
import com.djx.mulcomposerespect.export.rememberStatusBarVisible
import com.djx.mulcomposerespect.router.Routes
import com.djx.mulcomposerespect.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun IndexScreen(
    vm: HomeViewModel = koinViewModel(),
    go: (n: String) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        vm.toast.collect { msg -> snackbarHostState.showSnackbar(msg) }
    }
    val title by vm.title.collectAsState()
    val count by vm.count.collectAsState()
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
                IconButton({
                    go(Routes.TodoDemo.route)
                }) {
                    Icon(Icons.AutoMirrored.Filled.List, null)
                }
                IconButton(onClick = vm::toggleDark) {
                    val ic =
                        if (vm.isDark.value) Icons.Default.DarkMode else Icons.Default.LightMode
                    Icon(ic, null)
                }
            })
        },
        floatingActionButton = {
            /*IconButton({
                go(Routes.Scan.route)
            }) {
                Icon(Icons.Default.QrCode, null)
            }*/
            val statusBarVisible by rememberStatusBarVisible()
            IconButton(
                onClick = {
                    SystemStyles.toggleStatusBar()
                }
            ) {
                Icon(
                    imageVector = if (statusBarVisible) {
                        Icons.Default.Fullscreen
                    } else {
                        Icons.Default.FullscreenExit
                    },
                    contentDescription = if (statusBarVisible) "隐藏状态栏" else "显示状态栏"
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }

    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item {
                Text("Count $count", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            }
            item {
                TextButton({
                    vm.add(10)
                }) {
                    Text("Count++")
                }
            }
            item {
                TextButton({
                    go(Routes.Detail.route)
                }) {
                    Text("getData")
                }
            }
        }
    }
}