package com.djx.mulcomposerespect.views.imageLoader

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.djx.mulcomposerespect.layout.LayoutCom
import com.djx.mulcomposerespect.utils.AsyncImagePlaceholder
import com.djx.mulcomposerespect.utils.CachedImagePlaceholder
import mulcomposerespect.app.shared.generated.resources.Res
import mulcomposerespect.app.shared.generated.resources.cat
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageLoaderScreen(modifier: Modifier = Modifier, back: () -> Unit = {}) {
    val rSvg = "https://cdn.docschina.org/home/logo/webpack-offical.svg"
    LayoutCom("ImageLoader", back) {
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