package com.dawinder.googlemaps_jetpackcompose.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dawinder.googlemaps_jetpackcompose.R
import com.dawinder.googlemaps_jetpackcompose.ui.composables.maps.MapCustomMarker
import com.dawinder.googlemaps_jetpackcompose.ui.composables.maps.MapMarker
import com.dawinder.googlemaps_jetpackcompose.ui.composables.maps.MapMarkerCluster
import com.dawinder.googlemaps_jetpackcompose.ui.composables.maps.MapMultipleMarker
import com.dawinder.googlemaps_jetpackcompose.ui.composables.maps.MapScaleBar
import com.dawinder.googlemaps_jetpackcompose.ui.composables.maps.MapSimple
import com.dawinder.googlemaps_jetpackcompose.ui.composables.maps.MapStreetView
import com.dawinder.googlemaps_jetpackcompose.ui.fab.FabButtonItem
import com.dawinder.googlemaps_jetpackcompose.ui.fab.FabButtonMain
import com.dawinder.googlemaps_jetpackcompose.ui.fab.FabButtonSub
import com.dawinder.googlemaps_jetpackcompose.ui.fab.MultiFloatingActionButton
import com.dawinder.googlemaps_jetpackcompose.utils.MapOptions

/**
 * Composable function representing the main screen of the application.
 *
 * This function defines the layout and behavior of the main screen, which includes a Scaffold with a
 * Multi-Floating Action Button (Multi-FAB) for selecting different map options. The selected map
 * option determines which map-related content is displayed on the screen.
 *
 * @see MapOptions Enum representing different map options that can be selected.
 * @see MapSimple Composable function for displaying a simple map.
 * @see MapMarker Composable function for displaying map markers.
 * @see MapCustomMarker Composable function for displaying custom map markers.
 * @see MapMultipleMarker Composable function for displaying multiple map markers.
 * @see MapMarkerCluster Composable function for displaying clustered map markers.
 * @see MapScaleBar Composable function for displaying a map scale bar.
 * @see MapStreetView Composable function for displaying a street view map.
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
                },
                FabButtonItem(
                    iconRes = Icons.Filled.AddAlert,
                    label = stringResource(R.string.map_scale_bar)
                ) {
                    selectedMapOption = MapOptions.MAP_SCALE_BAR
                },
                FabButtonItem(
                    iconRes = Icons.Filled.AddAlert,
                    label = stringResource(R.string.map_street_view)
                ) {
                    selectedMapOption = MapOptions.MAP_STREET_VIEW
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
                MapOptions.MAP_SCALE_BAR -> MapScaleBar()
                MapOptions.MAP_STREET_VIEW -> MapStreetView()
            }
        }
    })
}