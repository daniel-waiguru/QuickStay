package com.danielwaiguru.tripicaandroid.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.danielwaiguru.tripicaandroid.designsystem.theme.TripitacaAndroidTestTheme
import com.danielwaiguru.tripitacaandroid.auth.presentation.navigation.SignInScreenDestination
import com.danielwaiguru.tripitacaandroid.auth.presentation.navigation.signInScreen
import com.danielwaiguru.tripitacaandroid.booking.presentation.navigation.bookingScreen
import com.danielwaiguru.tripitacaandroid.booking.presentation.navigation.navigateToBookingScreen
import com.danielwaiguru.tripitacaandroid.properties.presentation.navigation.navigateToPropertiesGraph
import com.danielwaiguru.tripitacaandroid.properties.presentation.navigation.propertiesFeatureGraph

@Composable
fun TripitacaRootNavigation() {
    TripitacaAndroidTestTheme {
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = SignInScreenDestination.route
            ) {
                signInScreen(
                    onNavigateToHome = {
                        navController.navigateToPropertiesGraph()
                    }
                )
                propertiesFeatureGraph(
                    navController = navController,
                    onClickBookNow = {
                        navController.navigateToBookingScreen(it)
                    }
                )
                bookingScreen(navController)
            }
        }
    }
}