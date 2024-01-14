package com.danielwaiguru.tripicaandroid.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danielwaiguru.tripicaandroid.designsystem.R


@Composable
fun TripitacaRoundedInputField(
    value: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    readOnly: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        shape = RoundedCornerShape(10.dp),
        placeholder = {
            if (label != null) {
                Text(
                    text = label,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        },
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon,
        readOnly = readOnly,
        visualTransformation = visualTransformation,
    )
}

@Composable
fun TripitacaPasswordInputField(
    value: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    isError: Boolean = false,
    readOnly: Boolean = false
) {
    PasswordInputField(
        value = value,
        modifier = modifier,
        label = label,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        isError = isError,
        readOnly = readOnly
    )
}

@Composable
private fun PasswordInputField(
    value: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    isError: Boolean = false,
    readOnly: Boolean = false
) {
    var peekPassword by rememberSaveable {
        mutableStateOf(true)
    }
    TripitacaRoundedInputField(
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            val icon = if (peekPassword) {
                Icons.Rounded.VisibilityOff
            } else {
                Icons.Rounded.Visibility
            }
            IconButton(
                onClick = { peekPassword = peekPassword.not() }
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = stringResource(R.string.view_password)
                )
            }
        },
        label = label,
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        visualTransformation = if (peekPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        isError = isError,
        readOnly = readOnly
    )
}

@Preview(showBackground = true)
@Composable
fun TripitacaRoundedInputFieldPreview() {
    TripitacaRoundedInputField(
        value = "",
        label = "Phoen",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth()
    )
}