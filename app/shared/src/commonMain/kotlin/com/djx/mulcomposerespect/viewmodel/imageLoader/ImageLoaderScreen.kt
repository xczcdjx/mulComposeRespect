package com.djx.mulcomposerespect.viewmodel.imageLoader

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.djx.mulcomposerespect.utils.AsyncImagePlaceholder
import com.djx.mulcomposerespect.utils.CachedImagePlaceholder

@Composable
fun ImageLoaderScreen(modifier: Modifier = Modifier, back: () -> Unit = {}) {
    val rSvg = "https://cdn.docschina.org/home/logo/webpack-offical.svg"
    Scaffold { paddingValues ->
        Column(modifier.padding(paddingValues)) {
            AsyncImagePlaceholder(
                url = rSvg,
                contentDescription = null,
                modifier = modifier.size(150.dp),
                errorTxt = "eeee"
            )
            CachedImagePlaceholder(
                url = rSvg,
                contentDescription = null,
                modifier = modifier.size(180.dp)
            )
        }
    }
}