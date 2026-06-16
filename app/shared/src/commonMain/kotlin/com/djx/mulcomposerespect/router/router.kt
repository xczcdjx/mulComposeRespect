package com.djx.mulcomposerespect.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.djx.mulcomposerespect.views.count.CountScreen
import com.djx.mulcomposerespect.views.webview.WebViewScreen
import com.djx.mulcomposerespect.views.imageLoader.ImageLoaderScreen
import com.djx.mulcomposerespect.views.index.IndexScreen
import com.djx.mulcomposerespect.views.scan.ScanPage
import com.djx.mulcomposerespect.views.todoDemo.TodoListScreen

@Composable
fun Router() {
    val navController: NavHostController = rememberNavController()
    fun back() {
        navController.popBackStack()
    }
    NavHost(navController = navController, startDestination = Routes.Index.route) {
        composable(Routes.Index.route) {
            IndexScreen(go = {
                navController.navigate(it)
            })
        }
        composable(Routes.Count.route) {
            CountScreen(back = ::back)
        }
        composable(Routes.WebView.route) {
            WebViewScreen(back = ::back)
        }
        composable(Routes.TodoList.route) {
            TodoListScreen(back = ::back)
        }
        composable(Routes.Scan.route) {
            ScanPage(back = ::back)
        }
        composable(Routes.ImageLoader.route) {
            ImageLoaderScreen(back = ::back)
        }
    }
}