package com.danielwaiguru.tripicaandroid.designsystem.components

import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TripitacaChip(
    modifier: Modifier = Modifier,
    label: String
) {
    AssistChip(
        onClick = {},
        label = {
            Text(text = label)
        },
        modifier = modifier,
        border = null,
        colors = AssistChipDefaults.assistChipColors(
            containerColor = Color(0xFFEEEEEE)
        )
    )
}