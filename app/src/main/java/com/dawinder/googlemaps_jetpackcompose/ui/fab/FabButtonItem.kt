package com.dawinder.googlemaps_jetpackcompose.ui.fab

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Represents an item for a Floating Action Button (FAB) with an icon and label.
 *
 * @param iconRes The [ImageVector] representing the icon to be displayed on the FAB item.
 * @param label The label or text associated with the FAB item.
 *
 * @constructor Creates a new instance of [FabButtonItem].
 */
data class FabButtonItem(val iconRes: ImageVector, val label: String)
