package com.dawinder.googlemaps_jetpackcompose.ui.composables.maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dawinder.googlemaps_jetpackcompose.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

/**
 * Composable function for displaying a map with multiple individual markers.
 *
 * This function displays a Google Map with multiple individual markers representing various points of
 * interest in Edmonton, Canada. Each marker is associated with a specific location, title, and snippet
 * providing information about the point of interest. Users can interact with the map and view details
 * of individual markers when clicked.
 */
@Composable
fun MapMultipleMarker() {
    val edmontonCanadaPlace = LatLng(53.542763503552784, -113.48666486116335)
    val artGallery = LatLng(53.54516462106588, -113.48877356392386)
    val icsDistrict = LatLng(53.54661005021069, -113.49641131314762)
    val royalAlexandraHospital = LatLng(53.558112865163814, -113.49774393177967)
    val edmontonExoCentre = LatLng(53.57021771357655, -113.45439468152234)
    val naitCollage = LatLng(53.56904135312879, -113.50612591137424)

    val builder = LatLngBounds.builder()
    builder.include(edmontonCanadaPlace)
    builder.include(artGallery)
    builder.include(icsDistrict)
    builder.include(royalAlexandraHospital)
    builder.include(edmontonExoCentre)
    builder.include(naitCollage)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(builder.build().center, 13f)
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
        Marker(
            state = MarkerState(position = artGallery),
            title = stringResource(R.string.art_gallery),
            snippet = stringResource(R.string.edmonton)
        )
        Marker(
            state = MarkerState(position = icsDistrict),
            title = stringResource(R.string.ics_district),
            snippet = stringResource(R.string.edmonton)
        )
        Marker(
            state = MarkerState(position = royalAlexandraHospital),
            title = stringResource(R.string.royal_alexandra_hospital),
            snippet = stringResource(R.string.edmonton)
        )
        Marker(
            state = MarkerState(position = edmontonExoCentre),
            title = stringResource(R.string.edmonton_expo_centre),
            snippet = stringResource(R.string.edmonton)
        )
        Marker(
            state = MarkerState(position = naitCollage),
            title = stringResource(R.string.nait_collage),
            snippet = stringResource(R.string.edmonton)
        )
    }
}