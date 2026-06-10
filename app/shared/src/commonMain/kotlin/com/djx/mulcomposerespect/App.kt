package com.djx.mulcomposerespect


import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.djx.mulcomposerespect.utils.ToastManager
import com.djx.mulcomposerespect.viewmodel.detail.DetailCom
import com.djx.mulcomposerespect.viewmodel.list.ListCom
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState


@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface {
            val navController: NavHostController = rememberNavController()
            val toaster = rememberToasterState()
            LaunchedEffect(Unit) {
                ToastManager.toast.collect { toastEvent ->
                    toaster.show(
                        message = toastEvent.message,
                        id = toastEvent.id,
                        icon = toastEvent.icon,
                        action = toastEvent.action,
                        type = toastEvent.type,
                        duration = toastEvent.duration
                    )
                }
            }
            Box(){
                NavHost(navController = navController, startDestination = "List") {
                    composable("List"){
                        ListCom(goDetail={
                            navController.navigate("Detail")
                        })
                    }
                    composable("Detail"){
                        DetailCom()
                    }
                }
                Toaster(state = toaster, alignment = Alignment.TopCenter)
            }
        }
    }
}