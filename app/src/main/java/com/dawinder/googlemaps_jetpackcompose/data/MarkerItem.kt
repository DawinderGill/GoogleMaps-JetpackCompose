package com.dawinder.googlemaps_jetpackcompose.data

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

/**
 * Represents a marker item that can be placed on a map and used for clustering.
 *
 * This data class is typically used to define markers on a map. It implements the [ClusterItem]
 * interface, which allows it to be used with marker clustering libraries. Each [MarkerItem] has a
 * position, title, and snippet that can be displayed on the map.
 *
 * @property itemPosition The geographical position of the marker.
 * @property itemTitle The title of the marker (optional, defaults to an empty string).
 * @property itemSnippet Additional information or snippet for the marker (optional, defaults to an empty string).
 */
data class MarkerItem(
    val itemPosition: LatLng,
    val itemTitle: String = "",
    val itemSnippet: String = ""
) :
    ClusterItem {
    override fun getPosition(): LatLng =
        itemPosition

    override fun getTitle(): String =
        itemTitle

    override fun getSnippet(): String =
        itemSnippet
}