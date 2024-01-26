package com.danielwaiguru.tripitacaandroid.auth.presentation

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacaPasswordInputField
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacaPrimaryButton
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacaRoundedInputField
import com.danielwaiguru.tripicaandroid.designsystem.previews.DevicePreviews
import com.danielwaiguru.tripicaandroid.designsystem.theme.TripitacaAndroidTestTheme
import com.danielwaiguru.tripicaandroid.designsystem.utils.Dimensions
import com.danielwaiguru.tripitacaandroid.auth.presentation.components.GoogleSignInButton
import com.danielwaiguru.tripitacaandroid.auth.presentation.utils.GetSignInDataContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


@Composable
fun SignInRoute(
    onNavigateToHome: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState1.collectAsStateWithLifecycle()
    SignInScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimensions.ScreenPadding),
        onSignInResult = viewModel::onUserSignIn,
        onNavigateToHome = onNavigateToHome,
        state = uiState,
        onPasswordChange = viewModel::onPasswordChange,
        onEmailChange = viewModel::onEmailChange,
        onErrorShown = viewModel::onErrorShown
    )
}

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onSignInResult: (Intent?) -> Unit,
    onNavigateToHome: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onErrorShown:() -> Unit,
    state: SignInUIState
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
    val error = state.errorId?.let { stringResource(id = it) }
    LaunchedEffect(key1 = state.errorId) {
        if (state.errorId != null) {
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_SHORT
            ).show()
            onErrorShown()
        }
    }
    LaunchedEffect(key1 = state.isLoggedIn) {
        if (state.isLoggedIn) onNavigateToHome()
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Image(
            painter = painterResource(id = R.drawable.tripitaca),
            contentDescription = stringResource(R.string.tripitaca_logo),
            modifier = Modifier
                .padding(horizontal = 100.dp)
                .padding(top = 55.dp)
        )
        Text(
            text = stringResource(R.string.welcome_back),
            modifier = Modifier
                .padding(top = 20.dp, bottom = 30.dp)
        )
        GoogleSignInButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp),
            text = stringResource(R.string.sign_in_with_google),
            onClick = {
                getGoogleSignInLauncher.launch(googleSignInClient)
            },
            loading = state.isLoading
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f),
                thickness = 1.dp
            )
            Text(
                text = stringResource(R.string.or),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f),
                thickness = 1.dp
            )


        }
        SignInWithEmail(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = state,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange
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
            state = SignInUIState(),
            onPasswordChange = {},
            onEmailChange = {},
            onErrorShown = {}
        )
    }
}

@Composable
fun SignInWithEmail(
    modifier: Modifier = Modifier,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    state: SignInUIState
) {
    val context = LocalContext.current
    val attemptEmailLoginMessage = stringResource(
        R.string.please_use_google_sign_in_option_above_to_login
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = stringResource(R.string.email_address)
        )
        TripitacaRoundedInputField(
            value = state.email,
            onValueChange = onEmailChange,
            label = "example@tripitaca.com",
            modifier = Modifier
                .fillMaxWidth()
                .height(53.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Text(
            text = stringResource(R.string.password),
            modifier = Modifier
                .padding(top = 12.dp)
        )
        TripitacaPasswordInputField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = "**********",
            modifier = Modifier
                .fillMaxWidth()
                .height(53.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        Text(
            text = stringResource(R.string.forgot_password),
            textAlign = TextAlign.End,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 15.dp),
            color = MaterialTheme.colorScheme.primary
        )
        TripitacaPrimaryButton(
            text = stringResource(id = R.string.sign_in),
            onClick = {
                 Toast.makeText(
                     context,
                     attemptEmailLoginMessage,
                     Toast.LENGTH_SHORT
                 ).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        )
    }
}