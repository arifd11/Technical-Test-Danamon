package com.arifdauhi.technicaldanamon.navigation.graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arifdauhi.technicaldanamon.ui.screen.MainScreen
import com.arifdauhi.technicaldanamon.util.getLoginData
import com.arifdauhi.technicaldanamon.viewmodel.MainViewModel

@Composable
fun RootNavGraph(navController: NavHostController) {
    var start = Graph.AUTHENTICATION

    val currentLoginData = getLoginData()

    if (currentLoginData.id != -1){
        start = Graph.HOME
    }

    val viewModel: MainViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = start
    ) {
        authNavGraph(navController = navController, viewModel = viewModel)
        composable(
            route = Graph.HOME
        ) {
            MainScreen(navController, viewModel)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}