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
import com.dawinder.googlemaps_jetpackcompose.ui.composables.maps.MapCustomMarker
import com.dawinder.googlemaps_jetpackcompose.ui.composables.maps.MapMarker
import com.dawinder.googlemaps_jetpackcompose.ui.composables.maps.MapMarkerCluster
import com.dawinder.googlemaps_jetpackcompose.ui.composables.maps.MapMultipleMarker
import com.dawinder.googlemaps_jetpackcompose.ui.composables.maps.MapSimple
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