package com.danielwaiguru.tripitacaandroid.properties.lib.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.danielwaiguru.tripitacaandroid.properties.lib.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.maps.android.compose.rememberUpdatedMarkerState

@Composable
fun QuickStayGoogleMap(
    location: LatLng,
    title: String?,
    modifier: Modifier = Modifier
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 10f)
    }

    val markerState = rememberUpdatedMarkerState(position = location)
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val context = LocalContext.current
    val mapStyleOptions = remember {
        if (isSystemInDarkTheme) {
            MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style)
        } else null
    }
    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            mapStyleOptions = mapStyleOptions
        )
    ) {
        Marker(
            state = markerState,
            title = title
        )
    }
}