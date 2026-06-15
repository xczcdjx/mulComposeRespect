package com.djx.mulcomposerespect.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent

@Composable
fun CachedImage(
    url: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalPlatformContext.current

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .networkCachePolicy(CachePolicy.ENABLED)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@Composable
fun AsyncImagePlaceholder(
    url: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    loadingCom: (@Composable () -> Unit)? = null,
    errorIcon: ImageVector? = null,
    errorTxt: String? = null,
    errorCom: (@Composable () -> Unit)? = null,
) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = contentDescription,
        modifier = modifier,
        loading = {
            if (loadingCom != null) loadingCom()
            else DefaultLoadingCom()
        },
        error = {
            if (errorCom != null) errorCom()
            else DefaultErrorCom(errorIcon ?: Icons.Default.Error, errorTxt)
        },
        success = {
            SubcomposeAsyncImageContent()
        }
    )
}

@Composable
fun CachedImagePlaceholder(
    url: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    loadingCom: (@Composable () -> Unit)? = null,
    errorIcon: ImageVector? = null,
    errorTxt: String? = null,
    errorCom: (@Composable () -> Unit)? = null,
) {
    val context = LocalPlatformContext.current

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .networkCachePolicy(CachePolicy.ENABLED)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier,
        loading = {
            if (loadingCom != null) loadingCom()
            else DefaultLoadingCom()
        },
        error = {
            if (errorCom != null) errorCom()
            else DefaultErrorCom(errorIcon ?: Icons.Default.Error, errorTxt)
        },
        success = {
            SubcomposeAsyncImageContent()
        }
    )
}

@Composable
private fun BoxScope.DefaultLoadingCom() {
    Box(
        modifier = Modifier
            .matchParentSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(28.dp),
            strokeWidth = 2.dp
        )
    }
}

@Composable
private fun BoxScope.DefaultErrorCom(
    icon: ImageVector,
    errorTxt: String? = null
) {
    Column(
        modifier = Modifier
            .matchParentSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, null)
        if (errorTxt != null) Text(errorTxt, color = Color.Red)
    }
}