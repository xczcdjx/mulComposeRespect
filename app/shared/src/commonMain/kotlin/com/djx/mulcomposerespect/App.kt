package com.djx.mulcomposerespect


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*

import androidx.compose.ui.tooling.preview.Preview
import com.djx.mulcomposerespect.viewmodel.list.KoinViewmodelCom


@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinViewmodelCom()
    }
}