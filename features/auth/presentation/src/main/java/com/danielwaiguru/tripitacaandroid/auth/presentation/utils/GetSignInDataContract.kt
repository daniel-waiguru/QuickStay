package com.danielwaiguru.tripitacaandroid.auth.presentation.utils

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignInClient

class GetSignInDataContract : ActivityResultContract<GoogleSignInClient, Intent?>() {

    override fun createIntent(context: Context, input: GoogleSignInClient): Intent {
        return input.signInIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Intent? = intent
}