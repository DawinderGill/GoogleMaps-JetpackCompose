package com.dawinder.googlemaps_jetpackcompose.ui.composables.maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

/**
 * Composable function for displaying a simple Google Map.
 *
 * This function displays a basic Google Map centered on a specific location (Edmonton, Canada) with a
 * predefined zoom level. The map includes default user interface settings, such as pan and zoom controls.
 */
@Composable
fun MapSimple() {
    val edmonton = LatLng(53.5345934095359, -113.50004285476653)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(edmonton, 10.5f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = false)
    )
}