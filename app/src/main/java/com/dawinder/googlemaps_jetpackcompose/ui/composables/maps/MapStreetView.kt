package com.dawinder.googlemaps_jetpackcompose.ui.composables.maps

import androidx.compose.runtime.Composable
import com.google.android.gms.maps.StreetViewPanoramaOptions
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.streetview.StreetView

@Composable
fun MapStreetView() {
    val edmontonCanadaPlace = LatLng(53.543140171924016, -113.48595856458006)
    StreetView(
        streetViewPanoramaOptionsFactory = {
            StreetViewPanoramaOptions().position(edmontonCanadaPlace)
        }
    )
}