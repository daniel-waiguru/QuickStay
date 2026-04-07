package com.danielwaiguru.tripitacaandroid.auth.lib.presentation.utils

import android.app.Activity
import android.util.Base64
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.danielwaiguru.auth.contract.models.User
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import java.security.SecureRandom

internal class GoogleSignInLauncher internal constructor(
    private val scope: CoroutineScope,
    private val launchBlock: suspend () -> Unit
) {
    fun launch() {
        scope.launch {
            launchBlock()
        }
    }
}

@Composable
internal fun rememberGoogleSignInLauncher(
    webClientId: String,
    onResult: (Result<User>) -> Unit
): GoogleSignInLauncher {

    val context = LocalContext.current
    val activity = context as Activity
    val scope = rememberCoroutineScope()

    val credentialManager = remember {
        CredentialManager.create(activity)
    }

    val currentOnResult by rememberUpdatedState(onResult)

    fun generateSecureRandomNonce(byteLength: Int = 32): String {
        val randomBytes = ByteArray(byteLength)
        SecureRandom().nextBytes(randomBytes)
        return Base64.encodeToString(
            randomBytes,
            Base64.NO_WRAP or Base64.URL_SAFE or Base64.NO_PADDING
        )
    }

    return remember {
        GoogleSignInLauncher(
            scope = scope,
            launchBlock = {
                try {
                    val option = GetGoogleIdOption.Builder()
                        .setServerClientId(webClientId)
                        .setFilterByAuthorizedAccounts(false)
                        .setNonce(generateSecureRandomNonce())
                        .setAutoSelectEnabled(false)
                        .build()

                    val request = GetCredentialRequest.Builder()
                        .addCredentialOption(option)
                        .build()

                    val result = credentialManager.getCredential(
                        context = activity,
                        request = request
                    )

                    val credential = result.credential

                    if (credential is CustomCredential &&
                        credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
                    ) {
                        val googleCred =
                            GoogleIdTokenCredential.createFrom(credential.data)
                        val user = User(
                            id = googleCred.id,
                            displayName = googleCred.displayName,
                            email = googleCred.email,
                            photoUrl = googleCred.profilePictureUri?.toString()
                        )

                        currentOnResult(Result.success(user))
                    } else {
                        currentOnResult(Result.failure(Exception("Invalid credential")))
                    }

                } catch (e: Exception) {
                    currentCoroutineContext().ensureActive()
                    currentOnResult(Result.failure(e))
                }
            }
        )
    }
}