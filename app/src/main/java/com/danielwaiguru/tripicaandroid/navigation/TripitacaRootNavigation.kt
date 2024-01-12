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
                signInScreen(navController)
            }
        }
    }
}