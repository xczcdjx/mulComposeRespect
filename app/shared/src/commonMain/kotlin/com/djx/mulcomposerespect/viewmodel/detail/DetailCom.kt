package com.djx.mulcomposerespect.viewmodel.detail


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.djx.mulcomposerespect.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailCom(vm: HomeViewModel = koinViewModel(),) {
    val count by vm.count.collectAsState()
    Scaffold(topBar = {
        TopAppBar({
            Text("Detail")
        })
    }) {
            paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text("count = $count")
        }
    }
}