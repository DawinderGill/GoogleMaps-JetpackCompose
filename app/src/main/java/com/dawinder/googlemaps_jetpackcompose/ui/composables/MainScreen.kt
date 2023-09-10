@file:OptIn(MapsComposeExperimentalApi::class)

package com.dawinder.googlemaps_jetpackcompose.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dawinder.googlemaps_jetpackcompose.R
import com.dawinder.googlemaps_jetpackcompose.data.MarkerItem
import com.dawinder.googlemaps_jetpackcompose.ui.fab.FabButtonItem
import com.dawinder.googlemaps_jetpackcompose.ui.fab.FabButtonMain
import com.dawinder.googlemaps_jetpackcompose.ui.fab.FabButtonSub
import com.dawinder.googlemaps_jetpackcompose.ui.fab.MultiFloatingActionButton
import com.dawinder.googlemaps_jetpackcompose.utils.MapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.rememberCameraPositionState

/**
 * Composable function to display the main screen of the application.
 * The main screen contains a Scaffold with a Multi-Floating Action Button (Multi-FAB) and a Text component.
 * When the Multi-FAB is expanded, it shows sub-items as individual Floating Action Buttons (FABs).
 */
@Composable
fun MainScreen() {
    var selectedMapOption by rememberSaveable { mutableStateOf(MapOptions.MAP_SIMPLE) }

    // Scaffold with a Multi-Floating Action Button (Multi-FAB)
    Scaffold(floatingActionButton = {
        MultiFloatingActionButton(
            items = listOf(
                FabButtonItem(
                    iconRes = Icons.Filled.Home,
                    label = stringResource(R.string.map_simple)
                ) {
                    selectedMapOption = MapOptions.MAP_SIMPLE
                },
                FabButtonItem(
                    iconRes = Icons.Filled.ListAlt,
                    label = stringResource(R.string.map_marker)
                ) {
                    selectedMapOption = MapOptions.MAP_MARKER
                },
                FabButtonItem(
                    iconRes = Icons.Filled.AddAlert,
                    label = stringResource(R.string.map_custom_marker)
                ) {
                    selectedMapOption = MapOptions.MAP_CUSTOM_MARKER
                },
                FabButtonItem(
                    iconRes = Icons.Filled.AddAlert,
                    label = stringResource(R.string.map_multiple_marker)
                ) {
                    selectedMapOption = MapOptions.MAP_MULTIPLE_MARKER
                },
                FabButtonItem(
                    iconRes = Icons.Filled.AddAlert,
                    label = stringResource(R.string.map_marker_cluster)
                ) {
                    selectedMapOption = MapOptions.MAP_MARKER_CLUSTER
                }
            ),
            fabIcon = FabButtonMain(),
            fabOption = FabButtonSub()
        )
    }, content = { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (selectedMapOption) {
                MapOptions.MAP_SIMPLE -> MapSimple()
                MapOptions.MAP_MARKER -> MapMarker()
                MapOptions.MAP_CUSTOM_MARKER -> MapCustomMarker()
                MapOptions.MAP_MULTIPLE_MARKER -> MapMultipleMarker()
                MapOptions.MAP_MARKER_CLUSTER -> MapMarkerCluster()
            }
        }
    })
}

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

@Composable
fun MapCustomMarker() {
    val edmontonCanadaPlace = LatLng(53.542763503552784, -113.48666486116335)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(edmontonCanadaPlace, 13f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = false)
    ) {
        MarkerInfoWindowContent(
            state = MarkerState(position = edmontonCanadaPlace),
            title = stringResource(R.string.canada_place),
            snippet = stringResource(R.string.government_office)
        ) { marker ->
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
                Image(
                    modifier = Modifier
                        .size(size = 60.dp)
                        .clip(shape = RoundedCornerShape(size = 10.dp)),
                    painter = painterResource(id = R.drawable.edmonton_canada_place),
                    contentDescription = stringResource(R.string.canada_place_des),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(
                        text = marker.title ?: "",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = marker.snippet ?: "")
                }
            }
        }
    }
}

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