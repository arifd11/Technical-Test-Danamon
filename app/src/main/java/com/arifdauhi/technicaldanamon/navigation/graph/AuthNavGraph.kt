package com.arifdauhi.technicaldanamon.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.arifdauhi.technicaldanamon.ui.screen.LoginScreen
import com.arifdauhi.technicaldanamon.ui.screen.RegisterScreen
import com.arifdauhi.technicaldanamon.viewmodel.MainViewModel

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(
            route = AuthScreen.Login.route
        ) {
            LoginScreen(navController, viewModel)
        }

        composable(
            route = AuthScreen.Register.route
        ) {
            RegisterScreen(navController, viewModel)
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object Login : AuthScreen(route = "LOGIN")
    data object Register : AuthScreen(route = "REGISTER")
}