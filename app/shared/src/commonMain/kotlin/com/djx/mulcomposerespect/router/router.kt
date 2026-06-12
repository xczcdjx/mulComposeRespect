package com.djx.mulcomposerespect.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.djx.mulcomposerespect.viewmodel.detail.DetailScreen
import com.djx.mulcomposerespect.viewmodel.index.IndexScreen
import com.djx.mulcomposerespect.viewmodel.todoDemo.TodoListScreen

@Composable
fun Router() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Index.route) {
        composable(Routes.Index.route) {
            IndexScreen(go = {
                navController.navigate(it)
            })
        }
        composable(Routes.Detail.route) {
            DetailScreen()
        }
        composable(Routes.TodoDemo.route) {
            TodoListScreen() {
                navController.popBackStack()
            }
        }
    }
}