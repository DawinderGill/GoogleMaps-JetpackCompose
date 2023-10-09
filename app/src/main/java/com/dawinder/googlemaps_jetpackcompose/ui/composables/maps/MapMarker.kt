package com.dawinder.googlemaps_jetpackcompose.ui.composables.maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dawinder.googlemaps_jetpackcompose.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

/**
 * Composable function for displaying a map with a standard marker.
 *
 * This function displays a Google Map with a standard marker at a specific location (Edmonton,
 * Canada Place). The marker represents a point of interest and provides basic information such as
 * a title and snippet. Users can interact with the map and view marker details when clicked.
 */
@Composable
fun MapMarker() {
    val edmontonCanadaPlace = LatLng(53.542763503552784, -113.48666486116335)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(edmontonCanadaPlace, 13f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = false)
    ) {
        Marker(
            state = MarkerState(position = edmontonCanadaPlace),
            title = stringResource(R.string.canada_place),
            snippet = stringResource(R.string.government_office)
        )
    }
}