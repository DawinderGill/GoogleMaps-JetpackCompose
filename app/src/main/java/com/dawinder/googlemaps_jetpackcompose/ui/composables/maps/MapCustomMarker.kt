package com.dawinder.googlemaps_jetpackcompose.ui.composables.maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dawinder.googlemaps_jetpackcompose.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

/**
 * Composable function for displaying a map with a custom marker and info window content.
 *
 * This function displays a Google Map with a custom marker at a specific location (Edmonton, Canada
 * Place) and an associated info window. The info window contains an image, title, and snippet
 * describing the Canada Place location. Users can interact with the map and view the custom marker's
 * information when clicked.
 */
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