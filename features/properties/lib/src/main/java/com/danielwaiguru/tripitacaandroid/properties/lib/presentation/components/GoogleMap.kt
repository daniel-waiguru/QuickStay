package com.danielwaiguru.tripitacaandroid.properties.lib.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.danielwaiguru.tripitacaandroid.shared.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun QuickStayGoogleMap(
    location: LatLng,
    title: String?,
    modifier: Modifier = Modifier
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 10f)
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            mapStyleOptions = if (isSystemInDarkTheme()) {
                MapStyleOptions.loadRawResourceStyle(
                    LocalContext.current, R.raw.map_style
                )
            } else null
        )
    ) {
        Marker(
            state = MarkerState(position = location),
            title = title
        )
    }
}