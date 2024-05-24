package com.danielwaiguru.tripicaandroid.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.danielwaiguru.tripicaandroid.designsystem.previews.DevicePreviews
import com.danielwaiguru.tripicaandroid.designsystem.theme.TripitacaAndroidTheme

@Composable
fun IconText(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}

@DevicePreviews
@Composable
fun IconTextPreview() {
    TripitacaAndroidTheme {
        IconText(
            icon = Icons.Outlined.LocationOn,
            text = "Nairobi, Kenya",
            modifier = Modifier.fillMaxWidth()
        )
    }
}