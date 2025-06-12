package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun GarisAbu(modifier: Modifier = Modifier, thickness: Dp = 1.dp) {
    Divider(color = Warna.AbuMuda, thickness = thickness, modifier = modifier)
}
