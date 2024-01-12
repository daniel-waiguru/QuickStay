package com.danielwaiguru.tripitacaandroid.auth.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.danielwaiguru.tripitacaandroid.auth.presentation.SignInRoute
import com.danielwaiguru.tripitacaandroid.shared.navigation.AppNavigationDestination

object SignInScreenDestination: AppNavigationDestination {
    override val route: String
        get() = "com.danielwaiguru.tripitacaandroid.auth.SignInScreen"
    override val destination: String
        get() = "com.danielwaiguru.tripitacaandroid.auth.SignInScreenDestination"
}

fun NavGraphBuilder.signInScreen(navController: NavHostController) {
    composable(route = SignInScreenDestination.route) {
        SignInRoute(
            onNavigateToHome = {}
        )
    }
}