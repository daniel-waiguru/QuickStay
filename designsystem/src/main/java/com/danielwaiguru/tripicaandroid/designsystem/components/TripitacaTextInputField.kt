package com.danielwaiguru.tripicaandroid.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun TripitacaRoundedInputField(
    value: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    onValueChange: (String) -> Unit,
    trailingIcon:  @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
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
                Text(text = label)
            }
        },
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon,
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