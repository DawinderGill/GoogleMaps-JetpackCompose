package com.dawinder.googlemaps_jetpackcompose.ui.composables.maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.widgets.DisappearingScaleBar
import com.google.maps.android.compose.widgets.ScaleBar

/**
 * Composable function for displaying a map with a scale bar.
 *
 * This function displays a Google Map with a scale bar, providing users with a visual reference to
 * estimate distances on the map. The scale bar is visible at all times, and there is also a
 * disappearing scale bar that disappears after a few seconds. The map is centered on a specific
 * location (Edmonton, Canada) with a predefined zoom level.
 */
@Composable
fun MapScaleBar() {
    Box(Modifier.fillMaxSize()) {
        val edmonton = LatLng(53.5345934095359, -113.50004285476653)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(edmonton, 10.5f)
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(zoomControlsEnabled = false)
        )

        // This will be visible all the times
        ScaleBar(
            modifier = Modifier
                .padding(top = 5.dp, end = 15.dp)
                .align(Alignment.TopEnd),
            cameraPositionState = cameraPositionState
        )

        // This will disappear after few seconds
        DisappearingScaleBar(
            modifier = Modifier
                .padding(top = 5.dp, end = 15.dp)
                .align(Alignment.TopStart),
            cameraPositionState = cameraPositionState
        )
    }
}