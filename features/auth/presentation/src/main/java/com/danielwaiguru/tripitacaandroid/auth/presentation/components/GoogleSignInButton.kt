package com.danielwaiguru.tripitacaandroid.auth.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danielwaiguru.tripicaandroid.designsystem.components.ProgressIndicator
import com.danielwaiguru.tripicaandroid.designsystem.previews.DevicePreviews
import com.danielwaiguru.tripicaandroid.designsystem.theme.TripitacaAndroidTestTheme
import com.danielwaiguru.tripitacaandroid.auth.presentation.R

@Composable
fun GoogleSignInButton(
    modifier: Modifier = Modifier,
    text: String,
    loading: Boolean = false,
    onClick: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        ),
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 10.dp,
                    bottom = 10.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = stringResource(R.string.google_logo),
                tint = Color.Unspecified,
            )
            Spacer(
                modifier = Modifier
                    .width(8.dp)
                    .height(30.dp)
            )
            Text(
                text = text
            )
            if (loading) {
                Spacer(
                    modifier = Modifier
                        .width(12.dp)
                )
                ProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp)
                )
            }
        }
    }
}

@DevicePreviews
@Composable
fun GoogleSignInButtonPreview() {
    TripitacaAndroidTestTheme {
        GoogleSignInButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.sign_in_with_google),
            onClick = {}
        )

    }
}