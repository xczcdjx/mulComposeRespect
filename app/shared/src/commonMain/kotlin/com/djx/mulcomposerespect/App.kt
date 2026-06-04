package com.djx.mulcomposerespect


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*

import androidx.compose.ui.tooling.preview.Preview
import com.djx.mulcomposerespect.viewmodel.KoinViewmodelCom
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration
import org.koin.plugin.module.dsl.startKoin


@Composable
@Preview
fun App() {
    KoinApplication(
        configuration = koinConfiguration(declaration = { startKoin<AppKoin>() }),
    ) {
        MaterialTheme {
            KoinViewmodelCom()
        }
    }
}