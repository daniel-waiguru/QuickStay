package com.danielwaiguru.tripitacaandroid.properties.presentation.propertieslisting.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.danielwaiguru.tripitacaandroid.properties.presentation.propertieslisting.PropertiesRoute
import com.danielwaiguru.tripitacaandroid.properties.presentation.propertyinfo.navigation.navigateToPropertyInfo
import com.danielwaiguru.tripitacaandroid.shared.navigation.AppNavigationDestination

object PropertiesScreenDestination: AppNavigationDestination {
    override val route: String = "com.danielwaiguru.tripitacaandroid.properties.PropertiesScreen"
    override val destination: String =
        "com.danielwaiguru.tripitacaandroid.properties.PropertiesScreenDestination"
}

fun NavGraphBuilder.propertiesScreen(navController: NavHostController) {
    composable(route = PropertiesScreenDestination.destination) {
        PropertiesRoute(
            onClick = {
                navController.navigateToPropertyInfo(it)
            }
        )
    }
}