package com.djx.mulcomposerespect.viewmodel.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.djx.mulcomposerespect.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ListCom(
    vm: HomeViewModel = koinViewModel(),
    goDetail:()-> Unit
) {
    LaunchedEffect(Unit) {
        vm.toast.collect { msg -> print("Toast: $msg") }
    }
    val count by vm.count.collectAsState()
    val title by vm.title.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(title)
            })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text("count $count")
            TextButton({
                vm.add(1)
            }) {
                Text("c++")
            }
            TextButton(goDetail){
                Text("to detail")
            }
        }
    }
}