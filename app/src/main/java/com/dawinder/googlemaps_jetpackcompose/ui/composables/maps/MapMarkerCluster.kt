@file:OptIn(MapsComposeExperimentalApi::class)

package com.dawinder.googlemaps_jetpackcompose.ui.composables.maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.dawinder.googlemaps_jetpackcompose.data.MarkerItem
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.rememberCameraPositionState

/**
 * Composable function for displaying a map with clustered markers.
 *
 * This function displays a Google Map with clustered markers representing multiple points of interest
 * in Edmonton, Canada. It groups nearby markers into clusters for improved visualization when the map
 * is zoomed out. Users can interact with the map and view individual markers and their details when clicked.
 */
@Composable
fun MapMarkerCluster() {
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
        position = CameraPosition.fromLatLngZoom(builder.build().center, 10.5f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = false)
    ) {
        val markerItems = remember {
            mutableStateListOf(
                MarkerItem(edmontonCanadaPlace),
                MarkerItem(artGallery),
                MarkerItem(icsDistrict),
                MarkerItem(royalAlexandraHospital),
                MarkerItem(edmontonExoCentre),
                MarkerItem(naitCollage),
            )
        }

        Clustering(items = markerItems)
    }
}