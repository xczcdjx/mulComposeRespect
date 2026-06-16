package com.djx.mulcomposerespect.viewmodel.imageLoader

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.djx.mulcomposerespect.utils.AsyncImagePlaceholder
import com.djx.mulcomposerespect.utils.CachedImagePlaceholder
import mulcomposerespect.app.shared.generated.resources.Res
import mulcomposerespect.app.shared.generated.resources.cat
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageLoaderScreen(modifier: Modifier = Modifier, back: () -> Unit = {}) {
    val rSvg = "https://cdn.docschina.org/home/logo/webpack-offical.svg"
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("ImageLoader")
            },
                navigationIcon = {
                    IconButton({
                        back()
                    }){
                        Icon(Icons.AutoMirrored.Filled.ArrowBack,null)
                    }
                }
                )
        }
    ) {
        paddingValues ->
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
            Image(painterResource(Res.drawable.cat), null, modifier.size(200.dp))
        }
    }
}