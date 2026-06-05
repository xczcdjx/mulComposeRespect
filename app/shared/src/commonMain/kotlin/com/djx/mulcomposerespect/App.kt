package com.djx.mulcomposerespect


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.djx.mulcomposerespect.viewmodel.detail.DetailCom
import com.djx.mulcomposerespect.viewmodel.list.ListCom


@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface {
            val navController: NavHostController = rememberNavController()
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
        }
    }
}