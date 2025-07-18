package com.danielwaiguru.tripitacaandroid.properties.lib.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.danielwaiguru.tripitacaandroid.properties.lib.presentation.propertieslisting.navigation.PropertiesScreen
import com.danielwaiguru.tripitacaandroid.properties.lib.presentation.propertieslisting.navigation.propertiesScreen
import com.danielwaiguru.tripitacaandroid.properties.lib.presentation.propertyinfo.navigation.propertyInfoScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToPropertiesGraph(navOptions: NavOptions? = null) = navigate(
    PropertiesNavigationGraph,
    navOptions
)
@Serializable
object PropertiesNavigationGraph
fun NavGraphBuilder.propertiesFeatureGraph(
    navController: NavHostController,
    onClickBookNow: (id: String) -> Unit
) {
    navigation<PropertiesNavigationGraph>(
        startDestination = PropertiesScreen
    ) {
        propertiesScreen(navController)
        propertyInfoScreen(navController, onClickBookNow)
    }
}

internal const val animationDuration = 700