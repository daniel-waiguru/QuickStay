package com.danielwaiguru.tripicaandroid.designsystem.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.danielwaiguru.tripicaandroid.designsystem.previews.DevicePreviews
import com.danielwaiguru.tripicaandroid.designsystem.theme.TripitacaAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripitacaTopAppBar(
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    title: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors()
) {
    TopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        colors = colors
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@DevicePreviews
@Composable
fun TripitacaTopAppBarPreview() {
    TripitacaAndroidTheme {
        TripitacaTopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {},
            navigationIcon = {},
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = null
                    )
                }
            }
        )
    }
}