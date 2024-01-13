package com.danielwaiguru.tripitacaandroid.properties.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.danielwaiguru.tripitacaandroid.properties.presentation.propertieslisting.navigation.PropertiesScreenDestination
import com.danielwaiguru.tripitacaandroid.properties.presentation.propertieslisting.navigation.propertiesScreen
import com.danielwaiguru.tripitacaandroid.properties.presentation.propertyinfo.navigation.propertyInfoScreen

private const val PROPERTIES_GRAPH_ROUTE_PATTERN = "properties"
fun NavController.navigateToPropertiesGraph(navOptions: NavOptions? = null) = navigate(
    PROPERTIES_GRAPH_ROUTE_PATTERN,
    navOptions
)
fun NavGraphBuilder.propertiesFeatureGraph(navController: NavHostController) {
    navigation(
        route = PROPERTIES_GRAPH_ROUTE_PATTERN,
        startDestination = PropertiesScreenDestination.destination
    ) {
        propertiesScreen(navController)
        propertyInfoScreen(navController)
    }
}