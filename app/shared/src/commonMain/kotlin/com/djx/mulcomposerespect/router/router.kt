package com.djx.mulcomposerespect.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.djx.mulcomposerespect.viewmodel.detail.DetailCom
import com.djx.mulcomposerespect.viewmodel.list.ListCom
import com.djx.mulcomposerespect.viewmodel.todoDemo.TodoListScreen

@Composable
fun Router() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Index.route) {
        composable(Routes.Index.route) {
            ListCom(go = {
                navController.navigate(it)
            })
        }
        composable(Routes.Detail.route) {
            DetailCom()
        }
        composable(Routes.TodoDemo.route) {
            TodoListScreen() {
                navController.popBackStack()
            }
        }
    }
}