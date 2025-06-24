package com.danielwaiguru.tripitacaandroid.auth.lib.presentation.utils

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import javax.inject.Inject

/**
 * Google Sign In wrapper for testing
 */
internal class GoogleSignInWrapper @Inject constructor() {
    fun getSignedInAccountFromIntent(data: Intent?): Task<GoogleSignInAccount> {
        return GoogleSignIn.getSignedInAccountFromIntent(data)
    }
}