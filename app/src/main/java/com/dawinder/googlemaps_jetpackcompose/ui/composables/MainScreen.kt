package com.dawinder.googlemaps_jetpackcompose.ui.composables

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dawinder.googlemaps_jetpackcompose.R
import com.dawinder.googlemaps_jetpackcompose.ui.fab.FabButtonItem
import com.dawinder.googlemaps_jetpackcompose.ui.fab.FabButtonMain
import com.dawinder.googlemaps_jetpackcompose.ui.fab.FabButtonSub
import com.dawinder.googlemaps_jetpackcompose.ui.fab.MultiFloatingActionButton
import com.dawinder.googlemaps_jetpackcompose.utils.MapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
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
                    label = stringResource(R.string.map_markers)
                ) {
                    selectedMapOption = MapOptions.MAP_MARKER
                },
                FabButtonItem(
                    iconRes = Icons.Filled.AddAlert,
                    label = "Map Other"
                ) {
                    //selectedMapOption = MapOptions.MAP_MARKER
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
            MapSimple()
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
        modifier = Modifier
            .fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = false)
    )
}