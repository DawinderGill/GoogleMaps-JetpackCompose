package com.dawinder.googlemaps_jetpackcompose.ui.fab

import androidx.compose.ui.graphics.Color
import com.dawinder.googlemaps_jetpackcompose.ui.theme.md_theme_light_onSecondary
import com.dawinder.googlemaps_jetpackcompose.ui.theme.md_theme_light_primary

/**
 * Represents a sub-item for a Floating Action Button (FAB) with customized icon and background tints.
 * Sub-items are secondary action buttons that appear when the main FAB is expanded.
 */
interface FabButtonSub {
    val iconTint: Color
    val backgroundTint: Color
}

/**
 * Implementation of [FabButtonSub] interface.
 *
 * @property iconTint The [Color] used to tint the icon of the sub-item.
 * @property backgroundTint The [Color] used to tint the background of the sub-item.
 */
private class FabButtonSubImpl(
    override val iconTint: Color,
    override val backgroundTint: Color
) : FabButtonSub

/**
 * Creates a new instance of [FabButtonSub] with the provided icon and background tints.
 *
 * @param backgroundTint The [Color] used to tint the background of the sub-item.
 * @param iconTint The [Color] used to tint the icon of the sub-item.
 * @return A new instance of [FabButtonSub] with the specified icon and background tints.
 */
fun FabButtonSub(
    backgroundTint: Color = md_theme_light_primary,
    iconTint: Color = md_theme_light_onSecondary
): FabButtonSub = FabButtonSubImpl(iconTint, backgroundTint)