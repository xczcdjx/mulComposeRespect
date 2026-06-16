package com.djx.mulcomposerespect.views.webview


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.multiplatform.webview.web.LoadingState
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewState

@Composable
fun WebViewScreen(back:()-> Unit={}) {
    val state = rememberWebViewState(url = "https://transient.cloud")
    val navigator = rememberWebViewNavigator()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(text = state.pageTitle ?: "WebView Sample") },
            navigationIcon = {
                if (navigator.canGoBack) {
                    IconButton(onClick = { navigator.navigateBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            },
            actions = {
                IconButton({
                    back()
                }){
                    Icon(Icons.Default.ArrowBack,null)
                }
            }
        )

        Text(text = state.pageTitle ?: "null")

        val loadingState = state.loadingState
        if (loadingState is LoadingState.Loading) {
            LinearProgressIndicator(
                progress = { loadingState.progress },
                modifier = Modifier.fillMaxWidth()
            )
        }

        WebView(
            state = state,
            navigator = navigator,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}