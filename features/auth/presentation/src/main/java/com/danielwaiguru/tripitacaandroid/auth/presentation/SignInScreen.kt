package com.danielwaiguru.tripitacaandroid.auth.presentation

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.danielwaiguru.tripicaandroid.designsystem.previews.DevicePreviews
import com.danielwaiguru.tripicaandroid.designsystem.theme.TripitacaAndroidTestTheme
import com.danielwaiguru.tripicaandroid.designsystem.utils.Dimensions
import com.danielwaiguru.tripitacaandroid.auth.presentation.components.GoogleSignInButton
import com.danielwaiguru.tripitacaandroid.auth.presentation.utils.GetSignInDataContract
import com.danielwaiguru.tripitacaandroid.shared.state.ViewState
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


@Composable
fun SignInRoute(
    onNavigateToHome: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsStateWithLifecycle()
    SignInScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimensions.ScreenPadding),
        onSignInResult = viewModel::onUserSignIn,
        onNavigateToHome = onNavigateToHome,
        state = uiState
    )
}

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onSignInResult: (Intent?) -> Unit,
    onNavigateToHome: () -> Unit,
    state: ViewState?
) {
    val webClient = stringResource(id = R.string.web_client_id)
    val context = LocalContext.current
    val activity = context as Activity
    val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        )
            .requestIdToken(webClient)
            .requestEmail()
            .build()
        GoogleSignIn.getClient(activity, gso)
    }
    val getGoogleSignInLauncher = rememberLauncherForActivityResult(
        contract = GetSignInDataContract(),
        onResult = onSignInResult
    )
    when(state) {
        is ViewState.Error -> {
            val error = state.message ?: state.messageRes?.let { stringResource(id = it) }
            if (error.isNullOrBlank().not()) {
                Toast.makeText(
                    context,
                    error,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        is ViewState.Success<*> -> onNavigateToHome()
        else -> Unit
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.tripitaca),
            contentDescription = stringResource(R.string.tripitaca_logo),
            modifier = Modifier
                .padding(horizontal = 30.dp)
        )
        Text(
            text = stringResource(R.string.welcome_back),
            modifier = Modifier
                .padding(vertical = 10.dp)
        )
        GoogleSignInButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(R.string.sign_in_with_google),
            onClick = {
                getGoogleSignInLauncher.launch(googleSignInClient)
            },
            loading = state is ViewState.Loading
        )
    }
}

@DevicePreviews
@Composable
fun SignInScreenPreview() {
    TripitacaAndroidTestTheme {
        SignInScreen(
            modifier = Modifier
                .fillMaxSize(),
            onSignInResult = {},
            onNavigateToHome = {},
            state = null
        )
    }
}